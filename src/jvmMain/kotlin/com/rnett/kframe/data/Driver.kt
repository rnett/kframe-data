package com.rnett.kframe.data

/*
import com.rnett.kframe.data.DataMethod.*
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.application.ApplicationCallPipeline
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.httpMethod
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.util.pipeline.PipelineContext
import kotlinx.serialization.*
import kotlinx.serialization.cbor.CBOR
import kotlinx.serialization.json.JSON
import java.lang.RuntimeException
import javax.activation.DataHandler
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.declaredMemberFunctions

class KFrameDataCallException(val kframeMessage: String) : RuntimeException(kframeMessage)

fun Application.kframeDataDriver() {
    intercept(ApplicationCallPipeline.Call) {
        if (call.request.httpMethod == KFrameDataRequest) {
            try {

                val methodId = call.parameters["method"] ?: respondFailure("method must be passed")

                val method = valueOf(methodId)

                val classQualifiedName = call.parameters["klassName"]
                    ?: respondFailure("klassName must be passed")
                val handler = DataHandlers[classQualifiedName] as? DataHandler<Data<*, Any>, Any>?
                    ?: respondFailure("No DataHandler registered for $classQualifiedName")

                val body = call.receiveText()

                val data = if (body.isBlank()) null else CBOR.plain.loads(handler.serializer, body)

                when (method) {
                    Save -> {
                        if(data == null)
                            respondFailure("Data must be given for $method")
                        else
                            handler.save(data)
                    }
                    Update -> {
                        if(data == null)
                            respondFailure("Data must be given for $method")
                        else {
                            handler.update(data)
                            respondData(data)
                        }
                    }
                    Load -> {
                        val idStr = call.parameters["id"]
                        if(idStr == null)
                            respondFailure("ID must be passed")
                        else {
                            val id = CBOR.plain.loads(handler.idSerializer, idStr)
                            respondData(handler.load(id))
                        }
                    }
                    Delete -> {
                        if(data == null)
                            respondFailure("Data must be given for $method")
                        else {
                            handler.delete(data)
                            respondSuccess()
                        }
                    }
                    Create -> {
                        val argsStr = call.parameters["args"]
                        val args = argsStr?.let{ JSON.parse(String.serializer().list, it) } ?: emptyList()

                        respondData(handler.create(args))
                    }
                    All -> respondDataList(handler.all(), handler)
                }
            } catch (e : KFrameDataCallException){
                call.respond(HttpStatusCode.BadRequest, e.kframeMessage)
            }
        }
    }
}


private fun <T> PipelineContext<Unit, ApplicationCall>.respondFailure(message: String) : T =
    throw KFrameDataCallException(message)

private suspend fun <D : Data<*, *>> PipelineContext<Unit, ApplicationCall>.respondData(data: D){
    call.respond(CBOR.plain.dumps(data.handler.serializer as KSerializer<D>, data))
}

private suspend fun PipelineContext<Unit, ApplicationCall>.respondSuccess(){
    call.respond(HttpStatusCode.OK, "")
}

private suspend fun <D : Data<*, *>> PipelineContext<Unit, ApplicationCall>.respondDataList(data: List<D>, handler: DataHandler<D, *>){
    call.respond(CBOR.plain.dumps(handler.serializer.list, data))
}
        */
