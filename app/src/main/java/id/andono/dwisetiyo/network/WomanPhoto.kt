package id.andono.dwisetiyo.network

import com.squareup.moshi.Json

class WomanPhoto (
        val id: String,
        // digunakan untuk memetakan img_src dari JSON ke imgSrcUrl
        @Json(name = "img_src") val imgSrcUrl: String
        )