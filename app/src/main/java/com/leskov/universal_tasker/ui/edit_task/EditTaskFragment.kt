package com.leskov.universal_tasker.ui.edit_task

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.leskov.universal_tasker.R
import com.leskov.universal_tasker.base.BaseBindingFragment
import com.leskov.universal_tasker.databinding.FragmentEditTaskBinding
import com.leskov.universal_tasker.domain.models.TaskEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@AndroidEntryPoint
class EditTaskFragment : BaseBindingFragment<FragmentEditTaskBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_edit_task

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
                                binding.etTitle.text.toString(),
                                currentDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
                                binding.etDescription.text.toString(),
                                false
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