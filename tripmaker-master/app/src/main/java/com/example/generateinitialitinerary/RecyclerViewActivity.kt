package com.example.TripMaker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.TripMaker.databinding.PreviousItineraryBinding
import kotlinx.android.synthetic.main.fragment_draft_itinerary.*

class RecyclerViewActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Use the provided ViewBinding class to inflate the layout.
        val binding = PreviousItineraryBinding.inflate(layoutInflater)
        //val binding: MainActivityBinding = DataBindingUtil.setContentView(this, MainActivityBinding)

        // Set content view to the binding root view.
        setContentView(binding.root)

        //TODO: Delete this. For testing only
        var list = arrayListOf<DraftModelClass>()
        var types = listOf<String>()
        var item1 = DraftModelClass("Name 1", "Address 1", "5", "airport", false, true, types, 1)
        var item2 = DraftModelClass("Name 2", "Address 2", "4", "airport", false, true, types, 2)
        var item3 = DraftModelClass("Name 3", "Address 3", "3", "airport", false, true, types, 3)
        list.add(item1)
        list.add(item2)
        list.add(item3)


        //TODO: This gets the location name. Call firebase from here
        var name = intent.getStringExtra("location_name")


        binding.tripListContainer.layoutManager = LinearLayoutManager(this)
        binding.tripListContainer.adapter = PreviousItineraryAdapter(list)

        binding.albumButton.setOnClickListener {
            //TODO: use this button to link to the album
        }

        binding.previousButton.setOnClickListener {
            //binding.root.context
            finish()
        }
    }
}