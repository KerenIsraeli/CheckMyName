package com.example.checkmyname.probabilities.data

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface IProbabilitiesService {
    @GET("/")
    suspend fun getProbabilities(
        @Query("name") name: String
    ): NameProbabilities
}

const val PROBABILITIES_URL = "https://api.nationalize.io"
