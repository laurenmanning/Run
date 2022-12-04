package com.example.TripMaker

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.TripMaker.databinding.ActivityMapsBinding


class MapsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMapsBinding.inflate(layoutInflater).root)

    }
}