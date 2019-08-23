package com.boo.ketlint.sql.base

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.boo.ketlint.KlApplication
import com.boo.ketlint.sql.stu.Student
import com.boo.ketlint.sql.stu.StudentDao
import com.boo.ketlint.sql.tea.Teacher
import com.boo.ketlint.sql.tea.TeacherDao


@Database(entities = [Student::class, Teacher::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getStudentDao(): StudentDao

    abstract fun getTeacherDao(): TeacherDao

    companion object {

        val instance = Single.sin

    }

    private object Single {

        val sin: AppDataBase = Room.databaseBuilder(
            KlApplication.instance(),
            AppDataBase::class.java,
            "User.db"
        )
            .allowMainThreadQueries()
            .build()
    }

}
