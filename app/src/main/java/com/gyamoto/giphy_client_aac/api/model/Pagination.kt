package com.gyamoto.giphy_client_aac.api.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Pagination(

    val offset: Int,

    @Json(name = "total_count")
    val total_count: Int,

    val count: Int
) : Parcelable
