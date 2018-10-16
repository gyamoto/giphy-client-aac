package com.gyamoto.giphy_client_aac.api.model

import com.squareup.moshi.Json

data class Images(

    @Json(name = "fixed_height")
    val fixedHeight: Image,

    val original: Image
)
