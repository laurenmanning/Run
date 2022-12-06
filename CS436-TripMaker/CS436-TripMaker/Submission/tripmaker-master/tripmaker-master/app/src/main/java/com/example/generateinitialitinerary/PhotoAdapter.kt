package com.example.generateinitialitinerary

import android.net.Uri
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.TripMaker.R
import com.google.android.material.internal.ContextUtils.getActivity
import com.google.firebase.storage.FirebaseStorage

class PhotoAdapter(private var storageRefList: ArrayList<String>, private val activity: AlbumsActivity, private val fragment: AlbumFragment) :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    private lateinit var viewHolder : ViewHolder

    class ViewHolder(view: View, private val activity: AlbumsActivity) : RecyclerView.ViewHolder(view) {
        var photoView : ImageView

        init {

            photoView = view.findViewById(R.id.imageView)
            photoView.setOnClickListener {

                //TODO handle photo being clicked on for deleting and such

            }

        }

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.photo_row_item, viewGroup, false)

        return ViewHolder(view, activity)
    }

    //TODO TEST THIS AGAIN
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Log.e("test_tag", "onBind Called in PhotoAdapter")
        this.viewHolder = viewHolder
        if (storageRefList.isNotEmpty()) {
            val ref = storageRefList[position]
            FirebaseStorage.getInstance().getReference(ref).downloadUrl.addOnCompleteListener{task ->
                if (task.isSuccessful) {
                    Log.e("test_tag", "successful download")
                    Glide.with(fragment)
                        .load(task.result.toString())
                        .into(viewHolder.photoView)
                } else {
                    Log.e("test_tag", "unsuccessful download")
                }
            }
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = storageRefList.size

    fun clear() {
        storageRefList = ArrayList<String>()
    }

}