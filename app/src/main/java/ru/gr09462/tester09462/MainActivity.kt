package ru.gr09462.tester09462

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.gr09462.tester09462.ui.ResultScreen
import ru.gr09462.tester09462.ui.TestCard
import ru.gr09462.tester09462.ui.theme.Tester09462Theme

class MainActivity : ComponentActivity() {
    private val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tester09462Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        if (!vm.isFinished) {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = PaddingValues(8.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                            ) {
                                item {
                                    Text(
                                        stringResource(R.string.java_test),
                                        modifier = Modifier.fillMaxWidth(),
                                        fontSize = 32.sp,
                                        textAlign = TextAlign.Center,
                                    )
                                }
                                itemsIndexed(vm.cards.take(vm.currentCardNum + 1)) { index, cardState ->
                                    TestCard(
                                        cardState.card,
                                        number = index + 1,
                                        selectedOption = cardState.card.userAnswer ?: vm.selectedOption,
                                        isActive = cardState.isActive,
                                        cardColor = cardState.color,
                                        onOptionSelected = vm::onOptionSelected,
                                        onAnswer = vm::onAnswer,
                                    )
                                }
                                item {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier.fillMaxWidth(),
                                    ) {
                                        Button(onClick = vm::finishTest) {
                                            Text(
                                                stringResource(R.string.finish_test)
                                            )
                                        }
                                    }
                                }
                            }
                        } else {
                            ResultScreen(
                                score = vm.score,
                                total = MainViewModel.maxCardsCount,
                                onRestart = vm::initCards,
                            )
                        }
                    }
                }
            }
        }
    }
}
