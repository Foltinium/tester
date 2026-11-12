package ru.gr09462.tester09462.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.gr09462.tester09462.R
import ru.gr09462.tester09462.ui.theme.Tester09462Theme

@Composable
fun ResultScreen(
    score: Int,
    total: Int,
    onRestart: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            stringResource(R.string.test_finished),
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
        )
        Text(
            stringResource(R.string.result) + ": $score/$total",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
        Button(onClick = onRestart) {
            Text(
                stringResource(R.string.start_new_test)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    Tester09462Theme {
        ResultScreen(score = 7, total = 10, onRestart = {})
    }
}
