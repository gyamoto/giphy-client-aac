package com.gyamoto.giphy_client_aac.api.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Trend(
    val data: List<Gif>,
    val pagination: Pagination,
    val meta: Meta
) : Parcelable
