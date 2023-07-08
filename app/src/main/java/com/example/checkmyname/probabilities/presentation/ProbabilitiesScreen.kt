package com.example.checkmyname.probabilities.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.checkmyname.R
import com.example.checkmyname.probabilities.data.NameProbabilities
import com.example.checkmyname.probabilities.data.NationalProbability

@Composable
fun ProbabilitiesScreen(
    viewModel: ProbabilitiesViewModel,
    onBack: () -> Unit
) {

    val probabilities = viewModel.probabilities.collectAsState().value
    if (probabilities == null) {
        // TODO loader, then error.
        return
    }
    ProbabilitiesScreen(probabilities, onBack)
}

@Composable
private fun ProbabilitiesScreen(
    nameProbabilites: NameProbabilities,
    onBack: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        ScreenToolbar(onBack)

        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = nameProbabilites.name,
                style = typography.headlineMedium,
                color = Color.Black
            )

            val countries = nameProbabilites.country
            LazyColumn() {
                items(countries.size, key = { index -> countries[index].name }) { index ->
                    val probability = countries[index]
                    val country = probability.name

                    val annotatedLinkString: AnnotatedString = buildAnnotatedString {
                        append(stringResource(R.string.probability, country, probability.probability))

                        addStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.W600
                            ), start = 0, end = probability.name.length + 2
                        )
                    }

                    Text(
                        text = annotatedLinkString,
                        fontWeight = FontWeight.W400,
                        color = Color.Black.copy(0.6f)
                    )
                }
            }
        }
    }
}

@Composable
private fun ScreenToolbar(onBack: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(colorResource(R.color.teal_700)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBack) {
            Icon(
                painterResource(R.drawable.baseline_arrow_back_24),
                contentDescription = stringResource(R.string.back),
                modifier = Modifier.padding(24.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ProbabilitiesScreenPreview() {
    val probabilities = mutableListOf<NationalProbability>()
    probabilities.add(NationalProbability("IL", 0.0944534654837))
    probabilities.add(NationalProbability("TH", 0.005))
    probabilities.add(NationalProbability("US", 0.0004))
    ProbabilitiesScreen(NameProbabilities("Tal", probabilities)) {}
}