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
import com.honetware.statussaver.adapters.SavedVideoAdapter
import com.honetware.statussaver.apputils.App
import com.honetware.statussaver.apputils.Constants
import kotlinx.android.synthetic.main.fragment_viewed_video.*

import java.io.File

class SavedVideoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_viewed_video, container, false)
        val recyclerView= root.findViewById<RecyclerView>(R.id.recyclerView)

        val layout = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = layout
        val  adapter = SavedVideoAdapter(requireContext())
        recyclerView.adapter = adapter

        val sourceDirectory = (Environment.getExternalStorageDirectory().absoluteFile).toString() + "/" + Constants.saveLocation
        val files = App.getListFilesVideo(File(sourceDirectory))

        if (files != null) {
            if(files.isEmpty()){
                emptyList.visibility = View.VISIBLE
            }
        }

        adapter.setData(files)

        return root
    }

}
