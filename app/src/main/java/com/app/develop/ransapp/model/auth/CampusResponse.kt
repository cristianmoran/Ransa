package com.app.develop.ransapp.model.auth

import com.google.gson.annotations.SerializedName

data class CampusResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("codigo")
    val code: String?,
    @SerializedName("nombre")
    val name: String?,
    @SerializedName("direccion")
    val address: String?,
    @SerializedName("empresa")
    val company: String?,
    @SerializedName("fechacreacion")
    val creationDate: String?,
    @SerializedName("idestado")
    val idState: Int?,
    @SerializedName("state")
    val state: String?,
)