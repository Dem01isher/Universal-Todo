package com.leskov.universal_tasker.ui.task_list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.leskov.universal_tasker.R
import com.leskov.universal_tasker.base.BindingFragment
import com.leskov.universal_tasker.databinding.FragmentTaskListBinding
import com.leskov.universal_tasker.ui.create_task.CreateTaskDialog
import com.leskov.universal_tasker.utils.SwipeToDeleteCallback
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import com.google.android.material.snackbar.Snackbar


@AndroidEntryPoint
class TaskListFragment : BindingFragment<FragmentTaskListBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentTaskListBinding::inflate

    private val viewModel: TaskListViewModel by viewModels()

    private val adapter = TaskListAdapter {
        (parentFragment as NavHostFragment)
            .parentFragment
            ?.findNavController()
            ?.navigate(R.id.action_mainFragment_to_aboutTaskFragment, bundleOf("task_id" to it.id))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listOfTasks.adapter = adapter

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val adapter = binding.listOfTasks.adapter as TaskListAdapter

                Snackbar.make(
                    requireView(),
                    getString(R.string.remove_task),
                    Snackbar.LENGTH_SHORT
                )
                    .setAction("Undo") {
                        adapter.notifyItemChanged(viewHolder.adapterPosition)
                    }
                    .addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                        override fun onShown(transientBottomBar: Snackbar?) {
                            super.onShown(transientBottomBar)
                        }

                        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                            if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT) {
                                lifecycleScope.launchWhenResumed {
                                    viewModel.deleteTask(adapter.currentList[viewHolder.adapterPosition])
                                }
                            }
                        }
                    }).show()

            }
        }

        ItemTouchHelper(swipeHandler).attachToRecyclerView(binding.listOfTasks)

        binding.fab.setOnClickListener {
            CreateTaskDialog().show(parentFragmentManager, "")
        }

        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {

                R.id.delete_all_tasks -> deleteTask()

                R.id.search -> {

                }
            }
            true
        }

        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.tasks.collect { list ->
                if (list.isNullOrEmpty()) {
                    binding.progressBar.visibility = View.GONE
                    adapter.submitList(null)
                } else {
                    binding.progressBar.visibility = View.GONE
                    binding.listIsEmpty.visibility = View.GONE
                    adapter.submitList(list)
                }
            }
        }
    }

    private fun deleteTask() {
        val builder = MaterialAlertDialogBuilder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            lifecycleScope.launchWhenResumed {
                viewModel.deleteAllTasks()
            }
            Snackbar.make(requireView(), "Successfully removed everything", Snackbar.LENGTH_SHORT).show()
//            Toast.makeText(
//                requireContext(),
//                "Successfully removed everything",
//                Toast.LENGTH_SHORT
//            ).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete everything?")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()
    }

    private fun searchDatabase(title: String) {
        lifecycleScope.launchWhenResumed {
            viewModel.searchByName("%$title").collect { list ->
                list?.let {
                    adapter.submitList(it)
                }
            }
        }
    }

}