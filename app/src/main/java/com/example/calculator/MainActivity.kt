package com.example.calculator
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    var digitOnScreen = StringBuilder(12)
    var operation: Char = ' '
    var leftHandSide: Double = 0.0
    var rightHandSide: Double = 0.0
    var calculationHistory = StringBuilder()

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
        val oneBtn: Button = findViewById(R.id.one_btn)
        val twoBtn: Button = findViewById(R.id.two_btn)
        val threeBtn: Button = findViewById(R.id.three_btn)
        val fourBtn: Button = findViewById(R.id.four_btn)
        val fiveBtn: Button = findViewById(R.id.five_btn)
        val sixBtn: Button = findViewById(R.id.six_btn)
        val sevenBtn: Button = findViewById(R.id.seven_btn)
        val eightBtn: Button = findViewById(R.id.eight_btn)
        val nineBtn: Button = findViewById(R.id.nine_btn)
        val zeroBtn: Button = findViewById(R.id.zero_btn)
        val dotBtn: Button = findViewById(R.id.dot_btn)

        oneBtn.setOnClickListener { appendToDigitOnScreen("1") }
        twoBtn.setOnClickListener { appendToDigitOnScreen("2") }
        threeBtn.setOnClickListener { appendToDigitOnScreen("3") }
        fourBtn.setOnClickListener { appendToDigitOnScreen("4") }
        fiveBtn.setOnClickListener { appendToDigitOnScreen("5") }
        sixBtn.setOnClickListener { appendToDigitOnScreen("6") }
        sevenBtn.setOnClickListener { appendToDigitOnScreen("7") }
        eightBtn.setOnClickListener { appendToDigitOnScreen("8") }
        nineBtn.setOnClickListener { appendToDigitOnScreen("9") }
        zeroBtn.setOnClickListener { appendToDigitOnScreen("0") }
        dotBtn.setOnClickListener { appendToDigitOnScreen(".") }
    }

    private fun appendToDigitOnScreen(digit: String) {
        digitOnScreen.append(digit)
        val resultTextView: TextView = findViewById(R.id.result_id)
        resultTextView.text = digitOnScreen.toString()
    }

    private fun operationalButtons() {
        val additionBtn: Button = findViewById(R.id.addition_btn)
        val subtractBtn: Button = findViewById(R.id.subtract_btn)
        val divideBtn: Button = findViewById(R.id.divide_btn)
        val multiplyBtn: Button = findViewById(R.id.multipy_btn)

        additionBtn.setOnClickListener { selectOperation('A') }
        subtractBtn.setOnClickListener { selectOperation('S') }
        divideBtn.setOnClickListener { selectOperation('D') }
        multiplyBtn.setOnClickListener { selectOperation('M') }
    }

    private fun selectOperation(c: Char) {
        operation = c
        leftHandSide = digitOnScreen.toString().toDouble()
        digitOnScreen.clear()
        val resultTextView: TextView = findViewById(R.id.result_id)
        resultTextView.text = "0"
    }

    private fun functionalButtons() {
        val resultTextView: TextView = findViewById(R.id.result_id)
        val clearEverythingBtn: Button = findViewById(R.id.clear_everything_btn)
        val clearBtn: Button = findViewById(R.id.clear_btn)
        val backspaceBtn: ImageButton = findViewById(R.id.backspace_btn)
        val equalBtn: Button = findViewById(R.id.equal_btn)

        clearEverythingBtn.setOnClickListener {
            digitOnScreen.clear()
            calculationHistory.clear()
            resultTextView.text = "0"
//            val historyTextView: TextView = findViewById(R.id.history_id)
//            historyTextView.text = ""
        }

        clearBtn.setOnClickListener {
            if (digitOnScreen.length <= 0) {
                return@setOnClickListener
            } else {
                clearDigit()
            }
        }

        backspaceBtn.setOnClickListener {
            if (digitOnScreen.length <= 0) {
                return@setOnClickListener
            } else {
                clearDigit()
            }
        }

        equalBtn.setOnClickListener {
            performMathOperation()
        }
    }

    private fun performMathOperation() {
        rightHandSide = digitOnScreen.toString().toDouble()
        val resultTextView: TextView = findViewById(R.id.result_id)

        when (operation) {
            'A' -> {
                val sum = leftHandSide + rightHandSide
                resultTextView.text = sum.toString()
                calculationHistory.append("${leftHandSide.toInt()} + ${rightHandSide.toInt()} = $sum\n")
            }
            'S' -> {
                val subtract = leftHandSide - rightHandSide
                resultTextView.text = subtract.toString()
                calculationHistory.append("${leftHandSide.toInt()} - ${rightHandSide.toInt()} = $subtract\n")
            }
            'M' -> {
                val multiply = leftHandSide * rightHandSide
                resultTextView.text = multiply.toString()
                calculationHistory.append("${leftHandSide.toInt()} * ${rightHandSide.toInt()} = $multiply\n")
            }
            'D' -> {
                val divide = leftHandSide / rightHandSide
                resultTextView.text = divide.toString()
                calculationHistory.append("${leftHandSide.toInt()} / ${rightHandSide.toInt()} = $divide\n")
            }
        }

//        val historyTextView: TextView = findViewById(R.id.history_id)
//        historyTextView.text = calculationHistory.toString()
    }

    private fun clearDigit() {
        val resultTextView: TextView = findViewById(R.id.result_id)
        val length = digitOnScreen.length
        digitOnScreen.deleteCharAt(length - 1)
        if (length <= 0) {
            resultTextView.text = "0"
        } else {
            resultTextView.text = digitOnScreen.toString()
        }
    }
}
