package com.app.develop.ransapp.repository.impl

import com.app.develop.ransapp.base.core.EventResult
import com.app.develop.ransapp.model.auth.*
import com.app.develop.ransapp.repository.LoginRepository
import com.app.develop.ransapp.service.AuthService
import javax.inject.Inject

class LoginRepositoryImpl
@Inject constructor(
    private val authService: AuthService
) : LoginRepository {


    override suspend fun login(loginRequest: LoginRequest): EventResult<LoginResponse> {
        return authService.login(loginRequest)
    }

    override suspend fun ingresarSede(request: IngresoSedeRequest): EventResult<IngresoSedeResponse> {
        return authService.ingresarSede(request)
    }

    override suspend fun getEmpresaList(): EventResult<BaseResponse<MutableList<CompanyResponse>>> {
        return authService.getCompany()
    }

    override suspend fun getSedeLocalList(credentialsRequest: CredentialsRequest): EventResult<BaseResponse<MutableList<CompanyResponse>>> {
        return authService.getCampus(credentialsRequest.idCompany)
    }

}