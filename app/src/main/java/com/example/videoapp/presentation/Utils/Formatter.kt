package com.example.videoapp.presentation.Utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@SuppressLint("DefaultLocale")
fun FormatDurtation(durationMillis: Long) : String {

    val seconds = (durationMillis / 1000).toInt()
    val minutes = seconds / 60
    val hours = minutes / 60

    return when {
        hours > 0 -> String.format("%d:%02d%02d", hours, minutes % 60, seconds % 60)
        else -> String.format("%d:%02d", minutes, seconds % 60)
    }

}

    fun  FormatFileSize(sizeInByte: Long) : String{
        val kb =  sizeInByte / 1024.0
        val  mb = kb/1024.0
        val  gb =  mb / 1024.0

        return when{
            gb >= 1->  "%.2f GB".format(gb)
            mb>= 1->  "%.2f MB".format(mb)
            kb >= 1->  "%.2f KB".format(kb)
            else -> "$sizeInByte byte"

        }

    }

fun  FormatDate(timestamp: Long): String{
    val  sdf = SimpleDateFormat("dd MMM yyy, HH:mm", Locale.getDefault())
    return   sdf.format(Date(timestamp*1000))

}




