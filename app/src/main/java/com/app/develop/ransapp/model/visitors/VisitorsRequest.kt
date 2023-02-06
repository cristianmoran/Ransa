package com.app.develop.ransapp.model.visitors

import com.google.gson.annotations.SerializedName

data class VisitorsRequest(
    @SerializedName("numeropase")
    val numPase:String,
    @SerializedName("ubicacion")
    val ubication: String
)