package com.boo.ketlint.sql.video

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.boo.ketlint.sql.base.BaseDao

@Dao
interface VideoDao : BaseDao<Video> {
    @Insert
    fun insert(element: Video)

    @Query("select * from Video")
    fun getAllVideos(): MutableList<Video>

    @Query("select * from Video where v_url = :vUrl")
    fun getVideo(vUrl: String): Video

    @Query("delete from Teacher")
    fun deleteAll()
}