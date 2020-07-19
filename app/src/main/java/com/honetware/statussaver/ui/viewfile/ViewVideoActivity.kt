package com.honetware.statussaver.ui.viewfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.VideoView
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.honetware.statussaver.R
import com.honetware.statussaver.adapters.pageadapter.VideoPagingAdapter
import com.honetware.statussaver.apputils.App
import com.honetware.statussaver.apputils.Constants
import java.io.File

class ViewVideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_image)

//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        supportActionBar?.elevation = 0F

        val viewPager: ViewPager =  findViewById(R.id.pager)


        val compoundFiles = ArrayList<File>()
        val sourceDirectory1 = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.whatsAppBusinessUrl
        val files = App.getListFilesVideo(File(sourceDirectory1))

        val sourceDirectory2 = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.whatsAppUrl
        val files2 = App.getListFilesVideo(File(sourceDirectory2))

        if (files != null) {
            compoundFiles.addAll(files)
        }

        if (files2 != null) {
            compoundFiles.addAll(files2)
        }

        val adapter = VideoPagingAdapter(compoundFiles,this)
        //viewPager.offscreenPageLimit = 0
        viewPager.adapter = adapter

        val position = intent.getIntExtra("filePosition", 0)
        Log.d("pspsp",position.toString())

        Log.d("pspsp",compoundFiles[position].path)

        viewPager.currentItem = position


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {
                val video = findViewById<VideoView>(R.id.video)
                video.stopPlayback()
            }
        })
    }
}
