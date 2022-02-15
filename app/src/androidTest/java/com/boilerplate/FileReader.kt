package com.boilerplate

import androidx.test.platform.app.InstrumentationRegistry
import com.github.ariefannur.boilerplate.core.base.MyApp
import timber.log.Timber
import java.io.IOException
import java.io.InputStreamReader

object FileReader {
    fun readStringFromFile(fileName: String): String {
        try {
            val inputStream = (InstrumentationRegistry.getInstrumentation().targetContext
                .applicationContext as MyApp).assets.open(fileName)
            val builder = StringBuilder()
            val reader = InputStreamReader(inputStream, "UTF-8")
            reader.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        } catch (e: IOException) {
            Timber.d("AF err : ${e.message}")
            throw e
        }
    }
}