package com.leskov.universal_tasker.data.remote

import com.leskov.universal_tasker.domain.models.TaskEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.POST

/**
 *  Created by Android Studio on 8/6/2021 12:57 PM
 *  Developer: Sergey Leskov
 */

interface TaskApi {

    companion object{
        const val BASE_URL = "https://universal-backend.herokuapp.com/"
    }

    @GET("/tasks/api/todo/")
    suspend fun getTasks() : List<TaskEntity>

    @POST("/tasks/api/todo/")
    suspend fun createTask(vararg : TaskEntity)
}