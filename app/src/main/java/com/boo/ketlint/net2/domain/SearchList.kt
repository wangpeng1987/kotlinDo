package com.boo.ketlint.net2.domain

data class SearchList(
    val error: Boolean,
    val results: MutableList<Result>
)

data class Result(
    val _id: String,
    val createdAt: String,
    val desc: String,
    val images: MutableList<String>,
    val publishedAt: String,
    val source: String,
    val type: String,
    val url: String,
    val used: Boolean,
    val who: String
)