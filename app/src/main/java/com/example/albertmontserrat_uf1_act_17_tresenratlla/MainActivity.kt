package com.example.albertmontserrat_uf1_act_17_tresenratlla

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var board: Array<Array<Button>>
    private var currentPlayer = "X"
    private var boardStatus = Array(3) { IntArray(3) } // 0: empty, 1: X, 2: O

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(findViewById(R.id.button00), findViewById(R.id.button01), findViewById(R.id.button02)),
            arrayOf(findViewById(R.id.button10), findViewById(R.id.button11), findViewById(R.id.button12)),
            arrayOf(findViewById(R.id.button20), findViewById(R.id.button21), findViewById(R.id.button22))
        )

        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j].setOnClickListener {
                    onCellClicked(i, j)
                }
            }
        }

        findViewById<Button>(R.id.resetButton).setOnClickListener {
            resetBoard()
        }

        resetBoard()
    }

    private fun onCellClicked(row: Int, col: Int) {
        if (boardStatus[row][col] == 0) {
            boardStatus[row][col] = if (currentPlayer == "X") 1 else 2
            board[row][col].text = currentPlayer
            if (checkWinner()) {
                Toast.makeText(this, "Guanyador: $currentPlayer", Toast.LENGTH_LONG).show()
                disableBoard()
            } else {
                currentPlayer = if (currentPlayer == "X") "O" else "X"
            }
        }
    }

    private fun checkWinner(): Boolean {
        // Comprovar files
        for (i in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] &&
                boardStatus[i][1] == boardStatus[i][2] &&
                boardStatus[i][0] != 0) {
                return true
            }
        }
        // Comprovar columnes
        for (i in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] &&
                boardStatus[1][i] == boardStatus[2][i] &&
                boardStatus[0][i] != 0) {
                return true
            }
        }
        // Comprovar diagonals
        if (boardStatus[0][0] == boardStatus[1][1] &&
            boardStatus[1][1] == boardStatus[2][2] &&
            boardStatus[0][0] != 0) {
            return true
        }
        if (boardStatus[0][2] == boardStatus[1][1] &&
            boardStatus[1][1] == boardStatus[2][0] &&
            boardStatus[0][2] != 0) {
            return true
        }
        return false
    }

    private fun disableBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j].isEnabled = false
            }
        }
    }

    private fun resetBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                boardStatus[i][j] = 0
                board[i][j].text = ""
                board[i][j].isEnabled = true
            }
        }
        currentPlayer = "X"
    }
}
