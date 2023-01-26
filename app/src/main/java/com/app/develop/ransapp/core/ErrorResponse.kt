package com.app.develop.ransapp.core

data class ErrorResponse(
    val status: String?,
    val type: String?,
    val title: String?,
    val detail: String?
)