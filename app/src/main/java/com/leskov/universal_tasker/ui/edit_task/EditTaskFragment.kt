package com.leskov.universal_tasker.ui.edit_task

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.leskov.universal_tasker.R
import com.leskov.universal_tasker.base.BindingFragment
import com.leskov.universal_tasker.databinding.FragmentEditTaskBinding
import com.leskov.universal_tasker.domain.models.TaskEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@AndroidEntryPoint
class EditTaskFragment : BindingFragment<FragmentEditTaskBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentEditTaskBinding::inflate

    private val viewModel: EditTaskViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()
    }

    private fun initListeners() {

        val currentDateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            return
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.save_task -> {
                    lifecycleScope.launchWhenResumed {
                        viewModel.updateTask(
                            TaskEntity(
                                arguments?.getInt("edit_task") ?: 0,
                                binding.title.text.toString(),
                                currentDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
                                binding.description.text.toString(),
                                binding.done.isChecked
                            )
                        )
                        findNavController().popBackStack()
                    }
                }
            }
            true
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.getTaskById(arguments?.getInt("edit_task") ?: 0).collect {
                binding.task = it
            }
        }
    }
}