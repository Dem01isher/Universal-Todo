package com.leskov.universal_tasker.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.leskov.universal_tasker.R
import com.leskov.universal_tasker.base.BindingFragment
import com.leskov.universal_tasker.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BindingFragment<FragmentSplashBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSplashBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper())
            .postDelayed({
                findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
            }, 3000)
    }
}