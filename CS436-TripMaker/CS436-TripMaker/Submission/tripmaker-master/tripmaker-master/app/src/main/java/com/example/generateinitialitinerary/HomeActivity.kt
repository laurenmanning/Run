package com.example.TripMaker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.TripMaker.databinding.ActivityHomeBinding
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logoutButton.setOnClickListener {
            val intent = Intent(this, FirstActivity::class.java)
            startActivity(intent)
        }

        binding.button3.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }



    }

    fun updateList() {
        var results = ArrayList<String>()
        binding.tripListContainer.adapter = RecyclerViewAdapter(results)
        binding.tripListContainer.layoutManager = LinearLayoutManager(this)

        val databaseRef = FirebaseDatabase.getInstance().getReference("${FirebaseAuth.getInstance().currentUser?.uid}")

        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.i("info_q", "${snapshot.children.count()}")
                snapshot.children.forEach { it ->
                    Log.i("info_q", "${it.ref.path.back.asString()}")
                    results.add(it.ref.path.back.asString())
                }
                (binding.tripListContainer.adapter as RecyclerViewAdapter).setList(results)
                (binding.tripListContainer.adapter as RecyclerViewAdapter).notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("info_q", "cancelled")
            }

        })
    }

    override fun onResume() {
        super.onResume()
        updateList()
    }


}