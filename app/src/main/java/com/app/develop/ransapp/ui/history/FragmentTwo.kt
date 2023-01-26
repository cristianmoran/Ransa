package com.app.develop.ransapp.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.develop.ransapp.base.BaseFragment
import com.app.develop.ransapp.databinding.FragmentTwoBinding

class FragmentTwo: BaseFragment() {
    lateinit var binding: FragmentTwoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

}