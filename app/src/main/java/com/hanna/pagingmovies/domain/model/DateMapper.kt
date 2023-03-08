package com.hanna.pagingmovies.domain.model

import java.text.SimpleDateFormat
import java.util.Locale


const val SERVER_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
const val UI_DATE_FORMAT = "dd MMM yyyy • HH:mm"
const val SERVER_DATE_2_FORMAT = "yyyy-MM-dd"
const val UI_DATE_2_FORMAT = "dd MM yyyy"

fun String.toUiFullDate(): String {
    val format = SimpleDateFormat(SERVER_DATE_FORMAT, Locale.ENGLISH)
    val output = SimpleDateFormat(UI_DATE_FORMAT, Locale.ENGLISH)
    return try {
        val date = format.parse(this)
        if (date != null) output.format(date) else this
    } catch (e: Exception) {
        this
    }
}

fun String.toUiDate(): String {
    val format = SimpleDateFormat(SERVER_DATE_2_FORMAT, Locale.ENGLISH)
    val output = SimpleDateFormat(UI_DATE_2_FORMAT, Locale.ENGLISH)
    return try {
        val date = format.parse(this)
        if (date != null) output.format(date) else this
    } catch (e: Exception) {
        this
    }
}