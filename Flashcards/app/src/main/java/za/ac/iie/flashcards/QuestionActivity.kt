package za.ac.iie.flashcards

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class QuestionActivity : AppCompatActivity() {

    private val questions = arrayOf(
        "The Roman Empire fell in a single day?",
        "Thabo Mbeki was the first president in 1994?",
        "South Africa was a founding member of the United Nations Charter in 1945?",
        "the first European settlers in South Africa were primarily British?",
        "The Bambatha Rebellion in 1906 was A Zulu uprising against increased taxation by the Natal government?"
    ) // The Asked Question

    private val answers = arrayOf(false, false, true, false, true) // the Correct Answers

    private var currentQuestionIndex = 0
    private var score = 0

    private lateinit var txtQuestion: TextView
    private lateinit var txtFeedback: TextView
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_question)

        // UI elements
        txtQuestion = findViewById(R.id.txtQuestion)
        txtFeedback = findViewById(R.id.txtFeedback)
        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        btnNext = findViewById(R.id.btnNext)

        //Output first question
        presentQuestion()

        //True button click listener checks if answer is correct
        btnTrue.setOnClickListener {
            checkAnswer(true)
        }

        //False button click listener check if answer is correct
        btnFalse.setOnClickListener {
            checkAnswer(false)
        }

        //Next button click listener
        btnNext.setOnClickListener {
            //Moves to the next questions index
            currentQuestionIndex++
            //Checks if there are more questions remaining
            if (currentQuestionIndex < questions.size) {
                //shows the next question
                presentQuestion()
            } else {
                //navigates to score activity
                val intent = Intent(this, ScoreActivity::class.java)
                //gives the final score to the score activity
                intent.putExtra("score", score)
                //
                startActivity(intent)
                // Finishes the current activity to prevent going back
                finish()
            }
        }
    }

    private fun presentQuestion() {
        for (i in currentQuestionIndex until questions.size) {
            txtQuestion.text = questions[i]
            txtFeedback.text = "" // Clear feedback
            break
        }
        btnTrue.isEnabled = true
        btnFalse.isEnabled = true
        btnNext.visibility = View.INVISIBLE
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = answers[currentQuestionIndex]
        if (userAnswer == correctAnswer) {
            txtFeedback.text = "correct!"
            score++
        } else {
            txtFeedback.text = "incorrect!"
        }
        btnTrue.isEnabled = false
        btnFalse.isEnabled = false
        btnNext.visibility = View.VISIBLE
    }
}