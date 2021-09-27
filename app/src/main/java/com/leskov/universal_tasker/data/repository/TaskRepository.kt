package com.leskov.universal_tasker.data.repository

import androidx.room.withTransaction
import com.leskov.universal_tasker.data.remote.TaskApi
import com.leskov.universal_tasker.data.room.TaskDatabase
import com.leskov.universal_tasker.utils.networkBoundResult
import kotlinx.coroutines.delay
import javax.inject.Inject

/**
 *  Created by Android Studio on 9/26/2021 3:35 PM
 *  Developer: Sergey Leskov
 */

class TaskRepository @Inject constructor(
    private val api: TaskApi,
    private val db: TaskDatabase
) {
    private val taskDao = db.taskDao()

    fun getTasks() = networkBoundResult(
        query = {
            taskDao.getTasks()
        },
        fetch = {
            delay(2000)
            api.getTasks()
        },
        saveFetchResult = { tasks ->
            db.withTransaction {
                taskDao.deleteAllTasks()
                taskDao.insertFromRemote(tasks)
            }
        }
    )
}