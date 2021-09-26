package com.leskov.universal_tasker.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.leskov.universal_tasker.R
import com.leskov.universal_tasker.base.BaseBindingFragment
import com.leskov.universal_tasker.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BaseBindingFragment<FragmentSettingsBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_settings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        binding.btnNotificationOptions.setOnClickListener {
            (parentFragment as NavHostFragment)
                .parentFragment
                ?.findNavController()
                ?.navigate(R.id.action_mainFragment_to_notificationOptionsFragment)
        }
        binding.btnAppInfo.setOnClickListener {
            dialogAboutApp()
        }
    }

    private fun dialogAboutApp() {
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setPositiveButton("Ok") { _, _ -> }
        builder.setTitle("App info")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

}