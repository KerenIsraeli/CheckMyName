package com.example.checkmyname.nameChecker.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.checkmyname.R
import com.example.checkmyname.baseComponents.BaseComposeFragment
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class NameCheckerFragment : BaseComposeFragment() {

    private val viewModel: NameCheckerViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        navigateOnNameChange()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun navigateOnNameChange() {
        lifecycleScope.launch {
            viewModel.name.collect() {
                if (it.isEmpty()) { return@collect }
                val activity = activity ?: return@collect

                // TODO inside activity (access name value)
                val navController = Navigation.findNavController(activity, R.id.nav_host_fragment_activity_main)
                navController.navigate(R.id.navigation_probabilities)
            }
        }
    }

    @Composable
    override fun ScreenContent() {
        NameCheckerScreen(viewModel)
    }
}