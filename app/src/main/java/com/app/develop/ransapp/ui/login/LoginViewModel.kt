package com.app.develop.ransapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.develop.ransapp.base.BaseViewModel
import com.app.develop.ransapp.base.uimodels.UiLoadState
import com.app.develop.ransapp.base.core.EventResult
import com.app.develop.ransapp.entity.SpinnerEntity
import com.app.develop.ransapp.model.auth.*
import com.app.develop.ransapp.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
) : BaseViewModel() {

    private lateinit var preferenceManager: com.app.develop.ransapp.local.PreferenceManager

    private val _loginLiveData: MutableLiveData<LoginResponse> = MutableLiveData()
    val loginObserver: LiveData<LoginResponse> get() = _loginLiveData

    private val _loginLiveIngresaSedeData: MutableLiveData<IngresoSedeResponse> = MutableLiveData()
    val loginIngresaSede: LiveData<IngresoSedeResponse> get() = _loginLiveIngresaSedeData


    private val _empresasLiveData: MutableLiveData<MutableList<CompanyResponse>> =
        MutableLiveData()
    val empresasObserver: LiveData<MutableList<CompanyResponse>> get() = _empresasLiveData

    private val _sedeLiveData: MutableLiveData<MutableList<CompanyResponse>> =
        MutableLiveData()
    val sedeObserver: LiveData<MutableList<CompanyResponse>> get() = _sedeLiveData

    fun initOnClickSesion(
        editTextEmail: String,
        editTextClave: String,
        spinnerEmpresa: SpinnerEntity,
        spinnerSedeLocal: SpinnerEntity
    ) {
        viewModelScope.launch {
            loadingStateLivaData.value = UiLoadState.Loading
            val requestLogin = LoginRequest(
                editTextEmail,
                editTextClave,
                spinnerEmpresa.idStr?:"",
                "b6a3e4e5-3956-4645-8a59-a19efdf48336"//spinnerSedeLocal.idStr?:""
            )
            when (val response = loginRepository.login(requestLogin)) {
                is EventResult.Success -> managementLoginResponse(response.data)
                is EventResult.Failure -> managementException(response)
            }

        }
    }

    fun getEmpresaList() {
        viewModelScope.launch {
            //loadingStateLivaData.value = UiLoadState.Loading
            //val response = loginRepository.getEmpresaList()
            when (val response = loginRepository.getEmpresaList()) {
                is EventResult.Success -> managementEmpresasResponse(response.data?.data)
                is EventResult.Failure -> managementException(response)
            }

        }
    }

    private fun managementEmpresasResponse(data: MutableList<CompanyResponse>?) {
        data?.let { itData ->
            _empresasLiveData.postValue(itData)
        }
    }

    private fun managementLoginResponse(login: LoginResponse?) {
        login?.let { itResponse ->
            viewModelScope.launch {
                preferenceManager.saveDataUser(login.id, login.token)
                loadingStateLivaData.value = UiLoadState.Finished
                _loginLiveData.postValue(itResponse)
            }
            // saveDataLogin(it)
            // _loginSuccessLiveData.value = true
        }
    }

    private fun managementException(response: EventResult.Failure) {

       val data =  response.data
        /*val errorResponse = GsonBuilder().create().fromJson(
            response.data, Login::class.java
        )
        _loginExceptionLiveData.value = errorResponse.error*/
    }

    fun preferenceManager(preferenceManager: com.app.develop.ransapp.local.PreferenceManager) {
        this.preferenceManager = preferenceManager
    }



    fun ingresarSede(spinnerSedelocalIngresa: SpinnerEntity, id: String?, token: String?) {

        id?.let { it ->
            token?.let { itToken ->
                viewModelScope.launch {
                    val request = IngresoSedeRequest(it, spinnerSedelocalIngresa.idStr?:"62519d63-e411-421b-bf9e-47b9bcfb9e99", itToken)
                    loadingStateLivaData.value = UiLoadState.Loading
                    when (val response = loginRepository.ingresarSede(request)) {
                        is EventResult.Success -> {
                            loadingStateLivaData.value = UiLoadState.Finished
                            response.data?.let {
                                _loginLiveIngresaSedeData.postValue(it)
                            }

                        }
                        is EventResult.Failure -> managementException(response)
                    }
                }
            }
        }
    }



    fun getSedeLocal(dataEntitity: SpinnerEntity?) {
        viewModelScope.launch {
            //loadingStateLivaData.value = UiLoadState.Loading
            val credentialsRequest = CredentialsRequest(
                status = "1", idCompany = dataEntitity?.idStr?:"", page = "1",
                tamanio = "0"
            )
            when (val response = loginRepository.getSedeLocalList(credentialsRequest)) {
                is EventResult.Success -> managementSedeResponse(response.data?.data)
                is EventResult.Failure -> managementException(response)
            }

        }
    }


    private fun managementSedeResponse(data: MutableList<CompanyResponse>?) {
        data?.let { itData ->
            _sedeLiveData.postValue(itData)
        }
    }
}