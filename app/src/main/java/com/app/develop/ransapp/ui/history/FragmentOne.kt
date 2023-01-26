package com.app.develop.ransapp.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.develop.ransapp.base.BaseFragment
import com.app.develop.ransapp.databinding.FragmentOneBinding

class FragmentOne: BaseFragment() {

    lateinit var binding: FragmentOneBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root
    }

}