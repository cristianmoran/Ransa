package com.app.develop.ransapp.service

import com.app.develop.ransapp.base.core.EventResult
import com.app.develop.ransapp.model.auth.*
import com.google.gson.annotations.SerializedName

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Named
import javax.inject.Qualifier

interface AuthService {

    @POST("v1/auth/ransa-ingreso")
    suspend fun login(@Body request: LoginRequest): EventResult<LoginResponse>


    @GET("v1/auth/sedes")
    suspend fun getCampus(
        @Query("idempresa") idempresa: String,
        @Query("estado") state: Int = 1,
        @Query("pagina") page: Int = 1,
        @Query("tamanio") size: Int = 0
    ): EventResult<BaseResponse<MutableList<CompanyResponse>>>


    @GET("v1/auth/empresas")
    suspend fun getCompanies(
        @Query("auth-token") token: String,
        @Query("estado") state: Int = 1,
        @Query("pagina") page: Int = 1,
        @Query("tamanio") size: Int = 0
    ): EventResult<BaseResponse<List<CompanyResponse>>>

    @GET("v1/auth/empresas")
    suspend fun getCompany(): EventResult<BaseResponse<MutableList<CompanyResponse>>>

    @POST("/v1/auth/ransa-ingreso/sede")
    suspend fun ingresarSede(@Body request: IngresoSedeRequest): EventResult<IngresoSedeResponse>


}