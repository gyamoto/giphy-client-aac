package com.gyamoto.giphy_client_aac.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Trend(
    val data: List<Gif>,
    val pagination: Pagination,
    val meta: Meta
)
