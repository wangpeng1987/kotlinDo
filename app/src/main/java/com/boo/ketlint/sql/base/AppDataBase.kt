package com.boo.ketlint.sql.base

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.boo.ketlint.KlApplication
import com.boo.ketlint.sql.stu.Student
import com.boo.ketlint.sql.stu.StudentDao
import com.boo.ketlint.sql.tea.Teacher
import com.boo.ketlint.sql.tea.TeacherDao
import com.boo.ketlint.sql.video.Video
import com.boo.ketlint.sql.video.VideoDao
import androidx.room.migration.Migration

@Database(entities = [Student::class, Teacher::class, Video::class], version = 6)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getStudentDao(): StudentDao

    abstract fun getVideoDao(): VideoDao

    abstract fun getTeacherDao(): TeacherDao

    companion object {

        val instance = Single.sin

    }

    private object Single {

        //添加表
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE `Video` (`videoID` INTEGER, " + "`v_local` TEXT,`v_url` TEXT, PRIMARY KEY(`videoID`))")
            }
        }

        //新增字段
        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Student " + " ADD COLUMN s_sex INTEGER")
            }
        }

        //新增字段
        val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Student " + " ADD COLUMN s_grade INTEGER")
                database.execSQL("ALTER TABLE Teacher " + " ADD COLUMN t_grade INTEGER")
            }
        }

        //新增字段
        val MIGRATION_4_5: Migration = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Teacher " + " ADD COLUMN t_time INTEGER")
            }
        }

        //删除表字段
        val MIGRATION_5_6: Migration = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //ALTER TABLE [表名] DROP COLUMN [字段名]
               // database.execSQL("ALTER TABLE Student " + " DROP COLUMN s_sex")
            }
        }

        val sin: AppDataBase = Room.databaseBuilder(
            KlApplication.instance(),
            AppDataBase::class.java,
            "User.db"
        ).addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6)
            .allowMainThreadQueries()
            .build()
    }


}
