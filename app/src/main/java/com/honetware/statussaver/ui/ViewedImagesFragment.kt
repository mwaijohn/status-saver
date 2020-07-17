package com.honetware.statussaver.ui

import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.honetware.statussaver.R
import com.honetware.statussaver.adapters.ViewedImageAdapter
import com.honetware.statussaver.apputils.App
import com.honetware.statussaver.apputils.Constants
import java.io.File


class ViewedImagesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val root: View = inflater.inflate(R.layout.fragment_viewed_images, container, false)
        val recyclerView= root.findViewById<RecyclerView>(R.id.recyclerView)

        val layout = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layout
        val  adapter = ViewedImageAdapter(requireContext())
        recyclerView.adapter = adapter

        val sourceDirectory = (Environment.getExternalStorageDirectory().absoluteFile).toString() + "/" + Constants.whatsAppUrl
        val files = App.getListFiles(File(sourceDirectory))


        adapter.setData(files)

        return root
    }

}
