package com.example.calculatortest

import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val answerField = findViewById<TextView>(R.id.textView)
        val inputField = findViewById<TextView>(R.id.inputFieldValue)

        for (i in 0..9) {
            val btnId = resources.getIdentifier("button$i", "id", packageName)
            println(R.id.button1)
            val btn = findViewById<Button>(btnId)
                btn.setOnClickListener {
                    "${inputField.text}${btn.text}".also { inputField.text = it }
            }
        }
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
        findViewById<Button>(R.id.buttonEqual).setOnClickListener {
            answerField.text = evaluateInput(inputField.text)
        }
    }

    private fun evaluateInput(text: CharSequence?): CharSequence? {
        var expressionList = mutableListOf<String>()
        var operatorList = mutableListOf<String>()
        text?.forEach {
            if(it == '/' || it == '*'|| it == '-' || it == '+'){
                operatorList.add(it.toString())
            }
        }
        if (text != null) {
            expressionList = text.split('/','*','-','+') as MutableList<String>
        }
        println(expressionList)
        println(operatorList)
        if(expressionList.contains(element = null)){

        }

        return text
    }
}
