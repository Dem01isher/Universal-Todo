package com.leskov.universal_tasker.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

/**
 *  Created by Android Studio on 8/7/2021 6:32 PM
 *  Developer: Sergey Leskov
 */

abstract class BaseBindingFragment<Binding : ViewDataBinding> : Fragment() {

    protected lateinit var binding: Binding

    protected abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    fun popBackStack() = findNavController().popBackStack()

    fun navigate(@IdRes id: Int) = findNavController().navigate(id)

    fun navigate(@IdRes id: Int, args: Bundle?) = findNavController().navigate(id, args)

    fun showMessage(@StringRes message: Int) = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()

    fun showMessage(message: String) = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()

}