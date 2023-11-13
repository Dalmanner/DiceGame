package com.example.dicegame

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var welcomeText: TextView
    private lateinit var rollButton: Button

    private var userChoice: String = ""
    private var userScore: Int = 0
    private var roundsPlayed: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        welcomeText = findViewById(R.id.welcomeText)
        rollButton = findViewById(R.id.rollButton)

        rollButton.setOnClickListener {
            if (roundsPlayed < 3) {
                playRound()
            } else {
                showGameOver(userScore)
                resetGame()
            }
        }

        showWelcomeScreen()
    }

    private fun showWelcomeScreen() {
        welcomeText.text = "Let's Rock'N Roll Some Dice!!"
        rollButton.text = "Roll"
    }

    private fun playRound() {
        val cpuResult = rollDice()
        val userResult = rollDice()

        val totalResult = cpuResult + userResult

        if (userChoice == "higher" && totalResult > 7 ||
            userChoice == "lower" && totalResult <= 7
        ) {
            userScore++
        }

        roundsPlayed++
        if (roundsPlayed < 3) {
            showRoundResult("Round ${roundsPlayed + 1}. Your score: $userScore")
        } else {
            showGameOver(userScore)
        }
    }

    private fun rollDice(): Int {
        return Random.nextInt(1, 7)
    }

    private fun showRoundResult(message: String) {
        welcomeText.text = message
        rollButton.text = "Roll"
        userChoice = ""
    }

    private fun showGameOver(score: Int) {
        if (score == 3) {
            welcomeText.text = "Congratulations! You won! Play again?"
        } else {
            welcomeText.text = "Game over! Better luck next time. Play again?"
        }

        rollButton.text = "Play Again"
        rollButton.setOnClickListener {
            resetGame()
        }
    }


    private fun resetGame() {
        userScore = 0
        roundsPlayed = 0
        showWelcomeScreen()
        rollButton.setOnClickListener {
            if (roundsPlayed < 3) {
                playRound()
            } else {
                showGameOver(userScore)
                resetGame() // Reset again after game over and play again
            }
        }
    }

}
