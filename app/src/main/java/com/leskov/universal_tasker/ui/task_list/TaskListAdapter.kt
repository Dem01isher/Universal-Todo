package com.leskov.universal_tasker.ui.task_list

import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import com.leskov.universal_tasker.R
import com.leskov.universal_tasker.base.BaseListAdapter
import com.leskov.universal_tasker.base.BindingHolder
import com.leskov.universal_tasker.databinding.ListItemTaskBinding
import com.leskov.universal_tasker.domain.models.TaskEntity


/**
 *  Created by Android Studio on 8/6/2021 1:50 PM
 *  Developer: Sergey Leskov
 */

class TaskListAdapter(private val click: (task: TaskEntity) -> Unit) :
    BaseListAdapter<TaskEntity, ListItemTaskBinding>(diffCallback) {

    private val clickListener: (() -> Unit)? = null

    override val layoutId: Int
        get() = R.layout.list_item_task

    override fun onBindViewHolder(holder: BindingHolder<ListItemTaskBinding>, position: Int) {
        val item = getItem(holder.adapterPosition)

        holder.binding.task = item


        holder.binding.root.setOnClickListener {
            click(item)
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<TaskEntity>() {
            override fun areItemsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TaskEntity, newItem: TaskEntity): Boolean =
                oldItem == newItem

        }
    }
}