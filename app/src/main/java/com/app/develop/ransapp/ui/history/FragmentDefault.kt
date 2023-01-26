package com.app.develop.ransapp.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.develop.ransapp.base.BaseFragment
import com.app.develop.ransapp.databinding.FragmentDefaultBinding

class FragmentDefault:BaseFragment() {

    lateinit var binding :FragmentDefaultBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDefaultBinding.inflate(inflater, container, false)
        return binding.root
    }


}