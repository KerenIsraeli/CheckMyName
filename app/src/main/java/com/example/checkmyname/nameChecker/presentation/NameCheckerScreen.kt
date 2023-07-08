package com.example.checkmyname.nameChecker.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkmyname.R

@Composable
fun NameCheckerScreen(nameUpdater: INameUpdater) {
    NameCheckerScreen() { nameUpdater.onUpdateName(it) }
}

@Composable
private fun NameCheckerScreen(
    onNameEntered: (String) -> Unit
) {
    val name = remember { mutableStateOf("") }
    Box(Modifier.fillMaxSize(), contentAlignment = Center) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp), horizontalAlignment = CenterHorizontally) {
            // TODO put in compose base.
            BasicTextField(
                modifier = Modifier
                    .border(
                        BorderStroke(
                            1.dp,
                            color = Color.LightGray
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(12.dp),
                value = name.value,
                onValueChange = { newValue: String ->
                    name.value = newValue
                },
                singleLine = true,
                textStyle = TextStyle(textAlign = TextAlign.Start),
                decorationBox = @Composable { innerTextField ->
                    Box(
                        modifier = Modifier,
                        contentAlignment = CenterStart
                    ) {
                        if (name.value.isEmpty()) {
                            Text(
                                text = stringResource(R.string.name_insertion_hint),
                                color = Color.Black.copy(0.4f),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W400
                            )
                        }
                        innerTextField()
                    }
                }
            )

            TextButton(
                modifier = Modifier.background(colorResource(R.color.teal_700)),
                onClick = { onNameEntered(name.value) },
                enabled = name.value.isNotEmpty(),
            ) {
                Text(
                    text = stringResource(R.string.done),
                    color = Color.White,
                    fontSize = 16.sp,
                    lineHeight = 19.2.sp,
                    fontWeight = FontWeight.W700
                )
            }
        }
    }
}

@Preview
@Composable
private fun NameCheckerScreenPreview() {
    NameCheckerScreen() {}
}