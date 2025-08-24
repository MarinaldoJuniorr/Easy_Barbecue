package com.example.easy_barbecue

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.easy_barbecue.databinding.ResultsBinding

class ResultsActivity : AppCompatActivity() {

    private lateinit var binding: ResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val totalMeat = intent.getIntExtra("totalMeat", 0)
        val totalBeer = intent.getDoubleExtra("totalBeer", 0.0)
        val totaSoftDrink = intent.getDoubleExtra("totaSoftDrink", 0.0)

        val meatKg = totalMeat / 1000.0
        val beerCans = ((totalBeer / 0.35).toInt() + if ((totalBeer % 0.35) != 0.0) 1 else 0)
        val sodaLiters = totaSoftDrink

        binding.tvMeatResult.text = getString(R.string.meat_result, meatKg)
        binding.tvBeerResult.text = getString(R.string.beer_result, beerCans)
        binding.tvSodaResult.text = getString(R.string.soft_drink_result, sodaLiters)

        binding.btnRestart.setOnClickListener {
            finish()
        }
    }
}
