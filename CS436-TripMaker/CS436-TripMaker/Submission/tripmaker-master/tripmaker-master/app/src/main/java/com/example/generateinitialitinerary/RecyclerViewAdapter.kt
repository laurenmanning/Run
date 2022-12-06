package com.example.TripMaker

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.TripMaker.databinding.ListItemLayoutBinding
import kotlinx.android.synthetic.main.fragment_draft_itinerary.*

/**
 * A [RecyclerView.Adapter] that displays a list of colors.
 */
class RecyclerViewAdapter(
    private var list: ArrayList<String>
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    /**
     * Called [RecyclerView] to create a new [ViewHolder]
     * that contains an list item [View]. [onBindViewHolder]
     * will be called next.
     */

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    /**
     * Called by [RecyclerView] to bind item at [position] to the passed
     * [ViewHolder]. The [RecyclerView] maintains only a small number
     * of [ViewHolder] instances and therefore needs to recycle, or
     * rebind, those instances as the user scrolls new items into view.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //input the location information
        holder.name.text = list[position]
    }

    fun setList( list: ArrayList<String>) {
        this.list = list
    }

    /**
     * Called by [RecyclerView] when iterating through the list items.
     */
    override fun getItemCount(): Int = list.size

    /**
     * A custom [RecyclerView.ViewHolder] used to display each list item
     * and for handling button click input events.
     */
    inner class ViewHolder(binding: ListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val name: TextView = binding.textView

        init {
            itemView.setOnLongClickListener {

                var builder = AlertDialog.Builder(binding.root.context)
                builder.setTitle(R.string.confirm_load)
                builder.setMessage("Are you sure you want to load this item?: ${name.text}")
                builder.setPositiveButton(R.string.yes, DialogInterface.OnClickListener { dialog, id ->

                    val intent = Intent(binding.root.context, RecyclerViewActivity::class.java)
                    intent.putExtra("location_name", name.text)
                    binding.root.context.startActivity(intent)

                    dialog.cancel()
                })
                builder.setNegativeButton(R.string.no, DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })

                var alert = builder.create()
                alert.show()

                return@setOnLongClickListener true
            }
        }
    }
}
