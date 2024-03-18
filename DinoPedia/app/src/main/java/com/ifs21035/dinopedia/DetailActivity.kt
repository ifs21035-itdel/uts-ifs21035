package com.ifs21035.dinopedia

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ifs21035.dinopedia.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var family: Family? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        family = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_FAMILY,
                Family::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILY)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (family != null) {
            supportActionBar?.title = "Family Details"
            loadData(family!!)
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

    private fun loadData(family: Family) {
        binding.ivDetailFamilyIcon.setImageResource(family.icon)
        binding.tvDetailFamilyName.text = family.name
        binding.tvDetailDescription.text = family.description
        binding.tvDetailPeriod.text = family.period
        binding.tvDetailPhysics.text = family.physics
        binding.tvDetailHabitat.text = family.habitat
        binding.tvDetailBehaviour.text = family.behaviour
        binding.tvDetailClassification.text = family.classification
    }

    companion object {
        const val EXTRA_FAMILY = "extra_family"
    }
}