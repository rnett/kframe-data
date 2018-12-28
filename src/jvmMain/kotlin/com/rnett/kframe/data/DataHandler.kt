package com.rnett.kframe.data

/*
import kotlinx.serialization.KSerializer
import kotlin.reflect.KClass

actual abstract class DataHandler<D, I> actual constructor(
    actual val dataClass: KClass<D>,
    actual val serializer: KSerializer<D>,
    actual val idSerializer: KSerializer<I>
) where D : Data<*, I>, D : Any {
    init {
        DataHandlers.addHandler(dataClass, this)
    }

    actual fun save(data: D) = _save(data)
    actual fun update(data: D) = _update(data)
    actual fun load(id: I): D = _load(id)
    actual fun delete(data: D) = _delete(data)
    actual fun create(args: List<String>): D = _create(args)
    actual fun all(): List<D> = _all()

    protected abstract fun _save(data: D)
    protected abstract fun _update(data: D)
    protected abstract fun _load(id: I): D
    protected abstract fun _delete(data: D)
    protected abstract fun _create(args: List<String>): D
    protected abstract fun _all(): List<D>
}
        */
