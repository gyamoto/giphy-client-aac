package com.gyamoto.giphy_client_aac.api.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Meta(

    @Json(name = "msg")
    val message: String,

    val status: Int,

    @Json(name = "response_id")
    val response_id: String
) : Parcelable
