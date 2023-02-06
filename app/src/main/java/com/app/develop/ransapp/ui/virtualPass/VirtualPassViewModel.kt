package com.app.develop.ransapp.ui.virtualPass

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.develop.ransapp.base.BaseViewModel
import com.app.develop.ransapp.base.core.EventResult
import com.app.develop.ransapp.base.uimodels.UiLoadState
import com.app.develop.ransapp.model.auth.IngresoSedeResponse
import com.app.develop.ransapp.model.auth.LoginRequest
import com.app.develop.ransapp.model.auth.LoginResponse
import com.app.develop.ransapp.model.visitors.VisitorsRequest
import com.app.develop.ransapp.model.visitors.VisitorsResponse
import com.app.develop.ransapp.repository.LoginRepository
import com.app.develop.ransapp.repository.VirtualPassRepository
import com.google.gson.GsonBuilder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VirtualPassViewModel @Inject constructor(
    private val virtualPassRepository: VirtualPassRepository
) : BaseViewModel() {

    private val _virtualExeptionLiveData: MutableLiveData<VisitorsResponse> = MutableLiveData()
    val virtualPassObserver: LiveData<VisitorsResponse> get() = _virtualExeptionLiveData

    fun getInformation(numePase: String?) {
        numePase?.let {
            viewModelScope.launch {
                loadingStateLivaData.value = UiLoadState.Loading
                val request = VisitorsRequest(numePase,"salida")
                when (val response = virtualPassRepository.visitorsEnty(request)) {
                    is EventResult.Success -> managementLoginResponse(response.data)
                    is EventResult.Failure -> managementException(response)
                }

            }
        }

    }

    private fun managementException(response: EventResult.Failure) {
        val errorResponse = GsonBuilder().create().fromJson(
            response.data, VisitorsResponse::class.java
        )
       // Log.i("VirtualpassViewModel ", errorResponse.visitante().to)
        _virtualExeptionLiveData.value = errorResponse
    }

    private fun managementLoginResponse(data: VisitorsResponse?) {

    }
}