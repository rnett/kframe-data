package com.rnett.kframe.data

import io.ktor.http.HttpMethod
import kotlinx.serialization.KSerializer
import kotlinx.serialization.cbor.CBOR
import kotlinx.serialization.dumps
import kotlinx.serialization.loads
import kotlin.reflect.KFunction

val KFrameDataRequest = HttpMethod("KFrameData")

sealed class Endpoint<R : Any>(val method: KFunction<R>) {
    abstract fun serializeParams(params: List<Any>): List<String>
    abstract fun deserializeParams(params: List<String>): List<Any>
    abstract val resultSerializer: KSerializer<R>?
    fun deserializeResult(res: String) = CBOR.loads(resultSerializer ?: EndpointManager.getSerializer(), res)
    fun serializeResult(res: R) = CBOR.dumps(resultSerializer ?: EndpointManager.getSerializer(), res)
}

data class Endpoint0<R : Any>(
    val method1: KFunction<R>,
    override val resultSerializer: KSerializer<R>?
) : Endpoint<R>(method1) {
    fun serializeParams() = listOf<String>()

    override fun serializeParams(params: List<Any>): List<String> = serializeParams()

    fun deserializeParams() = listOf<Any>()

    override fun deserializeParams(params: List<String>): List<Any> = deserializeParams()
}

fun <R : Any> EndpointManager.addEndpoint(
    method: KFunction<R>,
    resultSerializer: KSerializer<R>? = null
) = addEndpoint(Endpoint0(method, resultSerializer))

data class Endpoint1<R : Any, T1 : Any>(
    val method1: KFunction<R>,
    val param1Serializer: KSerializer<T1>?,
    override val resultSerializer: KSerializer<R>?
) : Endpoint<R>(method1) {
    fun serializeParams(param1: T1) = listOf(
        CBOR.dumps(param1Serializer ?: EndpointManager.getSerializer(), param1)
    )

    override fun serializeParams(params: List<Any>): List<String> = serializeParams(
        params[0] as T1
    )

    fun deserializeParams(param1: String) = listOf<Any>(
        CBOR.loads(param1Serializer ?: EndpointManager.getSerializer(), param1)
    )

    override fun deserializeParams(params: List<String>): List<Any> = deserializeParams(params[0])
}

fun <R : Any, T1 : Any> EndpointManager.addEndpoint(
    method: KFunction<R>,
    resultSerializer: KSerializer<R>? = null,
    param1Serializer: KSerializer<T1>? = null
) = addEndpoint(Endpoint1(method, param1Serializer, resultSerializer))


