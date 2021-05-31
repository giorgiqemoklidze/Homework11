package com.example.homework11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.example.homework11.databinding.ActivityMainBinding
import com.google.gson.JsonArray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter: RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            var items = getitems()

            CoroutineScope(Dispatchers.Main).launch {


                    adapter = RecyclerViewAdapter(items)
                    binding.recyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
                    binding.recyclerView.adapter = adapter

            }



        }

    }



    private suspend fun getitems(): List<Model>? {
        val result =  RetrofitService.RetrofitService().getCountry()
        if(result.isSuccessful){
            var items =  result.body()

            return items

        }else{
           return null
        }

    }
}