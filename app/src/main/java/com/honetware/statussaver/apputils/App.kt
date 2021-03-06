package com.honetware.statussaver.apputils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ShareCompat.IntentBuilder
import androidx.core.net.toUri
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.channels.FileChannel
import java.util.*
import kotlin.collections.ArrayList


class App {
    companion object{
        private val FILE_EXTN  = arrayOf("jpg", "jpeg", "png")
        @Throws(IOException::class)
        fun downloadFile(sourceFile: File, destFile: File) {
            if (!destFile.parentFile.exists()) destFile.parentFile.mkdirs()
            if (!destFile.exists()) {
                destFile.createNewFile()
            }
            var source: FileChannel? = null
            var destination: FileChannel? = null
            try {
                source = FileInputStream(sourceFile).channel
                destination = FileOutputStream(destFile).channel
                destination.transferFrom(source, 0, source.size())
            } finally {
                source?.close()
                destination?.close()
            }
        }

        // Reading file paths from SDCard
        fun getFilePaths(source: String): ArrayList<String>? {

            val filePaths = ArrayList<String>()
            val directory = File(Environment.getExternalStorageDirectory().toString() + File.separator + source)

            if (directory.isDirectory) {
                val listFiles = directory.listFiles()

                if (listFiles.isNotEmpty()) {

                    // loop through all files
                    for (i in listFiles.indices) {

                        // get file path
                        val filePath = listFiles[i].absolutePath

                        // check for supported file extension
                        if (isFormatSupported(filePath)) {
                            // Add image path to array list
                            filePaths.add(filePath)
                        }
                    }
                } else {
                    // image directory is empty
                }
            } else {

            }
            return filePaths
        }

        private fun isFormatSupported(filePath: String): Boolean {
            val ext = filePath.substring(
                filePath.lastIndexOf(".") + 1,
                filePath.length
            )
            return FILE_EXTN.contains(ext.toLowerCase(Locale.getDefault()))
        }

        fun getListFiles(parentDir: File): ArrayList<File>? {
            val inFiles = ArrayList<File>()
            val files: Array<File>? = parentDir.listFiles()
            if (files != null) {
                for (file in files) {
                    if (file.name.endsWith(".jpg") ||
                        file.name.endsWith(".gif") ||
                        file.name.endsWith(".jpeg")
                        || file.name.endsWith(".png")
                    ) {
                        if (!inFiles.contains(file)) {
                            inFiles.add(file)
                        }
                    }
                }
            }
            return inFiles
        }

        fun getListFilesVideo(parentDir: File): ArrayList<File>? {
            val allFiles = ArrayList<File>()
            val files: Array<File>? = parentDir.listFiles()
            if (files != null) {
                for (file in files) {
                    if (file.name.endsWith(".mp4")) {
                        if (!allFiles.contains(file)) {
                            allFiles.add(file)
                        }
                    }
                }
            }
            return allFiles
        }

        fun shareFile(file: File,activity: Activity){
            val share = IntentBuilder.from(activity)
            val uri = Uri.fromFile(file)
            share.setStream(uri) // uri from FileProvider
                .intent
                .setAction(Intent.ACTION_SEND) //Change if needed
                .setDataAndType(uri, "image/*")
                .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            activity.startActivity(share.intent)
        }

        fun requestStoragePermissionExpPdf(view: View, activity: Activity){
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                Snackbar.make(view,"Enable write permission to external storage",Snackbar.LENGTH_INDEFINITE)
                    .setAction("enable"
                    ) {
                        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),20)
                    }.show()
            }else{
                ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),20)
            }
        }

        fun toastMassage(context: Context,massage: String){
            Toast.makeText(context,massage,Toast.LENGTH_LONG).show()
        }

        fun getImageFilesFromDirectories(): ArrayList<File>{
            val compoundFiles = ArrayList<File>()
            val sourceDirectory1 = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.whatsAppBusinessUrl
            val files = getListFiles(File(sourceDirectory1))

            val sourceDirectory2 = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.whatsAppUrl
            val files2 = getListFiles(File(sourceDirectory2))

            val sourceDirectory3 = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.whatsAppGbUrl
            val files3 = getListFiles(File(sourceDirectory3))

            if (files2 != null) {
                compoundFiles.addAll(files2)
            }

            if(files3 != null){
                compoundFiles.addAll(files3)
            }

            if (files != null) {
                compoundFiles.addAll(files)
            }

            return compoundFiles
        }

        fun getVideoFilesFromDirectories(): ArrayList<File>{
            val compoundFiles = ArrayList<File>()
            val sourceDirectory1 = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.whatsAppBusinessUrl
            val files = getListFilesVideo(File(sourceDirectory1))

            val sourceDirectory2 = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.whatsAppUrl
            val files2 = getListFilesVideo(File(sourceDirectory2))

            val sourceDirectory3 = (Environment.getExternalStorageDirectory().absoluteFile).toString()  + Constants.whatsAppGbUrl
            val files3 = getListFilesVideo(File(sourceDirectory3))

            if (files2 != null) {
                compoundFiles.addAll(files2)
            }

            if(files3 != null){
                compoundFiles.addAll(files3)
            }

            if (files != null) {
                compoundFiles.addAll(files)
            }

            return compoundFiles
        }
    }
}