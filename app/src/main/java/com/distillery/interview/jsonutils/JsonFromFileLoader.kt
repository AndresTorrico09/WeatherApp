package com.distillery.interview.jsonutils

import android.content.Context
import androidx.annotation.WorkerThread
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

class JsonFromFileLoader(private val context: Context) {

    @WorkerThread
    fun loadJson(filename: String): JSONObject? {
        val jsonString: String
        try {
            val inputStream = context.assets.open(filename)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            jsonString = String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return JSONObject(jsonString)
    }
}
