package com.app.develop.ransapp.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.develop.ransapp.base.BaseFragment
import com.app.develop.ransapp.databinding.FragmentThreeBinding

class FragmentThree: BaseFragment() {

    lateinit var binding : FragmentThreeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

}