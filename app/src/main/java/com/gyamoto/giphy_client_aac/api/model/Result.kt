package com.gyamoto.giphy_client_aac.api.model

sealed class Result<out T> {

    object Loading : Result<Nothing>()
    data class Success<out S>(val data: S) : Result<S>()
    data class Error(val error: Throwable) : Result<Nothing>()

}
