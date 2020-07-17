package com.honetware.statussaver.adapters

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.honetware.statussaver.R
import java.io.File


class ViewedImageAdapter(private val context: Context): RecyclerView.Adapter<ViewedImageAdapter.AdapterViewHolder>() {
    private var data: ArrayList<File> = ArrayList()

    inner class AdapterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.pictures_lists, parent, false)) {

        private val image: TextView = itemView.findViewById(R.id.imageViewImageMedia)

        fun bind(file: File){
            val imageUri = Uri.fromFile(file)
            //load image to view
        }
    }

    //set data and label
    internal fun setData(result: ArrayList<File>?) {
        if (result != null) {
            this.data = result
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val inflater = LayoutInflater.from(context)
        return AdapterViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val dayItem = data[position]
        holder.bind(dayItem)
    }
}