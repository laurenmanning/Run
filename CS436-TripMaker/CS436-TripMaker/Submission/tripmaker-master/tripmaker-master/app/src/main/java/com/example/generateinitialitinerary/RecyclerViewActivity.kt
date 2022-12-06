package com.example.TripMaker

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Mms.Draft
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.TripMaker.databinding.PreviousItineraryBinding
import com.example.generateinitialitinerary.AlbumsActivity
import com.example.generateinitialitinerary.TripDumby
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_draft_itinerary.*

class RecyclerViewActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Use the provided ViewBinding class to inflate the layout.
        val binding = PreviousItineraryBinding.inflate(layoutInflater)
        //val binding: MainActivityBinding = DataBindingUtil.setContentView(this, MainActivityBinding)

        // Set content view to the binding root view.
        setContentView(binding.root)


        //TODO: This gets the location name. Call firebase from here
        var name = intent.getStringExtra("location_name")

        var dataSet = ArrayList<DraftModelClass>()
        binding.tripListContainer.adapter = PreviousItineraryAdapter(dataSet)
        binding.tripListContainer.layoutManager = LinearLayoutManager(this)

        val databaseRef = FirebaseDatabase.getInstance().getReference("${FirebaseAuth.getInstance().currentUser?.uid}/$name")

        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.i("info_q", "${snapshot.children.count()}")

                snapshot.children.forEach { it ->
                    dataSet.add(it.getValue<TripDumby>()?.toDraftModel() as DraftModelClass)
                }

                (binding.tripListContainer.adapter as PreviousItineraryAdapter).setList(dataSet)
                (binding.tripListContainer.adapter as PreviousItineraryAdapter).notifyDataSetChanged()


            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("info_q", "cancelled")
            }

        })

        binding.albumButton.setOnClickListener {
            val intent = Intent(this, AlbumsActivity::class.java)
            intent.putExtra("title", name)
            startActivity(intent)
        }

        binding.previousButton.setOnClickListener {
            //binding.root.context
            finish()
        }
    }
}