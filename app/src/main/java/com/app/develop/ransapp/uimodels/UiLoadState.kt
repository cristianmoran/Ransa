package com.app.develop.ransapp.uimodels

internal sealed class UiLoadState {
    object Loading : UiLoadState()
    object Finished : UiLoadState()
}