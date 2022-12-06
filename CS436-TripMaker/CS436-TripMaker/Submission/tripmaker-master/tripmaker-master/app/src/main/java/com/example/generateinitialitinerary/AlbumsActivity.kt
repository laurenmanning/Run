package com.example.generateinitialitinerary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.*
import androidx.lifecycle.SavedStateHandle
import com.example.TripMaker.R
import com.example.TripMaker.databinding.AlbumActivityBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage

class AlbumsActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: AlbumActivityBinding
    lateinit var uid: String
    lateinit var viewModel: UniversalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AlbumActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        uid = FirebaseAuth.getInstance().currentUser?.uid as String

        if (savedInstanceState == null) {
            viewModel = UniversalViewModel(SavedStateHandle())
            viewModel.bindToActivityLifeCycle(this)

            Log.e("testing_tag", "onCreate no bundle called for user $uid")

            fetchTask()

        } else {
            Log.e("testing_tag", "onCreate with bundle called")
        }

    }

    fun addPhoto(title: String) {
        Log.e("testing_tag", "add photo called")
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<NewPhotoFragment>(R.id.fragmentContainer)
            addToBackStack(null)
        }
    }

    fun fetchTask() {
        val title = intent.getStringExtra("title")
        val albumRef = FirebaseStorage.getInstance().getReference("$uid/images/$title").listAll()

        albumRef.addOnCompleteListener {task ->
            if (task.isSuccessful) {
                Log.e("testing_tag", "success on get list")
                viewModel.setTaskTitle(title as String)
                val temp = ArrayList<String>()
                task.result.items.forEach { it ->
                    temp.add(it.path)
                }
                viewModel.setTaskResult(temp)

                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<AlbumFragment>(R.id.fragmentContainer)
                    addToBackStack(null)
                }

            } else {
                Log.e("testing_tag", "failed on get list")
            }
        }
    }

}