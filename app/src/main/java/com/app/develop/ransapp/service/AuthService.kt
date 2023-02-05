package com.app.develop.ransapp.service

import com.app.develop.ransapp.base.core.EventResult
import com.app.develop.ransapp.model.auth.BaseResponse
import com.app.develop.ransapp.model.auth.CompanyResponse
import com.app.develop.ransapp.model.auth.LoginRequest
import com.app.develop.ransapp.ui.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {

    @POST("v1/auth/ransa-ingreso/sede")
    suspend fun login(@Body request: LoginRequest): EventResult<LoginResponse>

    @GET("v1/auth/sedes")
    suspend fun getCampus(@Body request: CredentialsRequest): EventResult<LoginResponse>

    @GET("v1/auth/empresas")
    suspend fun getCompanies(
        @Query("auth-token") token: String,
        @Query("estado") state: Int = 1,
        @Query("pagina") page: Int = 1,
        @Query("tamanio") size: Int = 0
    ): EventResult<BaseResponse<List<CompanyResponse>>>

}