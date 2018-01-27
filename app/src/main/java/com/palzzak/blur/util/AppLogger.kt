package com.palzzak.blur.util

import android.util.Log

/**
 * Created by yooas on 2018-01-11.
 */
class AppLogger {
    companion object {
        private val APP_TAG = "ThirtyDoC"
        private val FORMAT = "[%s]: %s"

        fun v(text: String) {
            Log.v(APP_TAG, String.format(FORMAT, getCallerInfo(), text))
        }

        fun d(text: String) {
            Log.d(APP_TAG, String.format(FORMAT, getCallerInfo(), text))
        }

        fun i(text: String) {
            Log.i(APP_TAG, String.format(FORMAT, getCallerInfo(), text))
        }

        fun w(text: String) {
            Log.w(APP_TAG, String.format(FORMAT, getCallerInfo(), text))
        }

        fun e(text: String) {
            Log.e(APP_TAG, String.format(FORMAT, getCallerInfo(), text))
        }

        private fun getCallerInfo(): String {
            val elements = Exception().stackTrace
            val className = elements[2].className
            return className.substring(className.lastIndexOf(".") + 1, className.length) + "_" + elements[2].lineNumber
        }
    }
}