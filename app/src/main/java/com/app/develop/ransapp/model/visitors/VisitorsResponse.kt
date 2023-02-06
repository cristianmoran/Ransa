package com.app.develop.ransapp.model.visitors

import com.google.gson.annotations.SerializedName


data class VisitorsResponse(
    @SerializedName("apiEstado")
    val apiEstado:String,
    @SerializedName("visitante")
    val visitante: VisitanteResponse
)

data class VisitanteResponse(
    @SerializedName("numeroCita")
    val numeroCita:String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("dni")
    val dni: String,
    @SerializedName("visitado")
    val visitado: String,
    @SerializedName("fechaInicio")
    val fechaInicio: String,
    @SerializedName("fechaFin")
    val fechaFin: String,
    @SerializedName("sede")
    val sede: String

)

