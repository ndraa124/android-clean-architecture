package com.idn.news.external

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    private val localeID = Locale("in", "ID")

    @SuppressLint("SimpleDateFormat")
    fun getDateFormatNoGMT(date: String, type: Int? = null): String {
        val defaultFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", localeID)

        try {
            return when (type) {
                1 -> SimpleDateFormat("yyMMdd").format(defaultFormat.parse(date)!!)
                2 -> SimpleDateFormat("EEE, dd MMM yyyy").format(defaultFormat.parse(date)!!)
                3 -> SimpleDateFormat("dd MMM yyyy").format(defaultFormat.parse(date)!!)
                4 -> SimpleDateFormat("hh:mm a").format(defaultFormat.parse(date)!!)
                else -> SimpleDateFormat("dd-MM-yyyy").format(defaultFormat.parse(date)!!)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return ""
    }

    @SuppressLint("SimpleDateFormat")
    fun getDateFormat(date: String, type: Int? = null): String {
        val defaultFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", localeID)
        defaultFormat.timeZone = TimeZone.getTimeZone("GMT")

        try {
            return when (type) {
                1 -> SimpleDateFormat("yyMMdd").format(defaultFormat.parse(date)!!)
                2 -> SimpleDateFormat("EEE, dd MMM yyyy").format(defaultFormat.parse(date)!!)
                3 -> SimpleDateFormat("dd MMM yyyy").format(defaultFormat.parse(date)!!)
                4 -> SimpleDateFormat("hh:mm a").format(defaultFormat.parse(date)!!)
                else -> SimpleDateFormat("dd-MM-yyyy").format(defaultFormat.parse(date)!!)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return ""
    }
}
