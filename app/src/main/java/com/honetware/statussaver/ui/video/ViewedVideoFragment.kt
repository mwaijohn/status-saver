package com.honetware.statussaver.ui.video

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.honetware.statussaver.R
import com.honetware.statussaver.adapters.ViewedVideoAdapter
import com.honetware.statussaver.apputils.App


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

        val files = App.getVideoFilesFromDirectories()


        if(files.isEmpty()){
            val emptyList = root.findViewById<TextView>(R.id.emptyList)
            emptyList.visibility = View.VISIBLE
        }else{
            adapter.setData(files)
        }

        return root
    }

}
