package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private val TAG = "GameViewModel"
    private val _score = MutableLiveData<Int>(0)
    private val _currentWordCount =  MutableLiveData<Int>(0)
    private val _currentScrambledWord = MutableLiveData<String>()
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String

    val currentScrambledWord: LiveData<String>
        get() = _currentScrambledWord
    val score: LiveData<Int>
        get() = _score
    val currentWordCount: LiveData<Int>
        get() = _currentWordCount

    private fun getNextWord() {
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        tempWord.shuffle()

        while (String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }

        if (wordsList.contains(currentWord)) {
            getNextWord()
        } else {
            _currentScrambledWord.value = String(tempWord)
            _currentWordCount.value = (_currentWordCount.value)?.inc()
            wordsList.add(currentWord)
        }
    }

    init {
        Log.d(TAG, "GameViewModel created!")
        getNextWord()
    }

    private fun increaseScore() {
        _score.value = (_score.value)?.plus(SCORE_INCREASE)
        Log.d(TAG, "increaseScore: score is now $_score")
    }

    fun nextWord() : Boolean {
        if (_currentWordCount.value!! < MAX_NO_OF_WORDS) {
            getNextWord()
            return true
        } else {
            return false
        }

    }

    fun isUserWordCorrect(playedWord: String): Boolean {
        if (playedWord.equals(currentWord, true)) {
            Log.d(TAG, "correct word")
            increaseScore()
            return true
        } else {
            return false
        }
    }


    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "GameViewModel destroyed!")
    }

    fun reinitializeData() {
        _score.value = 0
        _currentWordCount.value = 0
        wordsList.clear()
        getNextWord()
    }


}