package com.example.TripMaker

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCaller
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.TripMaker.DraftModelClass
import com.example.TripMaker.R
import kotlinx.android.synthetic.main.new_destination.view.*
import kotlin.random.Random


class DraftAdapter(context: DraftItinerary, arrayList: ArrayList<DraftModelClass>, arrayList2: ArrayList<DraftModelClass>):
    RecyclerView.Adapter<DraftAdapter.ViewHolder>(){

    private val context : Context
    val arrayList: ArrayList<DraftModelClass>
    private val arrayList2: ArrayList<DraftModelClass>

    init {
        this.context = context.requireContext()
        this.arrayList = arrayList
        this.arrayList2 = arrayList2
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int):
            ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.new_destination, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelClass = arrayList[position]

        holder.itemView.textView1.text = modelClass.name

        holder.itemView.textView2.text = "Vicinity: " + modelClass.address

        holder.itemView.textView3.text = "Rating: " + modelClass.rating

        holder.itemView.textView4.text = "Time in Hours: " + modelClass.time

        if (modelClass.isSelected){
            holder.itemView.setBackgroundColor(context.resources.getColor(androidx.appcompat.R.color.highlighted_text_material_dark))
        }
        else{
            holder.itemView.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun replaceItem(position: Int) {
        arrayList[position] = arrayList2[Random.nextInt(0, arrayList2.size)]
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnLongClickListener {

                //Creates a pop up asking to delete the specified item
                var builder = AlertDialog.Builder(context)
                builder.setTitle(R.string.confirm_replace)
                builder.setMessage("Are you sure you want to replace this item?: ${arrayList[this.adapterPosition].name}")
                builder.setPositiveButton(R.string.yes, DialogInterface.OnClickListener { dialog, id ->
                    //Replaces item from the list
                    replaceItem(this.adapterPosition)
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

    private fun setMultipleSelection(adapterPosition: Int){
        arrayList[adapterPosition].isSelected = !arrayList[adapterPosition].isSelected

        notifyDataSetChanged()
    }

}