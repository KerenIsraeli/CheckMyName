package com.example.checkmyname.probabilities.data

import android.util.Log
import com.example.checkmyname.nameChecker.domain.NameUpdaterRepository
import com.example.checkmyname.probabilities.domain.ProbabiltyRetrieverRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CountryProbabilitiesRepositoryImpl(
    private val service: IProbabilitiesService
) : NameUpdaterRepository, ProbabiltyRetrieverRepository {

    companion object {
        val TAG = CountryProbabilitiesRepositoryImpl::class.java.simpleName
    }
    private val _probabilities: MutableStateFlow<NameProbabilities?> = MutableStateFlow(null)

    override suspend fun updateName(name: String) {
        Log.d(TAG, "updateName: $name")
        _probabilities.value = service.getProbabilities(name)
    }

    override fun getProbabilities(): StateFlow<NameProbabilities?> {
        return _probabilities
    }
}