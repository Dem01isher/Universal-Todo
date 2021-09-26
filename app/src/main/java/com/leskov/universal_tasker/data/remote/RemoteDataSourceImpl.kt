package com.leskov.universal_tasker.data.remote

import com.leskov.universal_tasker.domain.models.TaskEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit

/**
 *  Created by Android Studio on 8/6/2021 12:57 PM
 *  Developer: Sergey Leskov
 */

class RemoteDataSourceImpl(retrofit: Retrofit) : RemoteDataSource {
    private val api = retrofit.create(RemoteDataSource::class.java)

    override fun getTasks(): Flow<List<TaskEntity>> = api.getTasks()

    override suspend fun createTask(vararg: TaskEntity) = api.createTask(vararg)
}