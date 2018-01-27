package com.palzzak.blur.util

import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlinx.coroutines.experimental.newSingleThreadContext
import javax.inject.Singleton
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by jaeyoonyoo on 2018. 1. 28..
 */
@Singleton
class CoroutineContexts(private val diskIO: CoroutineContext, private val networkIO: CoroutineContext) {
    fun diskIO() = diskIO

    fun networkIO() = networkIO

    companion object {
        val THREAD_COUNT = 3

        fun getNewSingleThreadContext(name: String) = newSingleThreadContext(name)

        fun getNewFixedThreadPoolContext(count: Int, name: String) = newFixedThreadPoolContext(count, name)
    }
}
