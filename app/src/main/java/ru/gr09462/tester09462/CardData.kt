package ru.gr09462.tester09462

class CardData(
    val question: String,
    val options: List<String>,
    private val correctIndex: Int,
) {
    var userAnswer: Int? = null
    val isCorrect: Boolean?
        get() = userAnswer?.let { it == correctIndex }
}
