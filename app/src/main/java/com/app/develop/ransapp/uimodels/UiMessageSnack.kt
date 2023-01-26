package com.app.develop.ransapp.uimodels

import android.view.ViewGroup

internal data class UiMessageSnack(
    val message: String,
    var viewGroup: ViewGroup?,
    val type: UiMessageSnackType
)

internal enum class UiMessageSnackType(val code: Int) {
    SUCCESS(0),
    FAILED(1);

    companion object {
        fun findByType(code: Int) = values()
            .find { it.code == code } ?: SUCCESS
    }
}