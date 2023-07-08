package com.example.checkmyname.probabilities.presentation

import androidx.lifecycle.ViewModel
import com.example.checkmyname.probabilities.data.NameProbabilities
import com.example.checkmyname.probabilities.domain.ProbabiltyRetrieverRepository
import kotlinx.coroutines.flow.StateFlow

class ProbabilitiesViewModel(
    probabilitiesRepository: ProbabiltyRetrieverRepository
) : ViewModel() {

    val probabilities: StateFlow<NameProbabilities?> = probabilitiesRepository.getProbabilities()
}