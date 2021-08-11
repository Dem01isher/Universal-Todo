package com.leskov.universal_tasker.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leskov.universal_tasker.domain.models.TaskEntity

/**
 *  Created by Android Studio on 8/5/2021 3:28 PM
 *  Developer: Sergey Leskov
 */
@Database(entities = [TaskEntity::class], exportSchema = false, version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}