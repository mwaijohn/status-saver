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
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val root: View = inflater.inflate(R.layout.fragment_viewed_images, container, false)
        val recyclerView= root.findViewById<RecyclerView>(R.id.recyclerView)

        val layout = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layout
        val  adapter = ViewedImageAdapter(requireContext())
        recyclerView.adapter = adapter

        val compoundFiles = ArrayList<File>()
        val sourceDirectory1 = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.whatsAppBusinessUrl
        val files = App.getListFiles(File(sourceDirectory1))

        val sourceDirectory2 = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.whatsAppUrl
        val files2 = App.getListFiles(File(sourceDirectory2))

        if (files2 != null) {
            compoundFiles.addAll(files2)
        }

        if (files != null) {
            compoundFiles.addAll(files)
        }

        adapter.setData(compoundFiles)

        return root
    }

}
