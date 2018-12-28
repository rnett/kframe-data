package com.rnett.kframe.data

/*
import kotlinx.serialization.KSerializer
import kotlin.reflect.KClass

expect abstract class DataHandler<D, I> (
    dataClass: KClass<D>,
    serializer: KSerializer<D>,
    idSerializer: KSerializer<I>
) where D: Data<*, I>, D : Any {

    val dataClass: KClass<D>
    val serializer: KSerializer<D>
    val idSerializer: KSerializer<I>

    fun save(data: D)
    fun update(data: D)
    fun load(id: I): D
    fun delete(data: D)
    fun create(args: List<String>): D
    fun all(): List<D>

}
fun <D, I> DataHandler<D, I>.create(vararg args: String) where D: Data<*, I>, D : Any = create(args.toList())

val <D, I> DataHandler<D, I>.className where D: Data<*, I>, D : Any get() = dataClass.qualifiedName!!

interface Data<Self : Data<Self, I>, I>{

    val handler : DataHandler<Self, I>

    fun save() = handler.save(this as Self)
    fun update() = handler.update(this as Self)
    fun delete() = handler.delete(this as Self)
}

interface StaticData : Data<StaticData, Unit>{
}

abstract class DataCompanion<D, I>(val handler: DataHandler<D, I>) where D: Data<*, I>, D : Any {
    fun save(data: D) = handler.save(data)
    fun update(data: D) = handler.update(data)
    fun load(id: I): D = handler.load(id)
    fun delete(data: D) = handler.delete(data)
    fun create(args: List<String>): D = handler.create(args)
    fun all(): List<D> = handler.all()
}

*/
