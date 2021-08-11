package com.leskov.universal_tasker.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 *  Created by Android Studio on 8/10/2021 10:18 AM
 *  Developer: Sergey Leskov
 */

class BindingHolder<B : ViewDataBinding>(val binding: B) : RecyclerView.ViewHolder(binding.root)