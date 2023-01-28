package com.app.develop.ransapp.base.core

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.GsonBuilder
import okhttp3.Request
import okio.Timeout
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class CustomCallAdapterFactory(private val context: Context) : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ) = when (getRawType(returnType)) {
        retrofit2.Call::class.java -> {
            val callType = getParameterUpperBound(0, returnType as ParameterizedType)
            when (getRawType(callType)) {
                EventResult::class.java -> {
                    val resultType = getParameterUpperBound(0, callType as ParameterizedType)
                    ResultAdapter(resultType, context)
                }
                else -> null
            }
        }
        else -> null
    }
}

class ResultAdapter(
    private val type: Type,
    val context: Context
) : CallAdapter<Type, retrofit2.Call<EventResult<Type>>> {
    override fun responseType() = type
    override fun adapt(call: retrofit2.Call<Type>): retrofit2.Call<EventResult<Type>> =
        ResultCall(call, context)
}

class ResultCall<T>(proxy: retrofit2.Call<T>, val context: Context) :
    CallDelegate<T, EventResult<T>>(proxy) {
    override fun enqueueImpl(callback: retrofit2.Callback<EventResult<T>>) =
        proxy.enqueue(object : retrofit2.Callback<T> {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun onResponse(call: retrofit2.Call<T>, response: retrofit2.Response<T>) {
                val code = response.code()
                val responseErrorString = response.errorBody()?.string()
                val result = if (response.isSuccessful) {
                    val body = response.body()
                    EventResult.Success(body)
                } else {
                    try {
                        val responseError =
                            GsonBuilder().create().fromJson(
                                responseErrorString, ErrorResponse::class.java
                            )
                        formatResultException(code = code, responseError, responseErrorString)
                    } catch (e: Exception) {
                        formatResultException(code = code, null, responseErrorString)
                    }
                }
                callback.onResponse(this@ResultCall, retrofit2.Response.success(result))
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onFailure(call: retrofit2.Call<T>, t: Throwable) {
                val result = formatResultException()
                callback.onResponse(this@ResultCall, retrofit2.Response.success(result))
            }
        })

    override fun cloneImpl() = ResultCall(proxy.clone(), context)
    override fun timeout(): Timeout {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun formatResultException(
        code: Int = 0,
        responseError: ErrorResponse? = null,
        responseErrorString: String? = null
    ): EventResult.Failure {
        return if (context.isAirplaneModeActive()) {
            EventResult.Failure(
                ErrorRetrofitType.AIRPLANE_ACTIVE, responseError, responseErrorString
            )
        } else if (!context.isConnected()) {
            EventResult.Failure(
                ErrorRetrofitType.NETWORK_EXCEPTION, responseError, responseErrorString
            )
        } else if (code == 401) {
            EventResult.Failure(ErrorRetrofitType.UNAUTHORIZED, responseError, responseErrorString)
        } else {
            EventResult.Failure(ErrorRetrofitType.EXCEPTION, responseError, responseErrorString)
        }
    }
}

abstract class CallDelegate<TIn, TOut>(
    protected val proxy: retrofit2.Call<TIn>
) : retrofit2.Call<TOut> {
    override fun execute(): retrofit2.Response<TOut> = throw NotImplementedError()
    final override fun enqueue(callback: retrofit2.Callback<TOut>) = enqueueImpl(callback)
    final override fun clone(): retrofit2.Call<TOut> = cloneImpl()

    override fun cancel() = proxy.cancel()
    override fun request(): Request = proxy.request()
    override fun isExecuted() = proxy.isExecuted
    override fun isCanceled() = proxy.isCanceled

    abstract fun enqueueImpl(callback: retrofit2.Callback<TOut>)
    abstract fun cloneImpl(): retrofit2.Call<TOut>
}