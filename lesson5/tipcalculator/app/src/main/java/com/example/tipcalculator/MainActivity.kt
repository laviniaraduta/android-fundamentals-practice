package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculateTip() }

    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()

        val selectedTip = binding.tipOptions.checkedRadioButtonId
        val tipPercent = when (selectedTip) {
            R.id.option_eighteen_percent -> 0.18
            R.id.option_twenty_percent -> 0.20
            else -> 0.15
        }

        var calculatedTip = cost * tipPercent
        if (binding.roundUpSwitch.isChecked) {
            calculatedTip = ceil(calculatedTip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(calculatedTip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}