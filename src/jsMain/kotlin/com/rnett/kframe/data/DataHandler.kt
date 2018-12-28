package com.rnett.kframe.data

/*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.serialization.*
import kotlinx.serialization.cbor.CBOR
import kotlinx.serialization.json.JSON
import kotlin.reflect.KClass

@ExperimentalCoroutinesApi
actual abstract class DataHandler<D, I> actual constructor(
    actual val dataClass: KClass<D>,
    actual val serializer: KSerializer<D>,
    actual val idSerializer: KSerializer<I>
) where D : Data<*, I>, D : Any {
    init {
        DataHandlers.addHandler(dataClass, this)
    }

    actual fun save(data: D){
        val job = GlobalScope.launch {
            sendKFrameRequest(
                DataMethod.Save,
                className,
                CBOR.plain.dumps(serializer, data)
            )
        }
        while(job.isActive){}
    }

    actual fun update(data: D){
        val job = GlobalScope.launch {
            sendKFrameRequest(
                DataMethod.Update,
                className,
                CBOR.plain.dumps(serializer, data)
            )
        }
        while(job.isActive){}
    }

    actual fun load(id: I): D {
        val job = GlobalScope.async {
            sendKFrameRequest(
                DataMethod.Load,
                className,
                "",
                mapOf("id" to CBOR.plain.dumps(idSerializer, id))
            )
        }

        while(job.isActive){}

        if(job.isCompleted)
            return job.getCompleted().let{
                CBOR.plain.loads(serializer, it)
            }
        else
            throw job.getCompletionExceptionOrNull() ?: RuntimeException("Coroutine failed for call")
    }

    actual fun delete(data: D){
        val job = GlobalScope.launch {
            sendKFrameRequest(
                DataMethod.Delete,
                className,
                CBOR.plain.dumps(serializer, data)
            )
        }
        while(job.isActive){}
    }

    actual fun create(args: List<String>): D {
        val job = GlobalScope.async {
            sendKFrameRequest(
                DataMethod.Create,
                className,
                "",
                mapOf("params" to JSON.stringify(String.serializer().list, args))
            )
        }

        while(job.isActive){}
        if(job.isCompleted)
            return job.getCompleted().let{
                CBOR.plain.loads(serializer, it)
            }
        else
            throw job.getCompletionExceptionOrNull() ?: RuntimeException("Coroutine failed for call")
    }

    actual fun all(): List<D>{
        val job = GlobalScope.async {
            sendKFrameRequest(
                DataMethod.All,
                className
            )
        }

        while(job.isActive){}
        if(job.isCompleted)
            return job.getCompleted().let{
                CBOR.plain.loads(serializer.list, it)
            }
        else
            throw job.getCompletionExceptionOrNull() ?: RuntimeException("Coroutine failed for call")
    }
}
        */
