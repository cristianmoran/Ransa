package com.app.develop.ransapp.ui.virtualPass

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.app.develop.ransapp.base.BaseActivity
import com.app.develop.ransapp.databinding.ActivityLoginBinding
import com.app.develop.ransapp.databinding.ActivityVirtualPassBinding
import com.app.develop.ransapp.local.PreferenceManager
import com.app.develop.ransapp.ui.history.HistoryActivity
import com.app.develop.ransapp.ui.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class VirtualPassActivity : BaseActivity() {

    lateinit var binding: ActivityVirtualPassBinding

    private val viewModel: VirtualPassViewModel by (this as FragmentActivity).viewModels()

    override fun initObserver() {

        viewModel.virtualPassObserver.observe(this) {
            it?.let {
                binding.textViewNum.setText(it.visitante.numeroCita)
                binding.textViewNombreInput.setText(it.visitante.nombre)
                binding.textViewDniInput.setText(it.visitante.dni)
                binding.textViewVisitadoInput.setText(it.visitante.visitado)
                binding.textViewFechaInput.setText(it.visitante.fechaInicio +" - "+ it.visitante.fechaFin)
                binding.textViewSedeInput.setText(it.visitante.sede)
                binding.textViewZonaInput.setText(it.visitante.sede)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVirtualPassBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val numePase = intent.getStringExtra("editTextTipoDoc")
        viewModel.getInformation(numePase)
        initView()
    }

    private fun initView() {
        binding.imageClose.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
        binding.buttonContinuar.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
    }
}