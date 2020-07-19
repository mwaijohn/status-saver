package com.honetware.statussaver.adapters.pageadapter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.net.toFile
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.honetware.statussaver.R
import com.honetware.statussaver.apputils.App
import com.honetware.statussaver.apputils.Constants
import com.squareup.picasso.Picasso
import java.io.File
import java.util.ArrayList


class ImagePagingAdapter(var imageFiles: ArrayList<File>?, var activity: Activity): PagerAdapter() {
    private var inflater: LayoutInflater? = null
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout

    }

    override fun getCount(): Int{
        return imageFiles?.size!!
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val image: ImageView
        val btnClose: Button
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewLayout: View = inflater!!.inflate(R.layout.full_image, container, false)
        image = viewLayout.findViewById<View>(R.id.image) as ImageView
        btnClose = viewLayout.findViewById<View>(R.id.close) as Button

        //imagePaths?.get(position)
        val imageUri = Uri.fromFile(imageFiles?.get(position))
        Picasso.get().load(imageUri).error(R.drawable.ic_launcher_background).into(image)
        Log.d("pspspo",position.toString())
        // close button click event
        btnClose.setOnClickListener {
           // activity.finish()
            val file = imageUri.toFile()
            val sourceDirectory = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.saveLocation + file.name

            App.downloadFile(file,File(sourceDirectory))
        }
        (container as ViewPager).addView(viewLayout)
        return viewLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as RelativeLayout?)
    }

}