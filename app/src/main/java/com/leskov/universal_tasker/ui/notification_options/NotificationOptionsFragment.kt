package com.leskov.universal_tasker.ui.notification_options

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.leskov.universal_tasker.R
import com.leskov.universal_tasker.base.BaseBindingFragment
import com.leskov.universal_tasker.databinding.FragmentNotificationOptionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationOptionsFragment : BaseBindingFragment<FragmentNotificationOptionsBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_notification_options

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