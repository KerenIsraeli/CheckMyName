package com.example.checkmyname.probabilities.domain

import com.example.checkmyname.probabilities.data.NameProbabilities
import kotlinx.coroutines.flow.StateFlow

interface ProbabiltyRetrieverRepository {
    fun getProbabilities(): StateFlow<NameProbabilities?>
}