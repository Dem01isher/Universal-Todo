package com.leskov.universal_tasker.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.viewbinding.ViewBinding
import com.leskov.universal_tasker.R
import com.leskov.universal_tasker.base.BindingFragment
import com.leskov.universal_tasker.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BindingFragment<FragmentMainBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentMainBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.bottomNavigationMain.selectedItemId = R.id.taskListFragment

        setUpNavigation()
    }

    private fun setUpNavigation() {
        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(R.id.fragment) as? NavHostFragment
        val navController = nestedNavHostFragment?.navController
        NavigationUI.setupWithNavController(
            binding.bottomNavigationMain,
            navController!!
        )

    }
}