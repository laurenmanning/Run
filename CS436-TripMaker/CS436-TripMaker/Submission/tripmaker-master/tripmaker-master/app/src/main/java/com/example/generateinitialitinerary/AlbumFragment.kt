package com.example.generateinitialitinerary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.TripMaker.R
import com.example.TripMaker.databinding.AlbumFragmentLayoutBinding

class AlbumFragment : Fragment(R.layout.album_fragment_layout) {

    private lateinit var binding : AlbumFragmentLayoutBinding
    private lateinit var viewModel : UniversalViewModel
    //private lateinit var photoList: ArrayList<StorageReference>
    private lateinit var photoAdapter: PhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = AlbumFragmentLayoutBinding.inflate(inflater, container, false)
        viewModel = (requireActivity() as AlbumsActivity).viewModel

        (requireActivity() as AppCompatActivity).supportActionBar?.title = viewModel.taskTitle

        binding.fab.setOnClickListener {
            (requireActivity() as AlbumsActivity).addPhoto(viewModel.taskTitle)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.taskResult.isNotEmpty()) {

            photoAdapter =
                PhotoAdapter(viewModel.taskResult, requireActivity() as AlbumsActivity, this)
            binding.photosContainer.adapter = photoAdapter
            binding.photosContainer.layoutManager = LinearLayoutManager(context)

        }
    }





}