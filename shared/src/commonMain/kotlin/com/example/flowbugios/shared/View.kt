package com.example.flowbugios.shared

import kotlinx.coroutines.flow.*

// View Interfaces

interface GoodViewable {
    var events: Flow<Unit>
    fun emitEvent()
}

interface BadViewable: Flow<Unit> {
    fun emitEvent()
}

// Abstract View Classes

abstract class BadBaseView : AbstractFlow<Unit>() {

    private val sharedFlow = MutableSharedFlow<Unit>(extraBufferCapacity = 1)

    override suspend fun collectSafely(collector: FlowCollector<Unit>) {
        sharedFlow.collect { value -> collector.emit(value) }
    }

    fun emitEvent() {
        println("----------------------emit---------------------------")
        sharedFlow.tryEmit(Unit)
    }
}

abstract class BaseGoodView {

    private val sharedFlow = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    var events: Flow<Unit> = sharedFlow

    fun emitEvent() {
        println("----------------------emit---------------------------")
        sharedFlow.tryEmit(Unit)
    }
}
