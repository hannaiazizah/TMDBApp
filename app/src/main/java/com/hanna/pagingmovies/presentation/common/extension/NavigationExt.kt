package com.hanna.pagingmovies.presentation.common.extension

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun NavController.safeNavigate(navDirections: NavDirections) {
    try {
        navigate(navDirections)
    } catch (e: Exception) {
        Log.e("NavControllerException", e.stackTraceToString())
    }
}