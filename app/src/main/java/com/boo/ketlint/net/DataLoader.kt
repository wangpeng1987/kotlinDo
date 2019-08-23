package com.boo.ketlint.net

import com.google.gson.Gson

class DataLoader {
    fun getGankNewsList(date: String): List<GankNews> {
        val url = Request.BASE_URL + date
        return Gson().fromJson(Request(url).run(), GankNewsList::class.java).results
    }
}