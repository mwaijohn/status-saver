package com.honetware.statussaver.apputils

import android.os.Environment
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.channels.FileChannel
import java.util.*


class App {
    companion object{
        val FILE_EXTN  = arrayOf("jpg", "jpeg", "png")
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
        fun getFilePaths(PHOTO_ALBUM: String): ArrayList<String>? {

            val filePaths = ArrayList<String>()
            val directory = File(Environment.getExternalStorageDirectory().toString() + File.separator + PHOTO_ALBUM)

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
    }
}