package com.app.develop.ransapp.ui.login.service

import com.app.develop.ransapp.base.core.EventResult
import com.app.develop.ransapp.ui.login.IngresarSedeRequest
import com.app.develop.ransapp.ui.login.IngresarSedeResponse
import com.app.develop.ransapp.ui.login.LoginRequest
import com.app.develop.ransapp.ui.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("auth/ransa-ingreso")
    suspend fun login(@Body request: LoginRequest): EventResult<LoginResponse>

    @POST("auth/ransa-ingreso/sede")
    suspend fun ingresarSede(@Body request: IngresarSedeRequest): EventResult<IngresarSedeResponse>

}