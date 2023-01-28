package com.app.develop.ransapp.ui.virtualPass

import android.content.Intent
import android.os.Bundle
import com.app.develop.ransapp.base.BaseActivity
import com.app.develop.ransapp.databinding.ActivityLoginBinding
import com.app.develop.ransapp.databinding.ActivityVirtualPassBinding
import com.app.develop.ransapp.local.PreferenceManager
import com.app.develop.ransapp.ui.history.HistoryActivity

class VirtualPassActivity:BaseActivity() {

    lateinit var binding:ActivityVirtualPassBinding

    override fun initObserver() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVirtualPassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.imageClose.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
}