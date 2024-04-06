package com.example.lab2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import android.widget.ImageButton
import com.example.lab2.Model.entity.Dog
import com.example.lab2.Model.service.ApiClient
import com.example.lab2.adapter.DogAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var searchEditText: EditText
    private lateinit var searchButton: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var dogAdapter: DogAdapter

    private var dogList = mutableListOf<Dog>()
    private var filteredDogList = mutableListOf<Dog>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchEditText = findViewById(R.id.searchEditText)
        searchButton = findViewById(R.id.searchButton)
        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        dogAdapter = DogAdapter(dogList)
        recyclerView.adapter = dogAdapter

        searchButton.setOnClickListener {
            filterDogList(searchEditText.text.toString())
        }

        val apiService = ApiClient.create()

        apiService.getDogs(minWeight = 1).enqueue(object : Callback<List<Dog>> {
            override fun onResponse(call: Call<List<Dog>>, response: Response<List<Dog>>) {
                if (response.isSuccessful) {
                    val dogs = response.body()
                    dogs?.let {
                        dogList.addAll(it)
                        dogAdapter.notifyDataSetChanged()
                    }
                } else {
                    println("Error: ${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Dog>>, t: Throwable) {
                println("Failure: ${t.message}")
            }
        })
    }

    private fun filterDogList(query: String) {
        filteredDogList.clear()
        for (dog in dogList) {
            if (dog.name.contains(query, ignoreCase = true)) {
                filteredDogList.add(dog)
            }
        }
        dogAdapter.updateList(filteredDogList)
    }
}
