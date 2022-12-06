package com.example.TripMaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.TripMaker.databinding.ActivityHomeBinding
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var list: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logoutButton.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }

        binding.createButton.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        //TODO: set list equal to the firebase data location names
        //list =

        //TODO: Remove this
        //FOR TESTING
        list.add("Location 1")
        list.add("Location 2")
        list.add("Location 3")

        //create recyclerview that lets user click on which trip they want
        binding.tripListContainer.layoutManager = LinearLayoutManager(this)
        binding.tripListContainer.adapter = RecyclerViewAdapter(list)
    }


}