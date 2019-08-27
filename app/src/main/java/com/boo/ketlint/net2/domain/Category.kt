package com.boo.ketlint.net2.domain

data class Category(
    val category: MutableList<String>,
    val error: Boolean,
    val results: Results
)

data class Results(
    val Android: MutableList<Android>,
    val App: MutableList<App>,
    val iOS: MutableList<IOS>,
    val 休息视频: MutableList<休息视频>,
    val 前端: MutableList<前端>,
    val 拓展资源: MutableList<拓展资源>,
    val 瞎推荐: MutableList<瞎推荐>,
    val 福利: MutableList<福利>
)

data class Android(
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

data class 福利(
    val _id: String,
    val createdAt: String,
    val desc: String,
    val publishedAt: String,
    val source: String,
    val type: String,
    val url: String,
    val used: Boolean,
    val who: String
)

data class IOS(
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

data class 瞎推荐(
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

data class 前端(
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

data class App(
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

data class 休息视频(
    val _id: String,
    val createdAt: String,
    val desc: String,
    val publishedAt: String,
    val source: String,
    val type: String,
    val url: String,
    val used: Boolean,
    val who: String
)

data class 拓展资源(
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