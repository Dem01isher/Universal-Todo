package com.leskov.universal_tasker.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

/**
 *  Created by Android Studio on 9/25/2021 12:13 PM
 *  Developer: Sergey Leskov
 */

abstract class BaseListAdapter<T, B : ViewDataBinding>(diffCallBack: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BindingHolder<B>>(diffCallBack) {

    protected abstract val layoutId: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<B> =
        BindingHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )
}