data class Endpoint2<R : Any, T1 : Any, T2 : Any>(
    val method2: KFunction<R>,
    val param1Serializer: KSerializer<T1>?,
    val param2Serializer: KSerializer<T2>?,
    override val resultSerializer: KSerializer<R>?
) : Endpoint<R>(method2) {
    fun serializeParams(param1: T1, param2: T2) = listOf(
        CBOR.dumps(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.dumps(param2Serializer ?: EndpointManager.getSerializer(), param2)
    )

    override fun serializeParams(params: List<Any>): List<String> = serializeParams(
        params[0] as T1,
        params[1] as T2
    )

    fun deserializeParams(param1: String, param2: String) = listOf<Any>(
        CBOR.loads(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.loads(param2Serializer ?: EndpointManager.getSerializer(), param2)
    )

    override fun deserializeParams(params: List<String>): List<Any> = deserializeParams(params[0], params[1])
}

fun <R : Any, T1 : Any, T2 : Any> EndpointManager.addEndpoint(
    method: KFunction<R>,
    resultSerializer: KSerializer<R>? = null,
    param1Serializer: KSerializer<T1>? = null,
    param2Serializer: KSerializer<T2>? = null
) = addEndpoint(Endpoint2(method, param1Serializer, param2Serializer, resultSerializer))


data class Endpoint3<R : Any, T1 : Any, T2 : Any, T3 : Any>(
    val method3: KFunction<R>,
    val param1Serializer: KSerializer<T1>?,
    val param2Serializer: KSerializer<T2>?,
    val param3Serializer: KSerializer<T3>?,
    override val resultSerializer: KSerializer<R>?
) : Endpoint<R>(method3) {
    fun serializeParams(param1: T1, param2: T2, param3: T3) = listOf(
        CBOR.dumps(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.dumps(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.dumps(param3Serializer ?: EndpointManager.getSerializer(), param3)
    )

    override fun serializeParams(params: List<Any>): List<String> = serializeParams(
        params[0] as T1,
        params[1] as T2,
        params[2] as T3
    )

    fun deserializeParams(param1: String, param2: String, param3: String) = listOf<Any>(
        CBOR.loads(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.loads(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.loads(param3Serializer ?: EndpointManager.getSerializer(), param3)
    )

    override fun deserializeParams(params: List<String>): List<Any> = deserializeParams(params[0], params[1], params[2])
}

fun <R : Any, T1 : Any, T2 : Any, T3 : Any> EndpointManager.addEndpoint(
    method: KFunction<R>,
    resultSerializer: KSerializer<R>? = null,
    param1Serializer: KSerializer<T1>? = null,
    param2Serializer: KSerializer<T2>? = null,
    param3Serializer: KSerializer<T3>? = null
) = addEndpoint(Endpoint3(method, param1Serializer, param2Serializer, param3Serializer, resultSerializer))


data class Endpoint4<R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any>(
    val method4: KFunction<R>,
    val param1Serializer: KSerializer<T1>?,
    val param2Serializer: KSerializer<T2>?,
    val param3Serializer: KSerializer<T3>?,
    val param4Serializer: KSerializer<T4>?,
    override val resultSerializer: KSerializer<R>?
) : Endpoint<R>(method4) {
    fun serializeParams(param1: T1, param2: T2, param3: T3, param4: T4) = listOf(
        CBOR.dumps(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.dumps(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.dumps(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.dumps(param4Serializer ?: EndpointManager.getSerializer(), param4)
    )

    override fun serializeParams(params: List<Any>): List<String> = serializeParams(
        params[0] as T1,
        params[1] as T2,
        params[2] as T3,
        params[3] as T4
    )

    fun deserializeParams(param1: String, param2: String, param3: String, param4: String) = listOf<Any>(
        CBOR.loads(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.loads(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.loads(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.loads(param4Serializer ?: EndpointManager.getSerializer(), param4)
    )

    override fun deserializeParams(params: List<String>): List<Any> =
        deserializeParams(params[0], params[1], params[2], params[3])
}

fun <R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any> EndpointManager.addEndpoint(
    method: KFunction<R>,
    resultSerializer: KSerializer<R>? = null,
    param1Serializer: KSerializer<T1>? = null,
    param2Serializer: KSerializer<T2>? = null,
    param3Serializer: KSerializer<T3>? = null,
    param4Serializer: KSerializer<T4>? = null
) = addEndpoint(
    Endpoint4(
        method,
        param1Serializer,
        param2Serializer,
        param3Serializer,
        param4Serializer,
        resultSerializer
    )
)


data class Endpoint5<R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any>(
    val method5: KFunction<R>,
    val param1Serializer: KSerializer<T1>?,
    val param2Serializer: KSerializer<T2>?,
    val param3Serializer: KSerializer<T3>?,
    val param4Serializer: KSerializer<T4>?,
    val param5Serializer: KSerializer<T5>?,
    override val resultSerializer: KSerializer<R>?
) : Endpoint<R>(method5) {
    fun serializeParams(param1: T1, param2: T2, param3: T3, param4: T4, param5: T5) = listOf(
        CBOR.dumps(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.dumps(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.dumps(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.dumps(param4Serializer ?: EndpointManager.getSerializer(), param4),
        CBOR.dumps(param5Serializer ?: EndpointManager.getSerializer(), param5)
    )

    override fun serializeParams(params: List<Any>): List<String> = serializeParams(
        params[0] as T1,
        params[1] as T2,
        params[2] as T3,
        params[3] as T4,
        params[4] as T5
    )

    fun deserializeParams(param1: String, param2: String, param3: String, param4: String, param5: String) = listOf<Any>(
        CBOR.loads(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.loads(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.loads(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.loads(param4Serializer ?: EndpointManager.getSerializer(), param4),
        CBOR.loads(param5Serializer ?: EndpointManager.getSerializer(), param5)
    )

    override fun deserializeParams(params: List<String>): List<Any> =
        deserializeParams(params[0], params[1], params[2], params[3], params[4])
}

fun <R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any> EndpointManager.addEndpoint(
    method: KFunction<R>,
    resultSerializer: KSerializer<R>? = null,
    param1Serializer: KSerializer<T1>? = null,
    param2Serializer: KSerializer<T2>? = null,
    param3Serializer: KSerializer<T3>? = null,
    param4Serializer: KSerializer<T4>? = null,
    param5Serializer: KSerializer<T5>? = null
) = addEndpoint(
    Endpoint5(
        method,
        param1Serializer,
        param2Serializer,
        param3Serializer,
        param4Serializer,
        param5Serializer,
        resultSerializer
    )
)


data class Endpoint6<R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any>(
    val method6: KFunction<R>,
    val param1Serializer: KSerializer<T1>?,
    val param2Serializer: KSerializer<T2>?,
    val param3Serializer: KSerializer<T3>?,
    val param4Serializer: KSerializer<T4>?,
    val param5Serializer: KSerializer<T5>?,
    val param6Serializer: KSerializer<T6>?,
    override val resultSerializer: KSerializer<R>?
) : Endpoint<R>(method6) {
    fun serializeParams(param1: T1, param2: T2, param3: T3, param4: T4, param5: T5, param6: T6) = listOf(
        CBOR.dumps(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.dumps(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.dumps(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.dumps(param4Serializer ?: EndpointManager.getSerializer(), param4),
        CBOR.dumps(param5Serializer ?: EndpointManager.getSerializer(), param5),
        CBOR.dumps(param6Serializer ?: EndpointManager.getSerializer(), param6)
    )

    override fun serializeParams(params: List<Any>): List<String> = serializeParams(
        params[0] as T1,
        params[1] as T2,
        params[2] as T3,
        params[3] as T4,
        params[4] as T5,
        params[5] as T6
    )

    fun deserializeParams(
        param1: String,
        param2: String,
        param3: String,
        param4: String,
        param5: String,
        param6: String
    ) = listOf<Any>(
        CBOR.loads(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.loads(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.loads(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.loads(param4Serializer ?: EndpointManager.getSerializer(), param4),
        CBOR.loads(param5Serializer ?: EndpointManager.getSerializer(), param5),
        CBOR.loads(param6Serializer ?: EndpointManager.getSerializer(), param6)
    )

    override fun deserializeParams(params: List<String>): List<Any> =
        deserializeParams(params[0], params[1], params[2], params[3], params[4], params[5])
}

fun <R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any> EndpointManager.addEndpoint(
    method: KFunction<R>,
    resultSerializer: KSerializer<R>? = null,
    param1Serializer: KSerializer<T1>? = null,
    param2Serializer: KSerializer<T2>? = null,
    param3Serializer: KSerializer<T3>? = null,
    param4Serializer: KSerializer<T4>? = null,
    param5Serializer: KSerializer<T5>? = null,
    param6Serializer: KSerializer<T6>? = null
) = addEndpoint(
    Endpoint6(
        method,
        param1Serializer,
        param2Serializer,
        param3Serializer,
        param4Serializer,
        param5Serializer,
        param6Serializer,
        resultSerializer
    )
)


data class Endpoint7<R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any>(
    val method7: KFunction<R>,
    val param1Serializer: KSerializer<T1>?,
    val param2Serializer: KSerializer<T2>?,
    val param3Serializer: KSerializer<T3>?,
    val param4Serializer: KSerializer<T4>?,
    val param5Serializer: KSerializer<T5>?,
    val param6Serializer: KSerializer<T6>?,
    val param7Serializer: KSerializer<T7>?,
    override val resultSerializer: KSerializer<R>?
) : Endpoint<R>(method7) {
    fun serializeParams(param1: T1, param2: T2, param3: T3, param4: T4, param5: T5, param6: T6, param7: T7) = listOf(
        CBOR.dumps(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.dumps(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.dumps(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.dumps(param4Serializer ?: EndpointManager.getSerializer(), param4),
        CBOR.dumps(param5Serializer ?: EndpointManager.getSerializer(), param5),
        CBOR.dumps(param6Serializer ?: EndpointManager.getSerializer(), param6),
        CBOR.dumps(param7Serializer ?: EndpointManager.getSerializer(), param7)
    )

    override fun serializeParams(params: List<Any>): List<String> = serializeParams(
        params[0] as T1,
        params[1] as T2,
        params[2] as T3,
        params[3] as T4,
        params[4] as T5,
        params[5] as T6,
        params[6] as T7
    )

    fun deserializeParams(
        param1: String,
        param2: String,
        param3: String,
        param4: String,
        param5: String,
        param6: String,
        param7: String
    ) = listOf<Any>(
        CBOR.loads(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.loads(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.loads(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.loads(param4Serializer ?: EndpointManager.getSerializer(), param4),
        CBOR.loads(param5Serializer ?: EndpointManager.getSerializer(), param5),
        CBOR.loads(param6Serializer ?: EndpointManager.getSerializer(), param6),
        CBOR.loads(param7Serializer ?: EndpointManager.getSerializer(), param7)
    )

    override fun deserializeParams(params: List<String>): List<Any> =
        deserializeParams(params[0], params[1], params[2], params[3], params[4], params[5], params[6])
}

fun <R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any> EndpointManager.addEndpoint(
    method: KFunction<R>,
    resultSerializer: KSerializer<R>? = null,
    param1Serializer: KSerializer<T1>? = null,
    param2Serializer: KSerializer<T2>? = null,
    param3Serializer: KSerializer<T3>? = null,
    param4Serializer: KSerializer<T4>? = null,
    param5Serializer: KSerializer<T5>? = null,
    param6Serializer: KSerializer<T6>? = null,
    param7Serializer: KSerializer<T7>? = null
) = addEndpoint(
    Endpoint7(
        method,
        param1Serializer,
        param2Serializer,
        param3Serializer,
        param4Serializer,
        param5Serializer,
        param6Serializer,
        param7Serializer,
        resultSerializer
    )
)


data class Endpoint8<R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any>(
    val method8: KFunction<R>,
    val param1Serializer: KSerializer<T1>?,
    val param2Serializer: KSerializer<T2>?,
    val param3Serializer: KSerializer<T3>?,
    val param4Serializer: KSerializer<T4>?,
    val param5Serializer: KSerializer<T5>?,
    val param6Serializer: KSerializer<T6>?,
    val param7Serializer: KSerializer<T7>?,
    val param8Serializer: KSerializer<T8>?,
    override val resultSerializer: KSerializer<R>?
) : Endpoint<R>(method8) {
    fun serializeParams(
        param1: T1,
        param2: T2,
        param3: T3,
        param4: T4,
        param5: T5,
        param6: T6,
        param7: T7,
        param8: T8
    ) = listOf(
        CBOR.dumps(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.dumps(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.dumps(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.dumps(param4Serializer ?: EndpointManager.getSerializer(), param4),
        CBOR.dumps(param5Serializer ?: EndpointManager.getSerializer(), param5),
        CBOR.dumps(param6Serializer ?: EndpointManager.getSerializer(), param6),
        CBOR.dumps(param7Serializer ?: EndpointManager.getSerializer(), param7),
        CBOR.dumps(param8Serializer ?: EndpointManager.getSerializer(), param8)
    )

    override fun serializeParams(params: List<Any>): List<String> = serializeParams(
        params[0] as T1,
        params[1] as T2,
        params[2] as T3,
        params[3] as T4,
        params[4] as T5,
        params[5] as T6,
        params[6] as T7,
        params[7] as T8
    )

    fun deserializeParams(
        param1: String,
        param2: String,
        param3: String,
        param4: String,
        param5: String,
        param6: String,
        param7: String,
        param8: String
    ) = listOf<Any>(
        CBOR.loads(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.loads(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.loads(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.loads(param4Serializer ?: EndpointManager.getSerializer(), param4),
        CBOR.loads(param5Serializer ?: EndpointManager.getSerializer(), param5),
        CBOR.loads(param6Serializer ?: EndpointManager.getSerializer(), param6),
        CBOR.loads(param7Serializer ?: EndpointManager.getSerializer(), param7),
        CBOR.loads(param8Serializer ?: EndpointManager.getSerializer(), param8)
    )

    override fun deserializeParams(params: List<String>): List<Any> =
        deserializeParams(params[0], params[1], params[2], params[3], params[4], params[5], params[6], params[7])
}

fun <R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any> EndpointManager.addEndpoint(
    method: KFunction<R>,
    resultSerializer: KSerializer<R>? = null,
    param1Serializer: KSerializer<T1>? = null,
    param2Serializer: KSerializer<T2>? = null,
    param3Serializer: KSerializer<T3>? = null,
    param4Serializer: KSerializer<T4>? = null,
    param5Serializer: KSerializer<T5>? = null,
    param6Serializer: KSerializer<T6>? = null,
    param7Serializer: KSerializer<T7>? = null,
    param8Serializer: KSerializer<T8>? = null
) = addEndpoint(
    Endpoint8(
        method,
        param1Serializer,
        param2Serializer,
        param3Serializer,
        param4Serializer,
        param5Serializer,
        param6Serializer,
        param7Serializer,
        param8Serializer,
        resultSerializer
    )
)


data class Endpoint9<R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, T9 : Any>(
    val method9: KFunction<R>,
    val param1Serializer: KSerializer<T1>?,
    val param2Serializer: KSerializer<T2>?,
    val param3Serializer: KSerializer<T3>?,
    val param4Serializer: KSerializer<T4>?,
    val param5Serializer: KSerializer<T5>?,
    val param6Serializer: KSerializer<T6>?,
    val param7Serializer: KSerializer<T7>?,
    val param8Serializer: KSerializer<T8>?,
    val param9Serializer: KSerializer<T9>?,
    override val resultSerializer: KSerializer<R>?
) : Endpoint<R>(method9) {
    fun serializeParams(
        param1: T1,
        param2: T2,
        param3: T3,
        param4: T4,
        param5: T5,
        param6: T6,
        param7: T7,
        param8: T8,
        param9: T9
    ) = listOf(
        CBOR.dumps(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.dumps(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.dumps(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.dumps(param4Serializer ?: EndpointManager.getSerializer(), param4),
        CBOR.dumps(param5Serializer ?: EndpointManager.getSerializer(), param5),
        CBOR.dumps(param6Serializer ?: EndpointManager.getSerializer(), param6),
        CBOR.dumps(param7Serializer ?: EndpointManager.getSerializer(), param7),
        CBOR.dumps(param8Serializer ?: EndpointManager.getSerializer(), param8),
        CBOR.dumps(param9Serializer ?: EndpointManager.getSerializer(), param9)
    )

    override fun serializeParams(params: List<Any>): List<String> = serializeParams(
        params[0] as T1,
        params[1] as T2,
        params[2] as T3,
        params[3] as T4,
        params[4] as T5,
        params[5] as T6,
        params[6] as T7,
        params[7] as T8,
        params[8] as T9
    )

    fun deserializeParams(
        param1: String,
        param2: String,
        param3: String,
        param4: String,
        param5: String,
        param6: String,
        param7: String,
        param8: String,
        param9: String
    ) = listOf<Any>(
        CBOR.loads(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.loads(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.loads(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.loads(param4Serializer ?: EndpointManager.getSerializer(), param4),
        CBOR.loads(param5Serializer ?: EndpointManager.getSerializer(), param5),
        CBOR.loads(param6Serializer ?: EndpointManager.getSerializer(), param6),
        CBOR.loads(param7Serializer ?: EndpointManager.getSerializer(), param7),
        CBOR.loads(param8Serializer ?: EndpointManager.getSerializer(), param8),
        CBOR.loads(param9Serializer ?: EndpointManager.getSerializer(), param9)
    )

    override fun deserializeParams(params: List<String>): List<Any> = deserializeParams(
        params[0],
        params[1],
        params[2],
        params[3],
        params[4],
        params[5],
        params[6],
        params[7],
        params[8]
    )
}

fun <R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, T9 : Any> EndpointManager.addEndpoint(
    method: KFunction<R>,
    resultSerializer: KSerializer<R>? = null,
    param1Serializer: KSerializer<T1>? = null,
    param2Serializer: KSerializer<T2>? = null,
    param3Serializer: KSerializer<T3>? = null,
    param4Serializer: KSerializer<T4>? = null,
    param5Serializer: KSerializer<T5>? = null,
    param6Serializer: KSerializer<T6>? = null,
    param7Serializer: KSerializer<T7>? = null,
    param8Serializer: KSerializer<T8>? = null,
    param9Serializer: KSerializer<T9>? = null
) = addEndpoint(
    Endpoint9(
        method,
        param1Serializer,
        param2Serializer,
        param3Serializer,
        param4Serializer,
        param5Serializer,
        param6Serializer,
        param7Serializer,
        param8Serializer,
        param9Serializer,
        resultSerializer
    )
)


data class Endpoint10<R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, T9 : Any, T10 : Any>(
    val method10: KFunction<R>,
    val param1Serializer: KSerializer<T1>?,
    val param2Serializer: KSerializer<T2>?,
    val param3Serializer: KSerializer<T3>?,
    val param4Serializer: KSerializer<T4>?,
    val param5Serializer: KSerializer<T5>?,
    val param6Serializer: KSerializer<T6>?,
    val param7Serializer: KSerializer<T7>?,
    val param8Serializer: KSerializer<T8>?,
    val param9Serializer: KSerializer<T9>?,
    val param10Serializer: KSerializer<T10>?,
    override val resultSerializer: KSerializer<R>?
) : Endpoint<R>(method10) {
    fun serializeParams(
        param1: T1,
        param2: T2,
        param3: T3,
        param4: T4,
        param5: T5,
        param6: T6,
        param7: T7,
        param8: T8,
        param9: T9,
        param10: T10
    ) = listOf(
        CBOR.dumps(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.dumps(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.dumps(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.dumps(param4Serializer ?: EndpointManager.getSerializer(), param4),
        CBOR.dumps(param5Serializer ?: EndpointManager.getSerializer(), param5),
        CBOR.dumps(param6Serializer ?: EndpointManager.getSerializer(), param6),
        CBOR.dumps(param7Serializer ?: EndpointManager.getSerializer(), param7),
        CBOR.dumps(param8Serializer ?: EndpointManager.getSerializer(), param8),
        CBOR.dumps(param9Serializer ?: EndpointManager.getSerializer(), param9),
        CBOR.dumps(param10Serializer ?: EndpointManager.getSerializer(), param10)
    )

    override fun serializeParams(params: List<Any>): List<String> = serializeParams(
        params[0] as T1,
        params[1] as T2,
        params[2] as T3,
        params[3] as T4,
        params[4] as T5,
        params[5] as T6,
        params[6] as T7,
        params[7] as T8,
        params[8] as T9,
        params[9] as T10
    )

    fun deserializeParams(
        param1: String,
        param2: String,
        param3: String,
        param4: String,
        param5: String,
        param6: String,
        param7: String,
        param8: String,
        param9: String,
        param10: String
    ) = listOf<Any>(
        CBOR.loads(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.loads(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.loads(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.loads(param4Serializer ?: EndpointManager.getSerializer(), param4),
        CBOR.loads(param5Serializer ?: EndpointManager.getSerializer(), param5),
        CBOR.loads(param6Serializer ?: EndpointManager.getSerializer(), param6),
        CBOR.loads(param7Serializer ?: EndpointManager.getSerializer(), param7),
        CBOR.loads(param8Serializer ?: EndpointManager.getSerializer(), param8),
        CBOR.loads(param9Serializer ?: EndpointManager.getSerializer(), param9),
        CBOR.loads(param10Serializer ?: EndpointManager.getSerializer(), param10)
    )

    override fun deserializeParams(params: List<String>): List<Any> = deserializeParams(
        params[0],
        params[1],
        params[2],
        params[3],
        params[4],
        params[5],
        params[6],
        params[7],
        params[8],
        params[9]
    )
}

fun <R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, T9 : Any, T10 : Any> EndpointManager.addEndpoint(
    method: KFunction<R>,
    resultSerializer: KSerializer<R>? = null,
    param1Serializer: KSerializer<T1>? = null,
    param2Serializer: KSerializer<T2>? = null,
    param3Serializer: KSerializer<T3>? = null,
    param4Serializer: KSerializer<T4>? = null,
    param5Serializer: KSerializer<T5>? = null,
    param6Serializer: KSerializer<T6>? = null,
    param7Serializer: KSerializer<T7>? = null,
    param8Serializer: KSerializer<T8>? = null,
    param9Serializer: KSerializer<T9>? = null,
    param10Serializer: KSerializer<T10>? = null
) = addEndpoint(
    Endpoint10(
        method,
        param1Serializer,
        param2Serializer,
        param3Serializer,
        param4Serializer,
        param5Serializer,
        param6Serializer,
        param7Serializer,
        param8Serializer,
        param9Serializer,
        param10Serializer,
        resultSerializer
    )
)


data class Endpoint11<R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, T9 : Any, T10 : Any, T11 : Any>(
    val method11: KFunction<R>,
    val param1Serializer: KSerializer<T1>?,
    val param2Serializer: KSerializer<T2>?,
    val param3Serializer: KSerializer<T3>?,
    val param4Serializer: KSerializer<T4>?,
    val param5Serializer: KSerializer<T5>?,
    val param6Serializer: KSerializer<T6>?,
    val param7Serializer: KSerializer<T7>?,
    val param8Serializer: KSerializer<T8>?,
    val param9Serializer: KSerializer<T9>?,
    val param10Serializer: KSerializer<T10>?,
    val param11Serializer: KSerializer<T11>?,
    override val resultSerializer: KSerializer<R>?
) : Endpoint<R>(method11) {
    fun serializeParams(
        param1: T1,
        param2: T2,
        param3: T3,
        param4: T4,
        param5: T5,
        param6: T6,
        param7: T7,
        param8: T8,
        param9: T9,
        param10: T10,
        param11: T11
    ) = listOf(
        CBOR.dumps(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.dumps(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.dumps(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.dumps(param4Serializer ?: EndpointManager.getSerializer(), param4),
        CBOR.dumps(param5Serializer ?: EndpointManager.getSerializer(), param5),
        CBOR.dumps(param6Serializer ?: EndpointManager.getSerializer(), param6),
        CBOR.dumps(param7Serializer ?: EndpointManager.getSerializer(), param7),
        CBOR.dumps(param8Serializer ?: EndpointManager.getSerializer(), param8),
        CBOR.dumps(param9Serializer ?: EndpointManager.getSerializer(), param9),
        CBOR.dumps(param10Serializer ?: EndpointManager.getSerializer(), param10),
        CBOR.dumps(param11Serializer ?: EndpointManager.getSerializer(), param11)
    )

    override fun serializeParams(params: List<Any>): List<String> = serializeParams(
        params[0] as T1,
        params[1] as T2,
        params[2] as T3,
        params[3] as T4,
        params[4] as T5,
        params[5] as T6,
        params[6] as T7,
        params[7] as T8,
        params[8] as T9,
        params[9] as T10,
        params[10] as T11
    )

    fun deserializeParams(
        param1: String,
        param2: String,
        param3: String,
        param4: String,
        param5: String,
        param6: String,
        param7: String,
        param8: String,
        param9: String,
        param10: String,
        param11: String
    ) = listOf<Any>(
        CBOR.loads(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.loads(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.loads(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.loads(param4Serializer ?: EndpointManager.getSerializer(), param4),
        CBOR.loads(param5Serializer ?: EndpointManager.getSerializer(), param5),
        CBOR.loads(param6Serializer ?: EndpointManager.getSerializer(), param6),
        CBOR.loads(param7Serializer ?: EndpointManager.getSerializer(), param7),
        CBOR.loads(param8Serializer ?: EndpointManager.getSerializer(), param8),
        CBOR.loads(param9Serializer ?: EndpointManager.getSerializer(), param9),
        CBOR.loads(param10Serializer ?: EndpointManager.getSerializer(), param10),
        CBOR.loads(param11Serializer ?: EndpointManager.getSerializer(), param11)
    )

    override fun deserializeParams(params: List<String>): List<Any> = deserializeParams(
        params[0],
        params[1],
        params[2],
        params[3],
        params[4],
        params[5],
        params[6],
        params[7],
        params[8],
        params[9],
        params[10]
    )
}

fun <R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, T9 : Any, T10 : Any, T11 : Any> EndpointManager.addEndpoint(
    method: KFunction<R>,
    resultSerializer: KSerializer<R>? = null,
    param1Serializer: KSerializer<T1>? = null,
    param2Serializer: KSerializer<T2>? = null,
    param3Serializer: KSerializer<T3>? = null,
    param4Serializer: KSerializer<T4>? = null,
    param5Serializer: KSerializer<T5>? = null,
    param6Serializer: KSerializer<T6>? = null,
    param7Serializer: KSerializer<T7>? = null,
    param8Serializer: KSerializer<T8>? = null,
    param9Serializer: KSerializer<T9>? = null,
    param10Serializer: KSerializer<T10>? = null,
    param11Serializer: KSerializer<T11>? = null
) = addEndpoint(
    Endpoint11(
        method,
        param1Serializer,
        param2Serializer,
        param3Serializer,
        param4Serializer,
        param5Serializer,
        param6Serializer,
        param7Serializer,
        param8Serializer,
        param9Serializer,
        param10Serializer,
        param11Serializer,
        resultSerializer
    )
)


data class Endpoint12<R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, T9 : Any, T10 : Any, T11 : Any, T12 : Any>(
    val method12: KFunction<R>,
    val param1Serializer: KSerializer<T1>?,
    val param2Serializer: KSerializer<T2>?,
    val param3Serializer: KSerializer<T3>?,
    val param4Serializer: KSerializer<T4>?,
    val param5Serializer: KSerializer<T5>?,
    val param6Serializer: KSerializer<T6>?,
    val param7Serializer: KSerializer<T7>?,
    val param8Serializer: KSerializer<T8>?,
    val param9Serializer: KSerializer<T9>?,
    val param10Serializer: KSerializer<T10>?,
    val param11Serializer: KSerializer<T11>?,
    val param12Serializer: KSerializer<T12>?,
    override val resultSerializer: KSerializer<R>?
) : Endpoint<R>(method12) {
    fun serializeParams(
        param1: T1,
        param2: T2,
        param3: T3,
        param4: T4,
        param5: T5,
        param6: T6,
        param7: T7,
        param8: T8,
        param9: T9,
        param10: T10,
        param11: T11,
        param12: T12
    ) = listOf(
        CBOR.dumps(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.dumps(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.dumps(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.dumps(param4Serializer ?: EndpointManager.getSerializer(), param4),
        CBOR.dumps(param5Serializer ?: EndpointManager.getSerializer(), param5),
        CBOR.dumps(param6Serializer ?: EndpointManager.getSerializer(), param6),
        CBOR.dumps(param7Serializer ?: EndpointManager.getSerializer(), param7),
        CBOR.dumps(param8Serializer ?: EndpointManager.getSerializer(), param8),
        CBOR.dumps(param9Serializer ?: EndpointManager.getSerializer(), param9),
        CBOR.dumps(param10Serializer ?: EndpointManager.getSerializer(), param10),
        CBOR.dumps(param11Serializer ?: EndpointManager.getSerializer(), param11),
        CBOR.dumps(param12Serializer ?: EndpointManager.getSerializer(), param12)
    )

    override fun serializeParams(params: List<Any>): List<String> = serializeParams(
        params[0] as T1,
        params[1] as T2,
        params[2] as T3,
        params[3] as T4,
        params[4] as T5,
        params[5] as T6,
        params[6] as T7,
        params[7] as T8,
        params[8] as T9,
        params[9] as T10,
        params[10] as T11,
        params[11] as T12
    )

    fun deserializeParams(
        param1: String,
        param2: String,
        param3: String,
        param4: String,
        param5: String,
        param6: String,
        param7: String,
        param8: String,
        param9: String,
        param10: String,
        param11: String,
        param12: String
    ) = listOf<Any>(
        CBOR.loads(param1Serializer ?: EndpointManager.getSerializer(), param1),
        CBOR.loads(param2Serializer ?: EndpointManager.getSerializer(), param2),
        CBOR.loads(param3Serializer ?: EndpointManager.getSerializer(), param3),
        CBOR.loads(param4Serializer ?: EndpointManager.getSerializer(), param4),
        CBOR.loads(param5Serializer ?: EndpointManager.getSerializer(), param5),
        CBOR.loads(param6Serializer ?: EndpointManager.getSerializer(), param6),
        CBOR.loads(param7Serializer ?: EndpointManager.getSerializer(), param7),
        CBOR.loads(param8Serializer ?: EndpointManager.getSerializer(), param8),
        CBOR.loads(param9Serializer ?: EndpointManager.getSerializer(), param9),
        CBOR.loads(param10Serializer ?: EndpointManager.getSerializer(), param10),
        CBOR.loads(param11Serializer ?: EndpointManager.getSerializer(), param11),
        CBOR.loads(param12Serializer ?: EndpointManager.getSerializer(), param12)
    )

    override fun deserializeParams(params: List<String>): List<Any> = deserializeParams(
        params[0],
        params[1],
        params[2],
        params[3],
        params[4],
        params[5],
        params[6],
        params[7],
        params[8],
        params[9],
        params[10],
        params[11]
    )
}

fun <R : Any, T1 : Any, T2 : Any, T3 : Any, T4 : Any, T5 : Any, T6 : Any, T7 : Any, T8 : Any, T9 : Any, T10 : Any, T11 : Any, T12 : Any> EndpointManager.addEndpoint(
    method: KFunction<R>,
    resultSerializer: KSerializer<R>? = null,
    param1Serializer: KSerializer<T1>? = null,
    param2Serializer: KSerializer<T2>? = null,
    param3Serializer: KSerializer<T3>? = null,
    param4Serializer: KSerializer<T4>? = null,
    param5Serializer: KSerializer<T5>? = null,
    param6Serializer: KSerializer<T6>? = null,
    param7Serializer: KSerializer<T7>? = null,
    param8Serializer: KSerializer<T8>? = null,
    param9Serializer: KSerializer<T9>? = null,
    param10Serializer: KSerializer<T10>? = null,
    param11Serializer: KSerializer<T11>? = null,
    param12Serializer: KSerializer<T12>? = null
) = addEndpoint(
    Endpoint12(
        method,
        param1Serializer,
        param2Serializer,
        param3Serializer,
        param4Serializer,
        param5Serializer,
        param6Serializer,
        param7Serializer,
        param8Serializer,
        param9Serializer,
        param10Serializer,
        param11Serializer,
        param12Serializer,
        resultSerializer
    )
)

