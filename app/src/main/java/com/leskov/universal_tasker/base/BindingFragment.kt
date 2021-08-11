package com.leskov.universal_tasker.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

/**
 *  Created by Android Studio on 8/7/2021 6:32 PM
 *  Developer: Sergey Leskov
 */

abstract class BindingFragment<out T : ViewBinding> : Fragment() {

    private lateinit var _binding: ViewBinding
    @Suppress("UNCHECKED_CAST")
    protected val binding: T
        get() = _binding as T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater(inflater)
        return _binding!!.root
    }

    protected abstract val bindingInflater: (LayoutInflater) -> ViewBinding

}