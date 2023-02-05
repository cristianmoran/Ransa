package com.app.develop.ransapp.repository.impl

import com.app.develop.ransapp.base.core.EventResult
import com.app.develop.ransapp.repository.LoginRepository
import com.app.develop.ransapp.ui.login.IngresarSedeRequest
import com.app.develop.ransapp.ui.login.IngresarSedeResponse
import com.app.develop.ransapp.ui.login.LoginRequest
import com.app.develop.ransapp.ui.login.LoginResponse
import com.app.develop.ransapp.ui.login.service.LoginService
import javax.inject.Inject

class LoginRepositoryImpl
@Inject constructor(
    private val loginService: LoginService
) :
    LoginRepository {


    override suspend fun login(loginRequest: LoginRequest): EventResult<LoginResponse> {
        return loginService.login(loginRequest)
    }

    override suspend fun ingresarSede(request: IngresarSedeRequest): EventResult<IngresarSedeResponse> {
        return loginService.ingresarSede(request)
    }

}