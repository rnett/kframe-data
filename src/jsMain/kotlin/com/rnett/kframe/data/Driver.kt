package com.rnett.kframe.data

/*
import com.rnett.kframe.data.DataMethod.*
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.http.HttpMethod
import io.ktor.http.takeFrom
import kotlinx.serialization.KSerializer
import kotlinx.serialization.cbor.CBOR
import kotlinx.serialization.dumps
import kotlinx.serialization.loads

internal suspend fun sendKFrameRequest(method: DataMethod, klassName: String, body: String = "", params: Map<String, String> = emptyMap()) = HttpClient().request<String> {
    this.method = KFrameDataRequest
    this.parameter("method", method.name)
    this.parameter("klassName", klassName)
    params.forEach {
        parameter(it.key, it.value)
    }

    if(body != ""){
        this.body = body
    }

    if(KFrameDataServer.url.isBlank())
        throw RuntimeException("KFrameDataServer.serverUrl must be set")

    this.url(KFrameDataServer.url)
}*/