package com.app.develop.ransapp.service

import com.app.develop.ransapp.base.core.EventResult
import com.app.develop.ransapp.model.visitors.VisitorsRequest
import com.app.develop.ransapp.model.visitors.VisitorsResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface EntryService {

    @POST("/v1/ingresos/ingresoVisitante")
    suspend fun visitorEntry(@Body request: VisitorsRequest): EventResult<VisitorsResponse>
}