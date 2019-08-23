package com.boo.ketlint.sql.stu

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Student")
data class Student(

    @PrimaryKey(autoGenerate = true)
    var studentID: Int?,
    @ColumnInfo(name = "s_name")
    var studentName: String?,
    @ColumnInfo(name = "s_age")
    var age: Int?

)