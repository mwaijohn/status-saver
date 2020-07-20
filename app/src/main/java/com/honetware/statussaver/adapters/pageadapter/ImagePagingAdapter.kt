package com.honetware.statussaver.adapters.pageadapter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Environment
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
        val btnSave: Button
        val btnShare: Button
        val btnDelete: Button
        inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewLayout: View = inflater!!.inflate(R.layout.full_image, container, false)
        image = viewLayout.findViewById<View>(R.id.image) as ImageView
        btnSave = viewLayout.findViewById<View>(R.id.save) as Button
        btnShare = viewLayout.findViewById(R.id.share)
        btnDelete = viewLayout.findViewById(R.id.delete)

        //imagePaths?.get(position)
        val imageUri = Uri.fromFile(imageFiles?.get(position))
        Picasso.get().load(imageUri).error(R.drawable.ic_launcher_background).into(image)
        // close button click event
        btnSave.setOnClickListener {
           // activity.finish()
            val file = imageUri.toFile()
            val sourceDirectory = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.saveLocation + file.name

            App.downloadFile(file,File(sourceDirectory))
        }

        btnShare.setOnClickListener { App.shareFile(imageUri.toFile(),activity) }
        btnDelete.setOnClickListener {
            imageUri.toFile().delete()
            //imageFiles?.remove(imageFiles?.get(position))
            App.toastMassage(activity,"File deleted")
        }

        (container as ViewPager).addView(viewLayout)
        return viewLayout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        (container as ViewPager).removeView(`object` as RelativeLayout?)
    }

}