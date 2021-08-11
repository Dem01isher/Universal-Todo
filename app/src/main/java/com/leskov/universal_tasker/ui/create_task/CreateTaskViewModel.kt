package com.leskov.universal_tasker.ui.create_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leskov.universal_tasker.data.room.TaskDao
import com.leskov.universal_tasker.domain.models.TaskEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *  Created by Android Studio on 8/6/2021 12:28 PM
 *  Developer: Sergey Leskov
 */

@HiltViewModel
class CreateTaskViewModel @Inject constructor(private val dao: TaskDao) : ViewModel() {

    fun createTask(vararg: TaskEntity) =
        viewModelScope.launch {
            dao.insert(vararg)
        }


}