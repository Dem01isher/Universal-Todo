package com.leskov.universal_tasker.ui.task_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.leskov.universal_tasker.base.BaseFlowViewModel
import com.leskov.universal_tasker.data.room.TaskDao
import com.leskov.universal_tasker.domain.models.TaskEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *  Created by Android Studio on 8/6/2021 12:36 PM
 *  Developer: Sergey Leskov
 */

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val dao: TaskDao
) : ViewModel() {

    val tasks: Flow<List<TaskEntity>> by lazy { dao.getTasks() }

    fun searchByName(title: String): Flow<List<TaskEntity>> =
        dao.getTaskByTitle(title)


    suspend fun deleteAllTasks() =
        viewModelScope.launch(Dispatchers.Main) {
            dao.deleteAllTasks()
        }


    suspend fun deleteTask(task: TaskEntity) =
        viewModelScope.launch(Dispatchers.Main) {
            dao.deleteTask(task)
        }

    suspend fun insertTask(task: TaskEntity) =
        viewModelScope.launch(Dispatchers.Main) {
            dao.insert(task)
        }
}