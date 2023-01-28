package com.app.develop.ransapp.base.core

sealed class EventResult<out T> {
    data class Success<T>(val data: T?) : EventResult<T>()
    data class Failure(val type: ErrorRetrofitType, val responseError: ErrorResponse?, val data:String?=null) :
        EventResult<Nothing>()
}