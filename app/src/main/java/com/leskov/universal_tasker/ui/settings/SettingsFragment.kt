package com.leskov.universal_tasker.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.leskov.universal_tasker.R
import com.leskov.universal_tasker.base.BindingFragment
import com.leskov.universal_tasker.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BindingFragment<FragmentSettingsBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSettingsBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners(){
        binding.notificationOptions.setOnClickListener {
            (parentFragment as NavHostFragment)
                .parentFragment
                ?.findNavController()
                ?.navigate(R.id.action_mainFragment_to_notificationOptionsFragment)
        }
    }
}