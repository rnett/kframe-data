package com.rnett.kframe.data

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.request.receiveText
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.method
import io.ktor.util.pipeline.ContextDsl
import io.ktor.util.pipeline.PipelineContext
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.cbor.CBOR
import kotlinx.serialization.loads
import kotlin.reflect.KFunction

private val handledEndpoints = mutableSetOf<Int>()

private var bodyCache: String? = null

val PipelineContext<Unit, ApplicationCall>.body: String
    get() {
        if (bodyCache == null)
            bodyCache = runBlocking { call.receiveText() }

        return bodyCache!!
    }

@ContextDsl
fun Route.kframeEndpoint(endpoint: KFunction<*>): Route = kframeEndpoints(endpoint)

@ContextDsl
fun Route.kframeEndpoints(vararg endpoints: KFunction<*>): Route = kframeEndpoints(endpoints.toList())

@ContextDsl
fun Route.kframeEndpoints(endpoints: List<KFunction<*>>): Route {

    val ids = endpoints.map { EndpointManager.endpoints[it]!! }

    if (ids.any { it in handledEndpoints })
        throw RuntimeException("Endpoint is already handled")

    handledEndpoints.addAll(ids)

    return method(KFrameDataRequest) {
        handle {
            val data = CBOR.loads(EndpointData.serializer(), body)
            val end = (EndpointManager.endpointsById[data.endpointId]
                ?: throw RuntimeException("Endpoint Not Found")) as Endpoint<Any>
            call.respondText(doCall(end, data.params))
        }
    }
}

@ContextDsl
fun Route.kframeRemainingEndpoints(): Route {

    val ids = EndpointManager.endpointsById.keys - handledEndpoints

    return method(KFrameDataRequest) {
        handle {
            val data = CBOR.loads(EndpointData.serializer(), body)

            if (data.endpointId in handledEndpoints)
                this.proceed()

            val end = (EndpointManager.endpointsById[data.endpointId]
                ?: throw RuntimeException("Endpoint Not Found")) as Endpoint<Any>
            call.respondText(doCall(end, data.params))
        }
    }
}

fun <R : Any> doCall(endpoint: Endpoint<R>, params: List<String>): String {
    val deserializedParams = endpoint.deserializeParams(params)
    val result = endpoint.method.call(*deserializedParams.toTypedArray())
    return endpoint.serializeResult(result)
}