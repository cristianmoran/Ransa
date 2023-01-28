package com.app.develop.ransapp.base

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.develop.ransapp.base.core.EventResult
import com.app.develop.ransapp.base.core.isAirplaneModeActive
import com.app.develop.ransapp.base.core.isConnected
import com.app.develop.ransapp.base.uimodels.UiLoadState
import com.app.develop.ransapp.base.uimodels.UiMessageSnack

abstract class BaseViewModel() : ViewModel() {

    internal var messageSnackLiveData: MutableLiveData<UiMessageSnack> = MutableLiveData()

    internal val loadingStateLivaData = MutableLiveData<UiLoadState>()

    internal var exceptionLiveData = MutableLiveData<EventResult.Failure>()

    @RequiresApi(Build.VERSION_CODES.M)
    fun hasInternet(context: Context): Boolean {
        return context.isConnected() && !context.isAirplaneModeActive()
    }

}