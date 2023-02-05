package com.app.develop.ransapp.model.auth

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("apiEstado")
    var apiState: String?,
    @SerializedName("apiMensaje")
    var apiMessage: String?,
    @SerializedName("data")
    var data: T?,
    @SerializedName("total")
    var total: Int?
)
