package com.ifs21035.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21035.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataFamily =ArrayList<Family>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvFamily.setHasFixedSize(false)
        dataFamily.addAll(getListFamily())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Recycle")
    private fun getListFamily(): ArrayList<Family>{
        val dataName = resources.getStringArray(R.array.families_name)
        val dataIcon = resources.obtainTypedArray(R.array.families_icon)
        val dataDescription = resources.getStringArray(R.array.families_description)
        val dataPeriod = resources.getStringArray(R.array.families_period)
        val dataPhysics = resources.getStringArray(R.array.families_physics)
        val dataHabitat = resources.getStringArray(R.array.families_habitat)
        val dataBehavior = resources.getStringArray(R.array.families_behaviour)
        val dataClassification = resources.getStringArray(R.array.families_classification)
        val listFamily = ArrayList<Family>()
        for(i in dataName.indices){
            val family = Family(dataName[i],
                dataIcon.getResourceId(i,-1), dataDescription[i],
                dataPeriod[i], dataPhysics[i], dataHabitat[i], dataBehavior[i],dataClassification[i])
            listFamily.add(family)
        }
        return listFamily
    }
    private fun showRecyclerList(){
        if(resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE){
            binding.rvFamily.layoutManager = GridLayoutManager(this,2)
        } else {
            binding.rvFamily.layoutManager = LinearLayoutManager(this)
        }
        val listFamilyAdapter = ListFamilyAdapter(dataFamily)
        binding.rvFamily.adapter = listFamilyAdapter
        listFamilyAdapter.setOnItemClickCallback(object :
            ListFamilyAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Family) {
                showSelectedGame(data)
            }
        })
    }
    private fun showSelectedGame(family: Family){
        val intentWithData = Intent(this@MainActivity,DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_FAMILY, family)
        startActivity(intentWithData)
    }
}