package com.example.checkmyname.probabilities.data

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.json.JSONObject

class ProbabilitiesAdapter(private val moshi: Moshi) {

    @FromJson
    fun fromJson(value: JsonReader): List<NationalProbability>? {
        val json = JSONObject(value.nextString())
        val jsonArray = json.getJSONArray("country")
        val type = Types.newParameterizedType(List::class.java, NationalProbability::class.java)
        val adapter = moshi.adapter<List<NationalProbability>>(type)
        return adapter.fromJson(jsonArray.toString())
    }
}