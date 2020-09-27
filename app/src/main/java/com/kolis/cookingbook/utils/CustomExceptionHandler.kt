package com.kolis.cookingbook.utils

import android.content.ClipData
import android.content.ClipboardManager
import androidx.core.content.ContextCompat.getSystemService

/**
 * Handles uncaught exceptions by saving their stacktraces to clipboard
 */

class CustomExceptionHandler : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(p0: Thread, p1: Throwable) {

        val clipboard = getSystemService(AppState.appContext, ClipboardManager::class.java)
        val text = "Message = ${p1.message}, Stack trace = ${p1.stackTrace}"
        val clip = ClipData.newPlainText("label", text)
        clipboard!!.setPrimaryClip(clip)
    }
}