package com.kolis.cookingbook.utils

import android.widget.Toast

class ToastMaker {

    companion object {
        fun showLong(text: String) {
            Toast.makeText(AppState.appContext, text, Toast.LENGTH_LONG).show()
        }
    }
}