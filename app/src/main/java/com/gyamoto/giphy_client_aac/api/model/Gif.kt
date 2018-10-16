package com.gyamoto.giphy_client_aac.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Gif(

    val type: String,

    val id: String,

    val slug: String,

    val url: String,

    @Json(name = "bitly_url")
    val bitly_url: String,

    @Json(name = "embed_url")
    val embed_url: String,

    val username: String,

    val source: String,

    val rating: String,

    @Json(name = "content_url")
    val content_url: String,

    val user: User?,

    @Json(name = "source_tld")
    val source_tld: String,

    @Json(name = "source_post_url")
    val source_post_url: String,

    @Json(name = "update_datetime")
    val update_datetime: String?,

    @Json(name = "create_datetime")
    val create_datetime: String?,

    @Json(name = "import_datetime")
    val import_datetime: String?,

    @Json(name = "trending_datetime")
    val trending_datetime: String?,

    // TODO: support images
    val images: Images,

    val title: String
)
