package com.rnett.kframe.data

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlin.reflect.KFunction


object EndpointManager {

    var serverUrl = ""

    private val _endpoints = mutableMapOf<KFunction<*>, Int>()

    val endpoints: Map<KFunction<*>, Int> get() = _endpoints.toMap()


    private val _endpointsById = mutableMapOf<Int, Endpoint<*>>()

    val endpointsById get() = _endpointsById.toMap()


    private val _serializers = mutableListOf<KSerializer<*>>()

    val serializers: List<KSerializer<*>> get() = _serializers.toList()

    fun <T : Any> getSerializer(): KSerializer<T> =
        serializers.mapNotNull { it as? KSerializer<T> }.firstOrNull()
            ?: throw IllegalArgumentException("No Serializer found for type")

    private var nextId = 0

    fun <R : Any> addEndpoint(endpoint: Endpoint<R>) {
        _endpointsById[nextId] = endpoint
        _endpoints[endpoint.method] = nextId
        nextId++
    }
}

@Serializable
data class EndpointData(val params: List<String>, val endpointId: Int)
