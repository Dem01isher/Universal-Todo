package com.leskov.universal_tasker.data.room

import androidx.room.*
import com.leskov.universal_tasker.domain.models.TaskEntity
import kotlinx.coroutines.flow.Flow

/**
 *  Created by Android Studio on 8/5/2021 3:28 PM
 *  Developer: Sergey Leskov
 */

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table")
    fun getTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task_table WHERE title LIKE :title")
    fun getTaskByTitle(title: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM task_table WHERE id LIKE :id")
    fun getTaskById(id: Int) : Flow<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg: TaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFromRemote(tasks: List<TaskEntity>)

    @Update
    suspend fun update(task: TaskEntity)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()

    @Delete
    suspend fun deleteTask(task: TaskEntity)
}