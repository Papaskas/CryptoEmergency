package com.cryptoemergency.cryptoemergency.lib

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Convert {
    fun millisToDate(
        millis: Long,
        pattern: String = "MM/dd/yyyy"
    ): String {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(Date(millis))
    }
}
