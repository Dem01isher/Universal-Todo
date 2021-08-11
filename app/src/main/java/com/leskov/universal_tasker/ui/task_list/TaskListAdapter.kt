package com.leskov.universal_tasker.ui.task_list

import android.R.attr
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leskov.universal_tasker.databinding.ListItemTaskBinding
import com.leskov.universal_tasker.domain.models.TaskEntity
import android.R.attr.data
import android.R.attr.inputType
import android.graphics.Paint
import com.leskov.universal_tasker.base.BindingHolder
import com.leskov.universal_tasker.databinding.ItemDateBinding


/**
 *  Created by Android Studio on 8/6/2021 1:50 PM
 *  Developer: Sergey Leskov
 */

class TaskListAdapter(private val click: (task: TaskEntity) -> Unit) :
    ListAdapter<TaskEntity, BindingHolder<ListItemTaskBinding>>(diffCallback) {

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<*> =
//        ViewHolder(ListItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
//        when (inputType) {
//            1 -> {
//                val holder =
//                    ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//                BindingHolder(holder)
//            }
//            2 -> {
//                val holder =
//                    ListItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//                BindingHolder(holder)
//            }
//            else -> {
//                val holder =
//                    ItemDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//                BindingHolder(holder)
//            }
//        }
//

//    override fun onBindViewHolder(holder: BindingHolder<*>, position: Int) {
//        when (getItemViewType(position)) {
//            1 -> {
//                val holderBinding = holder as BindingHolder<ItemDateBinding>
//                holderBinding.binding.task = getItem(holder.adapterPosition)
//            }
//            2 -> {
//                val holderBinding = holder as BindingHolder<ListItemTaskBinding>
//                holderBinding.binding.task = getItem(holder.adapterPosition)
//
//                holder.binding.root.setOnClickListener {
//                    click(getItem(holder.adapterPosition))
//                }
//            }
//        }
//    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingHolder<ListItemTaskBinding> =
        BindingHolder(
            ListItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BindingHolder<ListItemTaskBinding>, position: Int) {
        val item = getItem(holder.adapterPosition)

        holder.binding.task = item

        holder.binding.done.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) holder.binding.title.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            else holder.binding.title.paintFlags = 0
        }

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