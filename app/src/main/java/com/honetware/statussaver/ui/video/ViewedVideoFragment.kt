package com.honetware.statussaver.ui.video

import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.honetware.statussaver.R
import com.honetware.statussaver.adapters.ViewedVideoAdapter
import com.honetware.statussaver.apputils.App
import com.honetware.statussaver.apputils.Constants
import kotlinx.android.synthetic.main.fragment_viewed_video.*
import java.io.File


class ViewedVideoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_viewed_video, container, false)
        val recyclerView= root.findViewById<RecyclerView>(R.id.recyclerView)

        val layout = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layout
        val  adapter = ViewedVideoAdapter(requireContext())
        recyclerView.adapter = adapter

        val compoundFiles = ArrayList<File>()
        val sourceDirectory1 = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.whatsAppBusinessUrl
        val files = App.getListFilesVideo(File(sourceDirectory1))

        val sourceDirectory2 = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.whatsAppUrl
        val files2 = App.getListFilesVideo(File(sourceDirectory2))

        val sourceDirectory3 = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.whatsAppGbUrl
        val files3 = App.getListFilesVideo(File(sourceDirectory2))

        if (files != null) {
            compoundFiles.addAll(files)
        }

        if (files2 != null) {
            compoundFiles.addAll(files2)
        }

        if (files3 != null) {
            compoundFiles.addAll(files3)
        }
        adapter.setData(compoundFiles)

        if (files != null) {
            if(files.isEmpty()){
                emptyList.visibility = View.VISIBLE
            }
        }

        return root
    }

}
