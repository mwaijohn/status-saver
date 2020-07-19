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

class SavedVideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_image)

        val viewPager: ViewPager =  findViewById(R.id.pager)


        val sourceDirectory1 = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.saveLocation
        val files = App.getListFilesVideo(File(sourceDirectory1))


        val adapter = VideoPagingAdapter(files,this)
        //viewPager.offscreenPageLimit = 0
        viewPager.adapter = adapter

        val position = intent.getIntExtra("filePosition", 0)

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
