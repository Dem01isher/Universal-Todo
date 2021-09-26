package com.leskov.universal_tasker.ui.about_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.leskov.universal_tasker.R
import com.leskov.universal_tasker.base.BaseBindingFragment
import com.leskov.universal_tasker.databinding.FragmentAboutTaskBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AboutTaskFragment : BaseBindingFragment<FragmentAboutTaskBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_about_task

    private val viewModel: AboutTaskViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.getTaskById(arguments?.getInt("task_id") ?: 0).collect {
                binding.task = it
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId){
                R.id.edit -> {
                    findNavController().navigate(R.id.action_aboutTaskFragment_to_editTaskFragment, bundleOf("edit_task" to arguments?.getInt("task_id")))
                }
            }
            true
        }
    }
}