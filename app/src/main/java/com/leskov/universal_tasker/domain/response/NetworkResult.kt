package com.leskov.universal_tasker.domain.response

/**
 *  Created by Android Studio on 8/7/2021 6:31 PM
 *  Developer: Sergey Leskov
 */

sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Loading<T>(data: T? = null) : NetworkResult<T>(data)
    class Error<T>(message: String, data: T? = null) : NetworkResult<T>(data, message)
}