package com.app.develop.ransapp.repository

import com.app.develop.ransapp.base.core.EventResult
import com.app.develop.ransapp.ui.login.IngresarSedeRequest
import com.app.develop.ransapp.ui.login.IngresarSedeResponse
import com.app.develop.ransapp.ui.login.LoginRequest
import com.app.develop.ransapp.ui.login.LoginResponse


interface LoginRepository {

    suspend fun login(loginRequest: LoginRequest): EventResult<LoginResponse>
    suspend fun ingresarSede(request: IngresarSedeRequest): EventResult<IngresarSedeResponse>

}