package com.boo.ketlint

import android.util.Log

object LOGS {
    var Tag = "wop"

    fun e(s: String) {
        Log.e(Tag, s)
    }

    fun v(s: String) {
        Log.v(Tag, s)
    }

    fun i(s: String) {
        Log.i(Tag, s)
    }

    fun d(s: String) {
        Log.d(Tag, s)
    }

    fun w(s: String) {
        Log.w(Tag, s)
    }
}