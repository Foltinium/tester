package ru.gr09462.tester09462

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

data class CardState(val card: CardData) {
    val color: Color
        get() = card.isCorrect?.let {
            if (it)
                Color(0f, 1f, 0f, 0.15f)
            else
                Color(1f, 0f, 0f, 0.15f)
        } ?: Color.Transparent
    val isActive: Boolean
        get() = card.userAnswer == null
    val points: Int
        get() = if (card.isCorrect == true) 1 else 0
}

class MainViewModel : ViewModel() {
    val cards = mutableStateListOf<CardState>()
    var selectedOption: Int? by mutableStateOf(null)
    var currentCardNum: Int by mutableIntStateOf(0)
    val score: Int
        get() = cards.sumOf { it.points }
    var isFinished: Boolean by mutableStateOf(false)

    init {
        initCards()
    }

    fun onOptionSelected(index: Int) {
        selectedOption = index
    }

    fun onAnswer() {
        val choice = selectedOption ?: return
        cards[currentCardNum].card.userAnswer = choice
        currentCardNum++
        selectedOption = null
    }

    fun initCards() {
        cards.clear()
        currentCardNum = 0
        selectedOption = null
        isFinished = false
        QuestionBank.questions.entries
            .shuffled()
            .take(maxCardsCount)
            .forEach { (text, q) ->
                cards.add(CardState(CardData(text, q.options, q.correctIndex)))
            }
    }

    fun finishTest() {
        isFinished = true
    }

    companion object {
        const val maxCardsCount = 10
    }
}
