package com.honetware.statussaver.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.honetware.statussaver.R
import com.honetware.statussaver.adapters.ViewedImageAdapter
import com.honetware.statussaver.apputils.App


class ViewedImagesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val root: View = inflater.inflate(R.layout.fragment_viewed_images, container, false)
        val recyclerView= root.findViewById<RecyclerView>(R.id.recyclerView)

        val layout = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layout
        val  adapter = ViewedImageAdapter(requireContext())
        recyclerView.adapter = adapter

        val files = App.getImageFilesFromDirectories()


        if(files.isEmpty()){
            val emptyList = root.findViewById<TextView>(R.id.emptyList)
            emptyList.visibility = View.VISIBLE
        }else{
            adapter.setData(files)

        }
        return root
    }

}
