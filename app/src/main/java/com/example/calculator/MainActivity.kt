package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService

class MainActivity : AppCompatActivity() {

    var digit_on_screen = StringBuilder(12)
    var operation: Char = ' '
    var leftHandSide: Double = 0.0
    var rightHandSide: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultTextView: TextView = findViewById(R.id.result_id)
        resultTextView.text = "0"

        initializeButtons()
    }
    private fun initializeButtons() {
        functionalButtons()
        operationalButtons()
        numericalButtons()
    }

    private fun numericalButtons() {
        val one_btn: Button = findViewById(R.id.one_btn)
        val two_btn: Button = findViewById(R.id.two_btn)
        val three_btn: Button = findViewById(R.id.three_btn)
        val four_btn: Button = findViewById(R.id.four_btn)
        val five_btn: Button = findViewById(R.id.five_btn)
        val six_btn: Button = findViewById(R.id.six_btn)
        val seven_btn: Button = findViewById(R.id.seven_btn)
        val eight_btn: Button = findViewById(R.id.eight_btn)
        val nine_btn: Button = findViewById(R.id.nine_btn)
        val zero_btn: Button = findViewById(R.id.zero_btn)
        val dot_btn: Button = findViewById(R.id.dot_btn)


        one_btn.setOnClickListener {
            appendToDigitOnScreen("1")
        }

        two_btn.setOnClickListener {
            appendToDigitOnScreen("2")
        }

        three_btn.setOnClickListener {
            appendToDigitOnScreen("3")
        }

        four_btn.setOnClickListener {
            appendToDigitOnScreen("4")
        }

        five_btn.setOnClickListener {
            appendToDigitOnScreen("5")
        }

        six_btn.setOnClickListener {
            appendToDigitOnScreen("6")
        }

        seven_btn.setOnClickListener {
            appendToDigitOnScreen("7")
        }

        eight_btn.setOnClickListener {
            appendToDigitOnScreen("8")
        }

        nine_btn.setOnClickListener {
            appendToDigitOnScreen("9")
        }

        zero_btn.setOnClickListener {
            appendToDigitOnScreen("0")
        }

        dot_btn.setOnClickListener {
            appendToDigitOnScreen(".")
        }


    }

    private fun appendToDigitOnScreen(digit: String) {

        digit_on_screen.append(digit)
        val resultTextView: TextView = findViewById(R.id.result_id)

        resultTextView.text = digit_on_screen.toString()
    }


    private fun operationalButtons() {
        val addition_btn: Button = findViewById(R.id.addition_btn)
        val subtract_btn: Button = findViewById(R.id.subtract_btn)
        val divide_btn: Button = findViewById(R.id.divide_btn)
        val multipy_btn: Button = findViewById(R.id.multipy_btn)

        addition_btn.setOnClickListener {
            selectOperation('A')
        }

        subtract_btn.setOnClickListener {
            selectOperation('S')
        }

        divide_btn.setOnClickListener {
            selectOperation('D')
        }

        multipy_btn.setOnClickListener {
            selectOperation('M')
        }

    }

    private fun selectOperation(c: Char) {

        operation = c
        leftHandSide = digit_on_screen.toString().toDouble()
        digit_on_screen.clear()
        val resultTextView: TextView = findViewById(R.id.result_id)

        resultTextView.text = "0"
    }

    private fun functionalButtons() {
        val resultTextView: TextView = findViewById(R.id.result_id)
        val clear_everything_btn: Button = findViewById(R.id.clear_everything_btn)
        val clear_btn: Button = findViewById(R.id.clear_btn)
        val backspace_btn: ImageButton = findViewById(R.id.backspace_btn)
        val equal_btn: Button = findViewById(R.id.equal_btn)

        clear_everything_btn.setOnClickListener {
            digit_on_screen.clear()
            resultTextView.text = "0"
        }

        clear_btn.setOnClickListener {

            if (digit_on_screen.length <= 0) {
                return@setOnClickListener
            } else {
                clearDigit()
            }
        }

        backspace_btn.setOnClickListener {
            if (digit_on_screen.length <= 0) {
                return@setOnClickListener
            } else {
                clearDigit()
            }
        }

        equal_btn.setOnClickListener {
            performMathOperation()
        }

    }


    private fun performMathOperation() {

        rightHandSide = digit_on_screen.toString().toDouble()
        val resultTextView: TextView = findViewById(R.id.result_id)


        when (operation) {

            'A' -> {
                val sum = OperationsHelper.add(leftHandSide, rightHandSide)
                resultTextView.text = sum.toString()
                digit_on_screen.clear()
                digit_on_screen.append(sum)
            }
            'S' -> {
                val subtract = OperationsHelper.subtract(leftHandSide, rightHandSide)
                resultTextView.text = subtract.toString()
                digit_on_screen.clear()
                digit_on_screen.append(subtract)
            }
            'M' -> {
                val multiply = OperationsHelper.multiply(leftHandSide, rightHandSide)
                resultTextView.text = multiply.toString()
                digit_on_screen.clear()
                digit_on_screen.append(multiply)
            }
            'D' -> {
                val divide = OperationsHelper.divide(leftHandSide, rightHandSide)
                resultTextView.text = divide.toString()
                digit_on_screen.clear()
                digit_on_screen.append(divide)
            }

        }

    }

    private fun clearDigit() {
        val resultTextView: TextView = findViewById(R.id.result_id)

        val length = digit_on_screen.length

        digit_on_screen.deleteCharAt(length - 1)
        if (length <= 0) {
            resultTextView.text = "0"
        }else{
            resultTextView.text = digit_on_screen.toString()
        }


    }


}