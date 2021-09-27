package com.leskov.universal_tasker.utils

import com.leskov.universal_tasker.domain.response.NetworkResult
import kotlinx.coroutines.flow.*


/**
 *  Created by Android Studio on 8/11/2021 9:13 AM
 *  Developer: Sergey Leskov
 */

inline fun <ResultType, RequestType> networkBoundResult(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(NetworkResult.Loading(data))

        try {
            saveFetchResult(fetch())
            query().map { NetworkResult.Success(it) }
        } catch (throwable: Throwable) {
            query().map { NetworkResult.Error(throwable, it) }
        }
    } else {
        query().map { NetworkResult.Success(it) }
    }

    emitAll(flow)
}