package com.boo.ketlint.net

import java.net.URL

class Request(val url: String) {

    companion object {
        val BASE_URL = "http://gank.io/api/"
    }

    fun run(): String {
        val resultStr = URL(url).readText()
        return resultStr
    }
}