package com.leskov.universal_tasker.data.remote

import com.leskov.universal_tasker.domain.models.TaskEntity
import retrofit2.Retrofit

/**
 *  Created by Android Studio on 8/6/2021 12:57 PM
 *  Developer: Sergey Leskov
 */

class RemoteDataSourceImpl(retrofit: Retrofit) : RemoteDataSource {
    private val api = retrofit.create(RemoteDataSource::class.java)

    override suspend fun getTasks(): List<TaskEntity> = api.getTasks()

    override suspend fun createTask(vararg: TaskEntity) = api.createTask(vararg)
}