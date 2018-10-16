package com.gyamoto.giphy_client_aac.api.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Image(

    val url: String
) : Parcelable
