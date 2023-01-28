package com.app.develop.ransapp.ui.login


data class LoginRequest(
    val correo: String,
    val contrasenia: String,
    val idempresa: String,
    val idsede: String
)

data class LoginResponse(
    val apiEstado: String,
    val id: String,
    val codigo: String,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val empresa: String,
    val sede: String,
    val idestado: Int,
    val estado: String,
    val tiporol: Int,
    val plataforma: String,
    val essede: Boolean,
    val token: String,
)

data class IngresarSedeRequest(
    val id: String,
    val idsede: String,
    val token: String
)
data class IngresarSedeResponse(
    val apiEstado: String,
    val apiMensaje: String,
    val id: String,
    val token: String
)