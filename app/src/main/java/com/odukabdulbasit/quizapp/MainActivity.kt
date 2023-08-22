package com.odukabdulbasit.quizapp

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var nextButton: Button

    private val questions = listOf(
        Question("What is the capital of France?", listOf("Berlin", "London", "Paris", "Madrid"), 2),
        Question("Which planet is known as the 'Red Planet'?", listOf("Mars", "Jupiter", "Venus", "Saturn"), 0),
        Question("What is the largest mammal?", listOf("Elephant", "Blue Whale", "Giraffe", "Hippopotamus"), 1)
    )

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionTextView = findViewById(R.id.questionTextView)
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup)
        nextButton = findViewById(R.id.nextButton)

        nextButton.setOnClickListener {
            checkAnswer()
            showNextQuestion()
        }

        // Set up a listener for radio button clicks
        optionsRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            // Enable the "Next" button when a radio button is selected
            nextButton.isEnabled = true
        }

        showNextQuestion()
    }

    private fun showNextQuestion() {
        if (currentQuestionIndex < questions.size) {
            val question = questions[currentQuestionIndex]

            questionTextView.text = question.text
            optionsRadioGroup.removeAllViews()

            question.options.forEachIndexed { index, optionText ->
                val radioButton = RadioButton(this)
                radioButton.id = index
                radioButton.text = optionText
                optionsRadioGroup.addView(radioButton)
            }

            nextButton.isEnabled = false
            currentQuestionIndex++
        } else {
            // Quiz completed
            // You can display the final score or any other result here
        }
    }

    private fun checkAnswer() {
        val selectedRadioButtonId = optionsRadioGroup.checkedRadioButtonId
        if (selectedRadioButtonId == questions[currentQuestionIndex - 1].correctAnswerIndex) {
            score++
        }
    }
}
