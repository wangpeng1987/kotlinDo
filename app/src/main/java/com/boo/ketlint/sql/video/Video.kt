package com.boo.ketlint.sql.video

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Video")
data class Video(

    @PrimaryKey(autoGenerate = true)
    var videoID: Int?,
    @ColumnInfo(name = "v_local")
    var videoLocal: String?,
    @ColumnInfo(name = "v_url")
    var videoUrl: String?

)