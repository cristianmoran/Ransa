package com.app.develop.ransapp.repository

import com.app.develop.ransapp.base.core.EventResult
import com.app.develop.ransapp.model.auth.*
import com.app.develop.ransapp.ui.login.*


interface LoginRepository {

    suspend fun login(loginRequest: LoginRequest): EventResult<LoginResponse>
    suspend fun ingresarSede(request: IngresoSedeRequest): EventResult<IngresoSedeResponse>
    suspend fun getEmpresaList(): EventResult<BaseResponse<MutableList<CompanyResponse>>>
    suspend fun getSedeLocalList(credentialsRequest: CredentialsRequest): EventResult<BaseResponse<MutableList<CompanyResponse>>>




}