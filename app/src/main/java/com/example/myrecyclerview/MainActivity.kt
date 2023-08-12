package com.example.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Hero>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvHeroes = findViewById(R.id.rvHeroes)
        rvHeroes.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecylerList()
    }

    // Memasukkan data ke data class Hero
    private fun getListHeroes(): ArrayList<Hero>{
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescripton = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listHero = ArrayList<Hero>()

        for (i in dataName.indices){
            val hero = Hero(dataName[i], dataDescripton[i], dataPhoto.getResourceId(i, -1))
            listHero.add(hero)
        }

        return listHero
    }

    // Memasukkan ke Adapter
    private fun showRecylerList(){
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapeter = ListHeroAdapeter(list)
        rvHeroes.adapter = listHeroAdapeter

        listHeroAdapeter.setOnItemClickCallback(object : ListHeroAdapeter.OnItemClickCallback{
            override fun onItemClickCallback(data: Hero) {
                selectedHeroes(data)
            }
        })
    }

    private fun selectedHeroes(hero: Hero){
        Toast.makeText(this, "Kamu Memilih ${hero.name}", Toast.LENGTH_LONG).show()
    }

    // Membuat Grid Layout
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_list -> {
                rvHeroes.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvHeroes.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}