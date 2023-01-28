package com.app.develop.ransapp.base.uimodels

internal sealed class UiLoadState {
    object Loading : UiLoadState()
    object Finished : UiLoadState()
}