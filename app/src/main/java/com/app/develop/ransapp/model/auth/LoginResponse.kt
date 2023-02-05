package com.app.develop.ransapp.model.auth

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("apiEstado")
    val apiState:String,
    @SerializedName("apiMensaje")
    val apiMessage:String,
    @SerializedName("id")
    val id:String,
    @SerializedName("codigo")
    val code:String,
    @SerializedName("nombre")
    val name:String,
    @SerializedName("apellido")
    val lastName:String,
    @SerializedName("correo")
    val email:String,
    @SerializedName("empresa")
    val company:String,
    @SerializedName("sede")
    val campus:String,
    @SerializedName("idestado")
    val idState:Int,
    @SerializedName("estado")
    val state:String,
    @SerializedName("tiporol")
    val typeRole:Int,
    @SerializedName("plataforma")
    val platform:Int,
    @SerializedName("essede")
    val isCampus:Boolean,
    @SerializedName("token")
    val token:String

)
