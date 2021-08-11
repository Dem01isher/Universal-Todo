package com.leskov.universal_tasker.ui.notification_options

import android.app.Application
import android.os.Build
import android.os.Bundle
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.leskov.universal_tasker.base.BindingFragment
import com.leskov.universal_tasker.databinding.FragmentNotificationOptionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationOptionsFragment : BindingFragment<FragmentNotificationOptionsBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentNotificationOptionsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}