package com.app.develop.ransapp.ui.scanner

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
class ScannerViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
) : BaseViewModel() {


}