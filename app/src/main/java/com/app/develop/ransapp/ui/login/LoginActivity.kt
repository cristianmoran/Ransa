package com.app.develop.ransapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.FragmentActivity
import com.app.develop.ransapp.R
import com.app.develop.ransapp.base.BaseActivity
import com.app.develop.ransapp.base.adapter.SpinnerAdapter
import com.app.develop.ransapp.base.uimodels.UiLoadState
import com.app.develop.ransapp.databinding.ActivityLoginBinding
import com.app.develop.ransapp.entity.SpinnerEntity
import com.app.develop.ransapp.local.PreferenceManager
import com.app.develop.ransapp.ui.history.HistoryActivity
import com.app.develop.ransapp.ui.virtualPass.VirtualPassActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_one.*


@AndroidEntryPoint
class LoginActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    lateinit var binding: ActivityLoginBinding
    private var spinnerEmpresa: SpinnerEntity? = null
    private var spinnerSedeLocal: SpinnerEntity? = null
    private var spinnerSedelocalIngresa: SpinnerEntity? = null
    private lateinit var preferenceManager: PreferenceManager
    private var loginResponse: LoginResponse? = null

    private val viewModel: LoginViewModel by (this as FragmentActivity).viewModels()


    override fun initObserver() {
        viewModel.loadingStateLivaData.observe(this) {
            handleVisibilityProgressLoadStates(it)
        }
        viewModel.loginObserver.observe(this) {
            it?.let {
                this.loginResponse = it
                goneLoginSesion()
                visibleRegistraSedeLocal()
                initDataIngresarSede(it)
            }
        }
        viewModel.loginIngresaSede.observe(this) {
            initIngresaViewScanner()
        }
    }

    private fun initDataIngresarSede(loginResponse: LoginResponse) {
        val dataListSedeLocal = mutableListOf<SpinnerEntity>()
        val sedeLocalEmpty = SpinnerEntity(0, "Seleccione Sede")
        dataListSedeLocal.add(sedeLocalEmpty)
        val sedeLocal = SpinnerEntity(1, loginResponse.id)
        dataListSedeLocal.add(sedeLocal)
        val adapterSedeLocal = SpinnerAdapter(context = baseContext, dataListSedeLocal)
        binding.spinnerSedeTrabajar.adapter = adapterSedeLocal
    }

    private fun visibleLoginSesion() {
        binding.constLayoutLogin.visibility = View.VISIBLE
    }

    private fun goneLoginSesion() {
        binding.constLayoutLogin.visibility = View.GONE
    }


    private fun visibleRegistraSedeLocal() {
        binding.constLayoutIngresa.visibility = View.VISIBLE
    }

    private fun goneRegistraSedeLocal() {
        binding.constLayoutIngresa.visibility = View.GONE
    }

    private fun visibleScanner() {
        binding.constLayoutScanner.visibility = View.VISIBLE
    }

    private fun goneScanner() {
        binding.constLayoutScanner.visibility = View.GONE
    }

    private fun handleVisibilityProgressLoadStates(it: UiLoadState?) {
        return when (it) {
            UiLoadState.Loading -> {
                showProgressDialog()
            }
            else -> {
                hideProgressDialog()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        preferenceManager = PreferenceManager(this)
        setContentView(binding.root)
        viewModel.preferenceManager(preferenceManager)
        initSpinnerAdapter()
        initListener()
        initView()
    }

    private fun initListener() {
        binding.spinnerEmpresa.onItemSelectedListener = this
        binding.spinnerSedeLocal.onItemSelectedListener = this
        binding.spinnerSedeTrabajar.onItemSelectedListener = this
    }

    private fun initView() {
        binding.buttonIngresar.setOnClickListener {
            val editTextEmail = binding.editTextUsuario.text.toString()
            if (editTextEmail.isEmpty()) {
                showMessageToast("Ingrese Usuario")
                return@setOnClickListener
            }
            val editTextClave = binding.editTextClaveUsuario.text.toString()
            if (editTextClave.isEmpty()) {
                showMessageToast("Ingrese Clave")
                return@setOnClickListener
            }
            if (spinnerEmpresa == null || spinnerEmpresa?.id == 0) {
                showMessageToast("Seleccione Empresa")
                return@setOnClickListener
            }
            if (spinnerSedeLocal == null || spinnerSedeLocal?.id == 0) {
                showMessageToast("Seleccione Sede/Local")
                return@setOnClickListener
            }
            viewModel.initOnClickSesion(
                editTextEmail,
                editTextClave,
                spinnerEmpresa!!,
                spinnerSedeLocal!!
            )
        }
        binding.btnIngresarSede.setOnClickListener {
            if (spinnerSedelocalIngresa == null || spinnerSedelocalIngresa?.id == 0) {
                showMessageToast("Seleccione Sede   asdadasd")
                return@setOnClickListener
            }
            viewModel.ingresarSede(
                spinnerSedelocalIngresa!!,
                loginResponse?.id,
                loginResponse?.token
            )
        }
        binding.btnIngresarScanner.setOnClickListener {
            startActivity(Intent(this, VirtualPassActivity::class.java))
        }
    }

    private fun initIngresaViewScanner() {
        goneRegistraSedeLocal()
        visibleScanner()
    }

    private fun initSpinnerAdapter() {
        val dataListEmpresa = mutableListOf<SpinnerEntity>()
        val empresaEmpty = SpinnerEntity(0, "Seleccione Empresa")
        dataListEmpresa.add(empresaEmpty)
        val empresa = SpinnerEntity(1, "53b23cdc-1e26-4181-a49e-0ecb67e3dfb8")
        dataListEmpresa.add(empresa)

        val adapterEmpresa = SpinnerAdapter(context = baseContext, dataListEmpresa)
        binding.spinnerEmpresa.adapter = adapterEmpresa

        val dataListSedeLocal = mutableListOf<SpinnerEntity>()
        val sedeLocalEmpty = SpinnerEntity(0, "Seleccione Sede/Local")
        dataListSedeLocal.add(sedeLocalEmpty)
        val sedeLocal = SpinnerEntity(1, "b6a3e4e5-3956-4645-8a59-a19efdf48336")
        dataListSedeLocal.add(sedeLocal)


        val adapterSedeLocal = SpinnerAdapter(context = baseContext, dataListSedeLocal)
        binding.spinnerSedeLocal.adapter = adapterSedeLocal
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val spinner: AppCompatSpinner = parent as AppCompatSpinner
        if (spinner.id == R.id.spinnerEmpresa) {
            val dataEntitity = parent.adapter?.getItem(position) as SpinnerEntity?
            this.spinnerEmpresa = dataEntitity


        } else if (spinner.id == R.id.spinnerSedeLocal) {
            val dataEntitity = parent.adapter?.getItem(position) as SpinnerEntity?
            this.spinnerSedeLocal = dataEntitity
        } else if (spinner.id == R.id.spinnerSedeTrabajar) {
            val dataEntitity = parent.adapter?.getItem(position) as SpinnerEntity?
            this.spinnerSedelocalIngresa = dataEntitity
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

}