package com.gyamoto.giphy_client_aac.api.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class User(

    @Json(name = "avatar_url")
    val avatar_url: String,

    @Json(name = "banner_url")
    val banner_url: String,

    @Json(name = "profile_url")
    val profile_url: String,

    val username: String,

    @Json(name = "display_name")
    val display_name: String
) : Parcelable
