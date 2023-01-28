package com.app.develop.ransapp.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.develop.ransapp.base.BaseFragment
import com.app.develop.ransapp.databinding.FragmentOneBinding

class FragmentOne: BaseFragment() {

    lateinit var binding: FragmentOneBinding

    private var statusSearch:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneBinding.inflate(inflater, container, false)
        return binding.root.apply {
            initView()
        }
    }

    private fun initView() {
        binding.btnSearch.setOnClickListener {
            if(!statusSearch){
                statusSearch = true
                visibleSearchConst()
            }else{
                statusSearch = false
                goneSearchConst()
            }
        }
    }

    private fun visibleSearchConst(){
        binding.constSearch.visibility= View.VISIBLE
    }
    private fun goneSearchConst(){
        binding.constSearch.visibility= View.GONE
    }

}