package com.boo.ketlint.net2.domain

data class MeList(
    val results: MutableList<Result>
)

data class MeResult(
    val images: String,
    val url: String,
    val name: String
)