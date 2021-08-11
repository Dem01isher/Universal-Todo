package com.leskov.universal_tasker.domain.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

/**
 *  Created by Android Studio on 8/5/2021 3:29 PM
 *  Developer: Sergey Leskov
 */

@Entity(tableName = "task_table")
@Parcelize
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "time")
    val time: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "done")
    val done: Boolean
) : Parcelable
