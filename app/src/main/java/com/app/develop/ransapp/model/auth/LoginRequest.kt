package com.app.develop.ransapp.model.auth

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("correo")
    val email:String,
    @SerializedName("contrasenia")
    val password:String,
    @SerializedName("idempresa")
    val idCompany:String,
    @SerializedName("idsede")
    val idCampus:String
)