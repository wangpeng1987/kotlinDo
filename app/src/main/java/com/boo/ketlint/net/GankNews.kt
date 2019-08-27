package com.boo.ketlint.net

data class GankNews(
    val _id: String,
    val createdAt: String,
    val desc: String,
    val publishedAt: String,
    val type: String,
    var url: String,
    val used: Boolean,
    val who: String,
    val images: MutableList<String>
)