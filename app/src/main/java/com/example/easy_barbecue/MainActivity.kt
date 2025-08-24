package com.example.easy_barbecue

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.easy_barbecue.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            validationBarbecueData()
        }
    }

    private fun validationBarbecueData() {
        val numAdults = binding.tieAdults.text.toString().toIntOrNull()
        val numChildren = binding.tieChildren.text.toString().toIntOrNull()
        val totalDuration =
            binding.tieDuration.text?.toString()?.replace(',', '.')?.trim()?.toDoubleOrNull()

        if (numAdults == null || numChildren == null || totalDuration == null) {
            Snackbar.make(binding.root, "Preencha todos os campos!", Snackbar.LENGTH_LONG).show()
            return
        }

        val longDuration = totalDuration >= 6f

        val meatAdult = if (longDuration) MEAT_ADULT_OVER6H else MEAT_ADULT_UNTIL6H
        val meatChildren = if (longDuration) MEAT_CHILDREN_OVER6H else MEAT_CHILDREN_UNTIL6H
        val beerAdult = if (longDuration) BEER_ADULT_OVER6H else BEER_ADULT_UNTIL6H
        val softDrinkAdult = if (longDuration) SOFT_DRINK_ADULT_OVER6H else SOFT_DRINK_ADULT_UNTIL6H
        val softDrinkChildren =
            if (longDuration) SOFT_DRINK_CHILDREN_OVER6H else SOFT_DRINK_CHILDREN_UNTIL6H

        val totalMeat = meatAdult * numAdults + meatChildren * numChildren
        val totalBeer = beerAdult * numAdults
        val totalSoftDrink = softDrinkAdult * numAdults + softDrinkChildren * numChildren

        val intent = Intent(this, ResultsActivity::class.java).apply {
            putExtra("totalMeat", totalMeat)
            putExtra("totalBeer", totalBeer)
            putExtra("totaSoftDrink", totalSoftDrink)
        }
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        clearInputs()
    }

    private fun clearInputs() {
        binding.tieAdults.text?.clear()
        binding.tieChildren.text?.clear()
        binding.tieDuration.text?.clear()
    }

    companion object {
        val MEAT_ADULT_UNTIL6H = 400
        val MEAT_CHILDREN_UNTIL6H = 200
        val MEAT_ADULT_OVER6H = 650
        val MEAT_CHILDREN_OVER6H = 325

        val BEER_ADULT_UNTIL6H = 1.2
        val BEER_ADULT_OVER6H = 2.0

        val SOFT_DRINK_ADULT_UNTIL6H = 1.0
        val SOFT_DRINK_CHILDREN_UNTIL6H = 0.5
        val SOFT_DRINK_ADULT_OVER6H = 1.5
        val SOFT_DRINK_CHILDREN_OVER6H = 0.75
    }
}
