package com.app.develop.ransapp.model.auth

import com.google.gson.annotations.SerializedName



data class CredentialsRequest(
    @SerializedName("estado")
    val status:String,
    @SerializedName("idempresa")
    val idCompany: String,
    @SerializedName("pagina")
    val page:String,
    @SerializedName("tamanio")
    val tamanio:String
)