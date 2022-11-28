package com.anago.applist

import android.util.Log


class Logger {
    companion object {
        private const val TAG: String = "anago-android"
        private fun str(str: String?): String {
            return str ?: "null"
        }

        fun d(x: String) {
            Log.d(TAG, str(x))
        }

        fun e(x: String) {
            Log.e(TAG, str(x))
        }
    }
}