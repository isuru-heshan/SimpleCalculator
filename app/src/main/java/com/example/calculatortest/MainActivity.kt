package com.example.calculatortest

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.LinkedList
import java.util.Queue
import java.util.Stack

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set main activity view
        setContentView(R.layout.activity_main)

        // text view for display answer
        val answerField = findViewById<TextView>(R.id.textView)
        // text view for display input
        val inputField = findViewById<TextView>(R.id.inputFieldValue)


        // set on click listeners on 0 to 9 buttons
        for (i in 0..9) {
            val btnId = resources.getIdentifier("button$i", "id", packageName)
            println(R.id.button1)
            val btn = findViewById<Button>(btnId)
                btn.setOnClickListener {
                    "${inputField.text}${btn.text}".also { inputField.text = it }
            }
        }

        // every time when user clicks on a button it appends to the input field text value

        // set operator onclick listeners
        findViewById<Button>(R.id.buttonMultiplier).setOnClickListener {
            "${inputField.text}*".also { inputField.text = it }
        }
        findViewById<Button>(R.id.buttonDivider).setOnClickListener {
            "${inputField.text}/".also { inputField.text = it }
        }
        findViewById<Button>(R.id.buttonSubtraction).setOnClickListener {
            "${inputField.text}-".also { inputField.text = it}
        }
        findViewById<Button>(R.id.buttonAddition).setOnClickListener {
            "${inputField.text}+".also { inputField.text = it}
        }
        findViewById<Button>(R.id.buttonDelete).setOnClickListener {
            inputField.text = inputField.text?.dropLast(1)
        }
        findViewById<Button>(R.id.buttonDelete).setOnLongClickListener{
            inputField.text = null
            true
        }

        // set equal button to get answer
        findViewById<Button>(R.id.buttonEqual).setOnClickListener {
            // call calculate function
            if (inputField.text != null) {
                answerField.text = calculate(inputField.text.toString())
            }
        }
    }

    private fun calculate(infixExpression: String): String {
        // to evaluate infix expression convert into a postfix expression and then evaluate it
        return evaluatePostfix(infixToPostfix(infixExpression))
    }

    // rank operator precedence
    private fun rankOperators(operator: Char): Int {
        return when (operator) {
            '+', '-' -> 1
            '*', '/' -> 2
            else -> 0
        }
    }


    // function to convert infix expression to postfix expression
    private fun infixToPostfix(infixExp: String): Queue<String> {
        val answer = LinkedList<String>()
        val stack = Stack<Char>()
        var i = 0
        val expressionLength = infixExp.length
        while (i < expressionLength) {
            val num = StringBuilder()
            while (i < expressionLength && infixExp[i].isDigit()) {
                num.append(infixExp[i++])
            }
            if (num.isNotEmpty()) {
                answer.add(num.toString())
            }
            if (i == expressionLength) {
                break
            }
            val currentChar = infixExp[i++]
            when {
                currentChar.isWhitespace() -> {}
                else -> {
                    while (stack.isNotEmpty() && rankOperators(stack.peek()) >= rankOperators(currentChar)) {
                        answer.add("${stack.pop()}")
                    }
                    stack.push(currentChar)
                }
            }
        }
        while (stack.isNotEmpty()) {
            answer.add("${stack.pop()}")
        }
        return answer
    }


    // evaluate postfix expression and return the result
    private fun evaluatePostfix(postfixExpression: Queue<String>): String {
        val stack = Stack<Double>()
        for (eachItem in postfixExpression) {
            if (eachItem[0].isDigit())
                stack.push(eachItem.toDouble())
            else {
                val value2 = stack.pop()
                val value1 = stack.pop()
                when (eachItem[0]) {
                    '+' -> stack.push(value1 + value2)
                    '-' -> stack.push(value1 - value2)
                    '*' -> stack.push(value1 * value2)
                    else -> {
                        if (value2 == 0.toDouble()){

                            return "Cannot divide by zero"
                        }
                        else{

                            stack.push(value1 / value2)
                        }
                    }
                }
            }
        }
        return stack.pop().toDouble().toString()
    }
}


// there are some functionalities to evaluate infix expression easily,
// but from this method we can clearly see what happens inside the function