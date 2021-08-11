package com.leskov.universal_tasker.ui.create_task

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.leskov.universal_tasker.databinding.DialogCreateTaskBinding
import com.leskov.universal_tasker.domain.models.TaskEntity
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

/**
 *  Created by Android Studio on 8/6/2021 10:53 PM
 *  Developer: Sergey Leskov
 */

@AndroidEntryPoint
class CreateTaskDialog : DialogFragment() {

    private lateinit var binding: DialogCreateTaskBinding

    private val viewModel: CreateTaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogCreateTaskBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val currentDateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            return
        }

        binding.create.setOnClickListener {
            if (validateFields(
                    binding.title.text.toString(),
                    binding.description.text.toString()
                )
            ) {
                viewModel.createTask(
                    TaskEntity(
                        0,
                        binding.title.text.toString(),
                        currentDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
                        binding.description.text.toString(),
                        false
                    )
                )

                binding.title.error = null
                binding.description.error = null
                dismiss()
            } else {
                binding.title.error = "Please set title"
                binding.description.error = "Please set description"
            }
        }

        binding.cancel.setOnClickListener {
            dismiss()
        }
    }

    private fun validateFields(title: String, description: String): Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(description))
    }
}