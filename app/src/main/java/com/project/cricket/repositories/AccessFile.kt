package com.project.cricket.repositories

import android.content.Context
import android.content.res.AssetManager
import java.io.IOException


class AccessFile {

    companion object {
        @Throws(IOException::class)
        fun getJsonFile(con: Context,fileName: String)  : String {
            val manager: AssetManager = con.getAssets()
            val file = manager.open(fileName)
            val formArray = ByteArray(file.available())
            file.read(formArray)
            file.close()
            return String(formArray)
        }

    }

}