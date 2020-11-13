package com.kolis.cookingbook.ui.welcome

interface OnPasswordCheckObserver {
    fun onPasswordCorrect(login: String?, password: String?)
    fun onPasswordWrong()
}