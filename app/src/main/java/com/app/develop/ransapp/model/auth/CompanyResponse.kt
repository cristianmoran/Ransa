package com.app.develop.ransapp.model.auth

import com.google.gson.annotations.SerializedName

data class CompanyResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("codigo")
    val code: String?,
    @SerializedName("nombre")
    val name: String?,
    @SerializedName("identificador")
    val identifier: String?,
    @SerializedName("pais")
    val country: String?,
    @SerializedName("fechacreacion")
    val creationDate: String?,
    @SerializedName("idestado")
    val idState: Int?,
    @SerializedName("state")
    val state: String?,
)