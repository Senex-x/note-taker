package com.senex.notetaker.mvi

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Common interface of asynchronous request state
 *
 * @param R - result of asynchronous request
 */
sealed interface AsyncResult<out R> {

    interface Loading<out R> : AsyncResult<R>

    interface Success<out R> : AsyncResult<R> {
        val value: R
    }

    interface Failure<out R> : AsyncResult<R> {
        val error: Throwable
    }
}

/**
 * Function to call asynchronous request and get [AsyncResult]<[R]>
 *
 * @param R - result of asynchronous request
 */
@SuppressWarnings("TooGenericExceptionCaught")
fun <R> asyncResultFlow(
    block: suspend () -> R,
): Flow<AsyncResult<R>> {
    return flow {
        emit(loadingAsyncResult())

        try {
            val result = block()
            emit(successAsyncResult(result = result))
        } catch (c: CancellationException) {
            throw c
        } catch (error: Throwable) {
            emit(failureAsyncResult(error = error))
        }
    }
}

/**
 * Function to call [asyncResultFlow] request with specific [CoroutineContext]
 *
 * @param R - result of asynchronous request
 */
fun <R> asyncResultFlow(
    context: CoroutineContext,
    block: suspend () -> R,
): Flow<AsyncResult<R>> {
    return asyncResultFlow {
        withContext(context = context) {
            block()
        }
    }
}

private val AsyncResultLoading: AsyncResult.Loading<Nothing> = object :
    AsyncResult.Loading<Nothing> {}

private fun <R> loadingAsyncResult(): AsyncResult.Loading<R> = AsyncResultLoading

private fun <R> successAsyncResult(result: R): AsyncResult.Success<R> {
    return object : AsyncResult.Success<R> {
        override val value: R = result
    }
}

private fun <R> failureAsyncResult(error: Throwable): AsyncResult.Failure<R> {
    return object : AsyncResult.Failure<R> {
        override val error: Throwable = error
    }
}
