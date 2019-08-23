package com.boo.ketlint.sql.tea

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.boo.ketlint.sql.base.BaseDao

@Dao
interface TeacherDao : BaseDao<Teacher> {

    @Insert
    fun insert(element: Teacher)

    @Query("select * from Teacher")
    fun getAllTeachers(): MutableList<Teacher>

    @Query("select * from Teacher where teacherID = :teacherID")
    fun getTeacher(teacherID: Int): Teacher

    @Query("select * from Teacher order by t_year desc ")
    fun getAllByDateDesc(): MutableList<Teacher>

    @Query("delete from Teacher")
    fun deleteAll()

}
