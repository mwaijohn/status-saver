package com.honetware.statussaver.ui.viewfile

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.tabs.TabLayout
import com.honetware.statussaver.R
import com.honetware.statussaver.adapters.pageadapter.ImagePagingAdapter
import com.honetware.statussaver.apputils.App
import com.honetware.statussaver.apputils.Constants
import java.io.File


class ViewImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_image)

//        val toolbar: Toolbar = findViewById(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        supportActionBar?.elevation = 0F

        val viewPager: ViewPager =  findViewById(R.id.pager)

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


        val adapter = ImagePagingAdapter(compoundFiles,this)

        viewPager.adapter = adapter

        val position = intent.getIntExtra("filePosition", 0)
        viewPager.currentItem = position

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}
