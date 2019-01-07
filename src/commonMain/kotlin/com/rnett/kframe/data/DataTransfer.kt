package com.rnett.kframe.data


fun <T> dataTransfer(block: () -> T): T {
    EndpointManager.inDataTransfer = true
    val temp = block()
    EndpointManager.inDataTransfer = false

    return temp
}