package com.example.checkmyname.probabilities.presentation

import androidx.compose.runtime.Composable
import com.example.checkmyname.baseComponents.BaseComposeFragment
import org.koin.android.ext.android.inject

class ProbabilitiesFragment : BaseComposeFragment() {

    private val viewModel: ProbabilitiesViewModel by inject()

    @Composable
    override fun ScreenContent() {
        ProbabilitiesScreen(viewModel) {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    companion object {
        val TAG = ProbabilitiesFragment::class.java.simpleName
    }
}