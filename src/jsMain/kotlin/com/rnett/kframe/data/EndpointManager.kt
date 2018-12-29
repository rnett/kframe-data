package com.rnett.kframe.data

import io.ktor.client.HttpClient
import io.ktor.client.request.request
import io.ktor.client.request.url
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.serialization.cbor.CBOR
import kotlinx.serialization.dumps
import kotlin.js.Date.Companion.now
import kotlin.reflect.KFunction

fun <R> callEndpoint(
    endpoint: KFunction<R>,
    client: HttpClient = HttpClient(),
    vararg params: Any,
    timeout: Long = 10000
): R = callEndpoint(endpoint, client, params, timeout)


fun <R> callEndpointNoTimeout(endpoint: KFunction<R>, client: HttpClient = HttpClient(), vararg params: Any): R =
    callEndpoint(endpoint, client, params, -1)

@ExperimentalCoroutinesApi
fun <R> callEndpoint(
    endpoint: KFunction<R>,
    client: HttpClient = HttpClient(),
    params: List<Any>,
    timeout: Long = 10000
): R {

    if (EndpointManager.allowOnlyInDataTransferBLock && !EndpointManager.inDataTransfer)
        throw IllegalArgumentException("Transfer not allowed when not in dataTransfer block (see EndpointManager.allowOnlyInDataTransferBLock")

    val endpointId =
        EndpointManager.endpoints[endpoint] ?: throw IllegalArgumentException("Endpoint unknown for function")
    val end =
        EndpointManager.endpointsById[endpointId] ?: throw IllegalArgumentException("Endpoint unknown for function")
    val data = EndpointData(end.serializeParams(params), endpointId)

    val job = GlobalScope.async(Dispatchers.Default) {
        client.request<String> {
            this.method = KFrameDataRequest
            body = CBOR.dumps(EndpointData.serializer(), data)

            if (EndpointManager.serverUrl.isBlank())
                throw RuntimeException("KFrameDataServer.serverUrl must be set")

            this.url(EndpointManager.serverUrl)
        }
    }

    val startTime = now()

    while (!(job.isCancelled || job.isCompleted) && (timeout <= 0 || (now() - startTime) < timeout)) {
    }

    if (job.isCompleted) {
        return end.deserializeResult(job.getCompleted()) as R
    } else if (job.isActive) {
        job.cancel()
        throw RuntimeException("KFrame Data call timed out")
    } else {
        throw job.getCompletionExceptionOrNull() ?: RuntimeException("Coroutine failed for call")
    }
}
