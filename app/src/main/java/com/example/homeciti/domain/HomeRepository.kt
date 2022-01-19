package com.example.homeciti.domain

import android.content.Context
import java.io.IOException
import java.nio.charset.Charset

class HomeRepository {
    fun getTutorials(context: Context): String {
        var json: String? = null
        val charset: Charset = Charsets.UTF_8

        try {
            val jsonString = context.assets.open("ServiceProvider.json")
            val size = jsonString.available()
            val buffer = ByteArray(size)
            jsonString.read(buffer)
            jsonString.close()
            json = String(buffer, charset)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return ""
        }
        return json
    }
}