package com.boo.ketlint

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.boo.ketlint.adapter.GankNewsAdapter
import com.boo.ketlint.net.DataLoader
import com.boo.ketlint.sql.base.AppDataBase
import com.boo.ketlint.sql.stu.Student
import com.boo.ketlint.sql.stu.StudentDao
import com.boo.ketlint.sql.tea.Teacher
import com.boo.ketlint.sql.tea.TeacherDao
import com.boo.ketlint.sql.video.Video
import com.boo.ketlint.sql.video.VideoDao
import com.boo.ketlint.ui.view.act.WebActivity
import com.boo.ketlint.ui.view.act.StudentActivity
import com.boo.ketlint.util.AppUtils
import com.boo.ketlint.util.AppUtils.ACTIVITY_ALIAS_1
import com.boo.ketlint.util.AppUtils.ACTIVITY_ALIAS_2
import com.ljb.mvp.kotlin.img.ImageLoader
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    private lateinit var student: String
    private var string1 = "a1"
    private var string2 = "a1 $string1"

    private var icon: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview1.text = "katlin test";
        button.text = "测试";

        ImageLoader.load(this, R.mipmap.ic_launcher_round, imageView, ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL))

        getGanksNewsList()

//        for (i in 0..30) {
//            downloadFile(i)
//        }

//        toast(sums(6).toString())
//        toast(string2)

        //2.let空值检测,必须结合?.来使用
        var data = null
        data?.let {
            //当data不为空时执行
            Log.e("aaa", "data不为空")
        }

        data ?: let {
            //当data等于空时执行
            Log.e("aaa", "data等于空")
        }

        val list = ArrayList<String>()
        list.add("aaa")
        list.add("bbb")
        list.add("ccc")
        list.add("ddd")

        Log.e("list.size", list.size.toString())

        //22.1递增for (int i = 0; i < list.size(); i++)
        for (i in list.indices) {
            Log.e("1递增for", list[i])
        }
        //22.2递减for (int i = list.size(); i >= 0; i--)
        for (i in list.size - 1 downTo 0) {
            Log.e("2递减for", list[i])
        }

        button.setOnClickListener({
            val intent = Intent()
            intent.setClass(this@MainActivity, StudentActivity::class.java)
            startActivity(intent)
        })

        imageView.setOnClickListener({
            doDatabasee()
            if (icon.equals("")) {
                toast("ACTIVITY_ALIAS_1")
                icon = ACTIVITY_ALIAS_1
                AppUtils.setIcon(ACTIVITY_ALIAS_1)
            } else if (icon.equals(ACTIVITY_ALIAS_1)) {
                toast("ACTIVITY_ALIAS_2")
                icon = ACTIVITY_ALIAS_2
                AppUtils.setIcon(ACTIVITY_ALIAS_2)
            } else if (icon.equals(ACTIVITY_ALIAS_2)) {
                toast("ACTIVITY_ALIAS_1")
                icon = ACTIVITY_ALIAS_1
                AppUtils.setIcon(ACTIVITY_ALIAS_1)
            }
        })


        doDatabasee()


    }


    private fun parseInt(str: String): Int? = str.toIntOrNull()

    private fun sums(a: Int = 0, b: Int = 0) = a + b

    private fun getGanksNewsList() = doAsync {
        val news = DataLoader().getGankNewsList("data/all/20/2")
        uiThread {
            toast("获取网络数据")
            recylerview.layoutManager = LinearLayoutManager(this@MainActivity)
            recylerview.adapter = GankNewsAdapter(news) {
                val intent = Intent()
                intent.setClass(this@MainActivity, WebActivity::class.java)
//                it.url = "https://wangpeng1987.github.io/AppServer/"
                intent.putExtra("url", it.url)
                startActivity(intent)
            }
        }
    }

    private fun doDatabasee() = doAsync {
        val sDao: StudentDao = AppDataBase.instance.getStudentDao()
//        val s_1 = Student(1, "s1", 10,1)
//        val sList: MutableList<Student> = mutableListOf()
//        sList.add(s_1)
//可以直接把list传进去，也可以一个一个单独添加
//        sDao.insertAll(sList)
        val sList_select_1: MutableList<Student> = sDao.getAllStudents()
        LOGS.e("sql student size : " + sList_select_1.size)
        sList_select_1.indices.forEach {
            LOGS.e("sql student : " + sList_select_1.get(it))
        }

        val tDao: TeacherDao = AppDataBase.instance.getTeacherDao()
//        val s_1 = Student(1, "s1", 10,1)
//        val sList: MutableList<Student> = mutableListOf()
//        sList.add(s_1)
//可以直接把list传进去，也可以一个一个单独添加
//        sDao.insertAll(sList)
        val tList_select_1: MutableList<Teacher> = tDao.getAllTeachers()
        LOGS.e("sql teacher size : " + tList_select_1.size)
        tList_select_1.indices.forEach {
            LOGS.e("sql teacher : " + tList_select_1.get(it))
        }

        val vDao: VideoDao = AppDataBase.instance.getVideoDao()
        val v_1 = Video(1,"bendi 1 ", "url1")

        val vList: MutableList<Video> = mutableListOf()
        vList.add(v_1)
//可以直接把list传进去，也可以一个一个单独添加
//       vDao.insertAll(vList)

        val vList_select_1: MutableList<Video> = vDao.getAllVideos()
        LOGS.e("sql video size : " + vList_select_1.size)
        vList_select_1.indices.forEach {
            LOGS.e("sql video: " + vList_select_1.get(it))
        }

        AppUtils.setIcon(ACTIVITY_ALIAS_2)
    }

    private fun downloadFile(i: Int) = doAsync {
        LOGS.e("downloadFile : " + i)
        GlobalScope.launch(Dispatchers.Main) {
            val randoms = (0..30).random()
            val randomsSex= (0..2).random()
            async(Dispatchers.IO) { delay(randoms * 1000L) }.await()
            val sDao: StudentDao = AppDataBase.instance.getStudentDao()
            val sList: MutableList<Student> = mutableListOf()
//            val s_1 = Student(i, "s" + i, i,randomsSex,1)
//            sList.add(s_1)
//            sDao.insertAll(sList)
            val sList_select_1: Student = sDao.getStudnetName("s" + i)
            LOGS.e("downloadFile : " + sList_select_1)
            if (i == 25) {
                student = sList_select_1.studentName.toString();

                if (::student.isInitialized) {
                    toast("student : " + student)
                }
            }
        }
    }


}
