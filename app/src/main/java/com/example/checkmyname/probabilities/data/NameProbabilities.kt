package com.example.checkmyname.probabilities.data

import com.squareup.moshi.Json

data class NameProbabilities(
    @Json(name = "name") val name: String,
    @Json(name = "country") val country: List<NationalProbability>
) {
}