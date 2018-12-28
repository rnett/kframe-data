package com.rnett.kframe.data

/*
import io.ktor.http.HttpMethod
import kotlin.js.JsName
import kotlin.jvm.JvmName
import kotlin.reflect.KClass

object DataHandlers{
    private val _handlers = mutableMapOf<String, DataHandler<*, *>>()

    fun <D : Data<*, *>> addHandler(klass: KClass<D>, handler: DataHandler<D, *>) = _handlers.put(klass.qualifiedName!!, handler)
    inline fun <reified D : Data<*, *>> addHandler(handler: DataHandler<D, *>) = addHandler(D::class, handler)

    operator fun <D : Data<*, *>> set(klass: KClass<D>, handler: DataHandler<D, *>) = addHandler(klass, handler)

    inline operator fun <reified D : Data<*, *>> plusAssign(handler: DataHandler<D, *>){ addHandler(handler) }

    internal val handlers get() = _handlers.toMap()
    internal operator fun <D : Data<*, I>, I> get(klass: KClass<D>) = _handlers[klass.qualifiedName!!] as DataHandler<D, I>
    internal operator fun <D : Data<*, I>, I> get(klassQualifiedName: String) = _handlers[klassQualifiedName] as DataHandler<D, I>
    @JvmName("getNoType")
    @JsName("getNoType")
    internal operator fun get(klassQualifiedName: String) = _handlers[klassQualifiedName]
}

enum class DataMethod{
    Save, Update, Load, Delete, Create, All
}

object KFrameDataServer{
    var url = ""
}
        */
