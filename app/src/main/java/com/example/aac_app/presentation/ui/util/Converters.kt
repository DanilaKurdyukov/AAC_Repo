package com.example.aac_app.presentation.ui.util

import androidx.databinding.InverseMethod

object Converters {

    @InverseMethod("stringToInt")
    @JvmStatic
    fun intToString(value: Int): String {
        return if(value==0) "" else value.toString()
    }

    @JvmStatic
    fun stringToInt(value: String): Int {
        return value.toInt()
    }

}