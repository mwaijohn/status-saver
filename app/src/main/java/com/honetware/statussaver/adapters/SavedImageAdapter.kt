package com.honetware.statussaver.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.honetware.statussaver.R
import com.honetware.statussaver.ui.viewfile.SavedImageActivity
import java.io.File


class SavedImageAdapter(private val context: Context): RecyclerView.Adapter<SavedImageAdapter.AdapterViewHolder>() {
    private var data: ArrayList<File> = ArrayList()

    inner class AdapterViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.pictures_lists, parent, false)) ,View.OnClickListener{

        private val image: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(file: File){
            val imageUri = Uri.fromFile(file)
            //load image to view

            Glide.with(context).
            load(imageUri.path)
                .centerCrop()
                .into(image)

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val intent = Intent(context as Activity, SavedImageActivity::class.java)
            intent.putExtra("filePosition",adapterPosition)
            val activity = context as Activity
            activity.startActivity(intent)
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
        val file = data[position]
        holder.bind(file)
    }
}