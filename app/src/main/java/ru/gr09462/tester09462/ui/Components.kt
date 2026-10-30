package ru.gr09462.tester09462.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.gr09462.tester09462.CardData
import ru.gr09462.tester09462.R
import ru.gr09462.tester09462.ui.theme.Tester09462Theme

@Composable
fun TestCard(
    cardData: CardData,
    number: Int,
    selectedOption: Int?,
    modifier: Modifier = Modifier,
    isActive: Boolean = true,
    cardColor: Color = Color.Transparent,
    onOptionSelected: (Int) -> Unit = {},
    onAnswer: () -> Unit = {},
) {
    ElevatedCard(
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(
            containerColor = cardColor,
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text("№$number")
            Text(
                "$number. " + cardData.question,
                fontSize = 20.sp
            )
            Column(Modifier.selectableGroup()) {
                cardData.options.forEachIndexed { index, text ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = selectedOption == index,
                                onClick = { onOptionSelected(index) },
                                enabled = isActive,
                                role = Role.RadioButton,
                            )
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = selectedOption == index,
                            onClick = null,
                            enabled = isActive,
                        )
                        Text(
                            text,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
            IconButton(
                onClick = onAnswer,
                enabled = isActive && selectedOption != null,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_add_task_32),
                    contentDescription = null,
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestCardPreview() {
    val c = CardData("Какой тип хранит true и false?", listOf("int", "boolean", "byte", "char"), 1)
    Tester09462Theme(darkTheme = true) {
        TestCard(c, 0 + 1, 1)
    }
}
