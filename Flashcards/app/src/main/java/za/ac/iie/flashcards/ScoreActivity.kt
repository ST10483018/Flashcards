package za.ac.iie.flashcards

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    private val questions = arrayOf(
        "The Roman Empire fell in a single day?",
        "Thabo Mbeki was the first president in 1994?",
        "South Africa was a founding member of the United Nations Charter in 1945?",
        "the first European settlers in South Africa were primarily British?",
        "The Bambatha Rebellion in 1906 was A Zulu uprising against increased taxation by the Natal government?"
    )

    private val answers = arrayOf(false,false,true,false,true)

    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)

        //get the information from QuestionActivity
        score = intent.getIntExtra("score", 0)

        //UI elements
        val outscore = findViewById<TextView>(R.id.txtScore)
        val txtFeed = findViewById<TextView>(R.id.txtReview)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val btnExit = findViewById<Button>(R.id.btnExit)

        // Output total score with feedback
        val feedbackMessage = if (score >= 3) "Great Job!" else "Keep practising!"
        outscore.text = "Your score: $score\n $feedbackMessage"

        //Review button to show all questions and correct answers
        btnReview.setOnClickListener{
            var reviewText = ""
            for (i in questions.indices) {
                val correctAnswer = if (answers[i]) "True" else "False"
                reviewText += "${i + 1}. ${questions[i]} \nCorrect Answer: $correctAnswer\n\n"
            }
            txtFeed.text = reviewText
        }

        //Exit Button
        btnExit.setOnClickListener {
            //terminates the app
            finishAffinity()
        }

    }
}