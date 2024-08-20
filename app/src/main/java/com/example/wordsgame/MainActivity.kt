package com.example.wordsgame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wordsgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val wordList = listOf("hung", "hoang", "huy", "hanh", "huong", "duc", "phuong", "thao", "dung", "thanh")
    private var currentWordIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setShuffledWord()
        binding.btnSubmit.setOnClickListener {
            checkAnswer()
        }
        binding.btnSkip.setOnClickListener {
            skipWord()
        }
    }

    private fun setShuffledWord() {
        val word = wordList[currentWordIndex]
        binding.tvSrambledWord.text = word.toList().shuffled().joinToString("")
    }

    private fun checkAnswer() {
        val userAnswer = binding.etUserInput.text.toString().trim()
        if (userAnswer.equals(wordList[currentWordIndex], true)) {
            score++
            binding.tvScore.text = "Score: $score"
        }
        if (score == 10) {
            binding.tvSrambledWord.text = "Winner!"
            binding.btnSubmit.isEnabled = false
            binding.btnSkip.isEnabled = false
        } else {
            currentWordIndex = (currentWordIndex + 1) % wordList.size
            setShuffledWord()
            updatePlayCount()
        }
        binding.etUserInput.text.clear()
    }

    private fun skipWord() {
        currentWordIndex = (currentWordIndex + 1) % wordList.size
        setShuffledWord()
        binding.etUserInput.text.clear()
        updatePlayCount()
    }

    private fun updatePlayCount() {
        binding.tvPlayCount.text = "${currentWordIndex + 1}/10"
    }
}