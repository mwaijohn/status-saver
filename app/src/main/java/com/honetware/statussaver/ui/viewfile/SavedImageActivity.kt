package com.honetware.statussaver.ui.viewfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import androidx.viewpager.widget.ViewPager
import com.honetware.statussaver.R
import com.honetware.statussaver.adapters.pageadapter.ImagePagingAdapter
import com.honetware.statussaver.apputils.App
import com.honetware.statussaver.apputils.Constants
import java.io.File

class SavedImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_image)

        val viewPager: ViewPager =  findViewById(R.id.pager)

        val sourceDirectory1 = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.saveLocation
        val files = App.getListFiles(File(sourceDirectory1))



        val adapter = ImagePagingAdapter(files,this)

        viewPager.adapter = adapter

        val position = intent.getIntExtra("filePosition", 0)
        viewPager.currentItem = position

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}
