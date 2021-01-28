package com.example.flowbugios.shared

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class Interactor(
    private val goodView: GoodViewable?, // set just this one for working behavior
    private val badView: BadViewable?, // set just this one for breaking behavior
) {
    private lateinit var scope: CoroutineScope

    @OptIn(InternalCoroutinesApi::class)
    fun attach() {
        scope = MainScope()

        val scopeHash = scope.hashCode()
        println("attached with                                            $scopeHash")
        scope.launch(Dispatchers.Main.immediate) {
            val events = badView ?: goodView?.events
            events!!.collect {
                println("COLLECT INVOKED in scope $scopeHash")
                detach()
                attach()
            }
        }.invokeOnCompletion {
            println("COMPLETED in scope $scopeHash")
        }
    }

    fun detach() {
        scope.cancel()
    }
}
