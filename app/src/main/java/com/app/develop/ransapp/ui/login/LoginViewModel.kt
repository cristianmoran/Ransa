package com.app.develop.ransapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.develop.ransapp.base.BaseViewModel
import com.app.develop.ransapp.base.uimodels.UiLoadState
import com.app.develop.ransapp.base.core.EventResult
import com.app.develop.ransapp.entity.SpinnerEntity
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

    private val _loginLiveIngresaSedeData: MutableLiveData<IngresarSedeResponse> = MutableLiveData()
    val loginIngresaSede: LiveData<IngresarSedeResponse> get() = _loginLiveIngresaSedeData

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
                spinnerEmpresa.name,
                spinnerSedeLocal.name
            )
            when (val response = loginRepository.login(requestLogin)) {
                is EventResult.Success -> managementLoginResponse(response.data)
                is EventResult.Failure -> managementException(response)
            }

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
        /*val errorResponse = GsonBuilder().create().fromJson(
            response.data, Login::class.java
        )
        _loginExceptionLiveData.value = errorResponse.error*/
    }

    fun preferenceManager(preferenceManager: com.app.develop.ransapp.local.PreferenceManager) {
        this.preferenceManager = preferenceManager
    }

    fun ingresarSede(spinnerSedelocalIngresa: SpinnerEntity, id: String?, token: String?) {

        id?.let {  it->
            token?.let {itToken->
                viewModelScope.launch {
                    val request = IngresarSedeRequest(it,spinnerSedelocalIngresa.name,itToken)
                    loadingStateLivaData.value = UiLoadState.Loading
                    when (val response = loginRepository.ingresarSede(request)) {
                        is EventResult.Success -> {
                            loadingStateLivaData.value = UiLoadState.Finished
                            _loginLiveIngresaSedeData.postValue(response.data)
                        }
                        is EventResult.Failure -> managementException(response)
                    }
                }
            }
        }
    }

}