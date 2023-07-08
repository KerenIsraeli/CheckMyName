package com.example.checkmyname.probabilities.data

import com.squareup.moshi.Json

data class NationalProbability(
    @field:Json(name = "country_id") val name: String,
    @Json(name = "probability") val probability: Double
)