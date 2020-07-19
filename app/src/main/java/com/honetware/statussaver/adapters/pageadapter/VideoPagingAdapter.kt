package com.honetware.statussaver.adapters.pageadapter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toFile
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.honetware.statussaver.R
import com.honetware.statussaver.apputils.App
import com.honetware.statussaver.apputils.Constants
import java.io.File
import java.util.*


class VideoPagingAdapter(private var imageFiles: ArrayList<File>?, private var activity: Activity): PagerAdapter() {
    private var inflater: LayoutInflater? = null
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout

    }

    override fun getCount(): Int{
        return imageFiles?.size!!
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val video: VideoView
        val btnClose: Button
        val thumbnail: ImageView
        val videoIcon: ImageView
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewLayout: View = inflater!!.inflate(R.layout.full_videos, container, false)
        video = viewLayout.findViewById<View>(R.id.video) as VideoView
        btnClose = viewLayout.findViewById<View>(R.id.save) as Button
        thumbnail = viewLayout.findViewById<View>(R.id.thumbnail) as ImageView
        videoIcon = viewLayout.findViewById<View>(R.id.video_icon) as ImageView

        //imagePaths?.get(position)
        val videoUri = Uri.fromFile(imageFiles?.get(position))
        Log.d("pspspo",position.toString())

        Glide.with(activity).
        load(videoUri.path)
            .centerCrop()
            .into(thumbnail)

        val mediaController = MediaController(activity as Context)
        video.setMediaController(mediaController)

        video.setVideoURI(videoUri)
        //video.start()

        thumbnail.setOnClickListener {
            thumbnail.visibility = View.GONE
            videoIcon.visibility = View.GONE
            video.start()
        }


        // close button click event
        btnClose.setOnClickListener {
           // activity.finish()
            val file = videoUri.toFile()
            val sourceDirectory = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.saveLocation + file.name

            App.downloadFile(file,File(sourceDirectory))
        }
        (container as ViewPager).addView(viewLayout)
        return viewLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as ConstraintLayout?)
    }

}