package com.rnett.kframe.data

/*
@ExperimentalContracts
fun forArgs(n: Int) = advancedBuildString{

    val params = (1..n+1).toList()

    +"data class Endpoint${n + 1}<R : Any, ${params.joinToString(", ") { "T$it: Any" }}>(val method${n+1}: KFunction<R>,"

    for(i in (1..n+1)){
        +"                                       val param${i}Serializer: KSerializer<T$i>?,"
    }
    -"                                       override val resultSerializer: KSerializer<R>?) : Endpoint<R>(method${n+1})"
    codeBlock {
        +"fun serializeParams(${params.joinToString(", ") { "param$it: T$it"  } }) = listOf("
        params.forEach {
            +"\tCBOR.dumps(param${it}Serializer ?: EndpointManager.getSerializer(), param$it)${if(it != n+1)"," else ""}"
        }
        +")\n"

        +"override fun serializeParams(params: List<Any>): List<String> = serializeParams("
        params.forEach {
            +"\tparams[${it - 1}] as T$it${if(it != n+1)"," else ""}"
        }
        +")\n"

        +"fun deserializeParams(${params.joinToString(", ") { "param$it: String" }}) = listOf<Any>("
        params.forEach {
            +"\tCBOR.loads(param${it}Serializer ?: EndpointManager.getSerializer(), param$it)${if(it != n+1)"," else ""}"
        }
        +")\n"

        +"override fun deserializeParams(params: List<String>): List<Any> = deserializeParams(${params.joinToString(", ") { "params[${it - 1}]" }})"
    }
    +"fun <R : Any, ${params.joinToString(", ") { "T$it: Any" }}> EndpointManager.addEndpoint("
    +"\tmethod: KFunction<R>,"
    +"\tresultSerializer: KSerializer<R>? = null,"
    params.forEach {
        +"\tparam${it}Serializer: KSerializer<T$it>? = null${if(it != n+1)"," else ""}"
    }
    +") = addEndpoint(Endpoint${n+1}(method, ${params.joinToString(", ") { "param${it}Serializer" }}, resultSerializer))"
    +"\n"
}

@ExperimentalContracts
fun main(args: Array<String>) {
    (0..11).forEach {
        println(forArgs(it))
    }
}
        */
