package com.ifs21035.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21035.dinopedia.databinding.ActivityDinoBinding

class DinoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDinoBinding
    private var dinosaur: Dinosaur? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dinosaur = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_DINOSAUR,
                Dinosaur::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINOSAUR)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dinosaur != null) {
            supportActionBar?.title = "Dinosaur Details"
            loadData(dinosaur!!)
        } else {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadData(dinosaur: Dinosaur){
        binding.ivDetailDinoIcon.setImageResource(dinosaur.icon)
        binding.tvDetailDinoName.text = dinosaur.name
        binding.tvDinoDescription.text = dinosaur.description
        binding.tvDinoCharacteristic.text = dinosaur.characteristic
        binding.tvDinoGroup.text = dinosaur.group
        binding.tvDinoHabitat.text = dinosaur.habitat
        binding.tvDinoFood.text = dinosaur.food
        binding.tvDinoLength.text = dinosaur.length
        binding.tvDinoHeight.text = dinosaur.height
        binding.tvDinoWeight.text = dinosaur.weight
        binding.tvDinoWeakness.text = dinosaur.weakness
    }

    companion object {
        const val EXTRA_DINOSAUR = "extra_dinosaur"
    }
}