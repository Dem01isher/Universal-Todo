package com.leskov.universal_tasker.ui.create_task

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.leskov.universal_tasker.R
import com.leskov.universal_tasker.base.BaseBindingFragment
import com.leskov.universal_tasker.databinding.FragmentCreateTaskBinding
import com.leskov.universal_tasker.domain.models.TaskEntity
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Created by Android Studio on 8/15/2021 2:37 AM
 *  Developer: Sergey Leskov
 */

@AndroidEntryPoint
class CreateTaskFragment : BaseBindingFragment<FragmentCreateTaskBinding>() {

    private val viewModel: CreateTaskViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.fragment_create_task

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.create_task -> {
                    viewModel.createTask(
                        TaskEntity(
                            id = 0,
                            title = binding.etTitle.text.toString(),
                            "",
                            description = binding.etDescription.text.toString(),
                            done = false
                        )
                    )
                    showMessage("Task has been created")
                    popBackStack()
                }
            }
            true
        }

        binding.toolbar.setNavigationOnClickListener {
            popBackStack()
        }
    }
}