package com.example.generateinitialitinerary

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.TripMaker.R
import com.example.TripMaker.databinding.NewPhotoFragmentLayoutBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class NewPhotoFragment: Fragment(R.layout.new_photo_fragment_layout) {

    private lateinit var binding: NewPhotoFragmentLayoutBinding
    private lateinit var viewModel: UniversalViewModel

    private var img: Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = NewPhotoFragmentLayoutBinding.inflate(inflater, container, false)
        viewModel = (requireActivity() as AlbumsActivity).viewModel

        binding.takePhotoFAB.setOnClickListener {
            val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePhotoIntent, 42)
        }

        binding.buttonFinished.setOnClickListener {
            if (binding.editTextPhotoName.text.toString() == "") {
                Toast.makeText(requireActivity(), "Must Insert Photo Name", Toast.LENGTH_SHORT).show()
            } else if (img == null) {
                Toast.makeText(requireActivity(), "Must Take Photo", Toast.LENGTH_SHORT).show()
            } else {

                val bos = ByteArrayOutputStream()
                (img as Bitmap).compress(Bitmap.CompressFormat.JPEG, 100, bos)
                val compressedImage = bos.toByteArray()

                val storageRef = FirebaseStorage.getInstance().reference.child(
                    "${FirebaseAuth.getInstance().currentUser?.uid}/images/${viewModel.taskTitle}" +
                            "/${binding.editTextPhotoName.text.toString()}")
                storageRef.putBytes(compressedImage).addOnCompleteListener {task ->
                    if (task.isSuccessful) {
                        Log.e("test_tag", "successful put")
                        (requireActivity() as AlbumsActivity).fetchTask()
                        requireActivity().supportFragmentManager.popBackStack()
                    } else {
                        Log.e("test_tag", "failed put")
                    }
                }
            }
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 42 && resultCode == Activity.RESULT_OK) {
            img = data?.extras?.get("data") as Bitmap
            binding.imageView2.setImageBitmap(img)
        }
    }

}