package com.leskov.universal_tasker.ui.about_task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leskov.universal_tasker.data.room.TaskDao
import com.leskov.universal_tasker.domain.models.TaskEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *  Created by Android Studio on 8/6/2021 3:49 PM
 *  Developer: Sergey Leskov
 */

@HiltViewModel
class AboutTaskViewModel @Inject constructor(private val dao: TaskDao) : ViewModel() {

    suspend fun getTaskById(id: Int): Flow<TaskEntity> = withContext(Dispatchers.Main){
        dao.getTaskById(id)
    }
//    suspend fun deleteTaskById(id: Int) =
//        viewModelScope.launch(Dispatchers.IO) {
//            dao.deleteTaskById(id)
//        }
}