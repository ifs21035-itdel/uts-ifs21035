package com.ifs21035.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21035.dinopedia.databinding.ActivityDinoMainBinding

class DinoMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDinoMainBinding
    private val dataDinosaur =ArrayList<Dinosaur>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvDinosaur.setHasFixedSize(false)
        dataDinosaur.addAll(getListDinosaur())
        showRecyclerList()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Dinosaur List"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    @SuppressLint("Recycle")
    private fun getListDinosaur(): ArrayList<Dinosaur>{
        val family = if(Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAMILY, Family::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILY)
        }

        val dataName = resources.getStringArray(R.array.dinosaur_name)
        val dataIcon = resources.obtainTypedArray(R.array.dinosaur_icon)
        val dataDescription = resources.getStringArray(R.array.dinosaur_description)
        val dataCharacter = resources.getStringArray(R.array.dinosaur_characteristic)
        val dataGroup = resources.getStringArray(R.array.dinosaur_group)
        val dataHabitat = resources.getStringArray(R.array.dinosaur_habitat)
        val dataFood = resources.getStringArray(R.array.dinosaur_food)
        val dataLength = resources.getStringArray(R.array.dinosaur_length)
        val dataHeight = resources.getStringArray(R.array.dinosaur_height)
        val dataWeight = resources.getStringArray(R.array.dinosaur_weight)
        val dataWeakness = resources.getStringArray(R.array.dinosaur_weakness)

        val start = family?.start
        val end = family?.end

        val listDinosaur = ArrayList<Dinosaur>()
        for(i in start!!..end!!){
            val dinosaur = Dinosaur(dataName[i],
                dataIcon.getResourceId(i,-1), dataDescription[i],
                dataCharacter[i], dataGroup[i], dataHabitat[i], dataFood[i],dataLength[i],
                dataHeight[i],dataWeight[i],dataWeakness[i])
            listDinosaur.add(dinosaur)
        }
        return listDinosaur
    }
    private fun showRecyclerList(){
        if(resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE){
            binding.rvDinosaur.layoutManager = GridLayoutManager(this,2)
        } else {
            binding.rvDinosaur.layoutManager = LinearLayoutManager(this)
        }
        val listDinosaurAdapter = ListDinoAdapter(dataDinosaur)
        binding.rvDinosaur.adapter = listDinosaurAdapter
        listDinosaurAdapter.setOnItemClickCallback(object :
            ListDinoAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Dinosaur) {
                showSelectedDino(data)
            }
        })
    }
    private fun showSelectedDino(dinosaur: Dinosaur){
        val intentWithData = Intent(this@DinoMainActivity,DinoActivity::class.java)
        intentWithData.putExtra(DinoActivity.EXTRA_DINOSAUR, dinosaur)
        startActivity(intentWithData)
    }

    companion object {
        const val EXTRA_FAMILY = "extra_family"
    }
}