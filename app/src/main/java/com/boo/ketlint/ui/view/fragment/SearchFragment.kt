package com.boo.ketlint.ui.view.fragment

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.recyclerview.widget.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.boo.ketlint.LOGS
import com.boo.ketlint.R
import com.boo.ketlint.adapter.GankNewsAdapter
import com.boo.ketlint.net.Category
import com.boo.ketlint.net.DataLoader
import com.boo.ketlint.net.GankNews
import com.boo.ketlint.ui.contract.TodayContract
import com.boo.ketlint.ui.presenter.TodayPresenter
import com.boo.ketlint.ui.view.act.WebActivity
import com.ljb.page.PageState
import com.umeng.commonsdk.statistics.common.DeviceConfig
import kotlinx.android.synthetic.main.activity_main_content.*
import kotlinx.android.synthetic.main.fragment_today.*
import mvp.ljb.kt.fragment.BaseMvpFragment
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by L on 2017/7/19.
 */
class SearchFragment : BaseMvpFragment<TodayContract.IPresenter>(), TodayContract.IView {

    var pindex: Int = 0

    override fun showPage(data: Category, page: Int) {
    }

    override fun errorPage(t: Throwable, page: Int) {
    }

    var pageNum: Int = 1
    var news: MutableList<GankNews>? = null
    var newsAll: MutableList<GankNews>? = null

    override fun getLayoutId() = R.layout.activity_main

    override fun registerPresenter() = TodayPresenter::class.java

    override fun initView() {
        page_layout.setPage(PageState.STATE_LOADING)
//        iv_back.visibility = View.INVISIBLE
//        tv_title.text = getText(R.string.app_name)

        newsAll = mutableListOf()


//        ImageLoader.clearImageDiskCache(this)

//        imageView.setOnClickListener({
//            ImageLoader.clearImageDiskCache(this)
//            ImageLoader.load(
//                this,
//                "https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture",
//                imageView,
//                ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
//            )
//        })
//        ImageLoader.load(
//            this,
//            "https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture",
//            imageView,
//            ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
//        )

        recycler_view.setListener(object : RecyclerListener {
            override fun loadMore() {
                if (news == null || news.isNullOrEmpty()) return
                if (news!!.size == 0) {
                    Toast.makeText(context, "没有更多数据了~", Toast.LENGTH_SHORT).show()
                } else {
                    pageNum++
                    getGanksNewsList()
                }
            }

            override fun refresh() {
                pageNum = 1
                getGanksNewsList()
            }
        })
        getGanksNewsList()

        getTestDeviceInfo(context)

//        for (i in 0..30) {
//            downloadFile(i)
//        }

//        toast(sums(6).toString())
//        toast(string2)

        //2.let空值检测,必须结合?.来使用
//        var data = null
//        data?.let {
//            //当data不为空时执行
//            Log.e("aaa", "data不为空")
//        }
//
//        data ?: let {
//            //当data等于空时执行
//            Log.e("aaa", "data等于空")
//        }
//
//        val list = ArrayList<String>()
//        list.add("aaa")
//        list.add("bbb")
//        list.add("ccc")
//        list.add("ddd")
//
//        Log.e("list.size", list.size.toString())

//        //22.1递增for (int i = 0; i < list.size(); i++)
//        for (i in list.indices) {
//            Log.e("1递增for", list[i])
//        }
//        //22.2递减for (int i = list.size(); i >= 0; i--)
//        for (i in list.size - 1 downTo 0) {
//            Log.e("2递减for", list[i])
//        }

//        button.setOnClickListener({
//            val intent = Intent()
//            intent.setClass(this@MainActivity, StudentActivity::class.java)
//            startActivity(intent)
//        })

//        imageView.setOnClickListener({
//            doDatabasee()
//            if (icon.equals("")) {
//                toast("ACTIVITY_ALIAS_1")
//                icon = ACTIVITY_ALIAS_1
//                AppUtils.setIcon(ACTIVITY_ALIAS_1)
//            } else if (icon.equals(ACTIVITY_ALIAS_1)) {
//                toast("ACTIVITY_ALIAS_2")
//                icon = ACTIVITY_ALIAS_2
//                AppUtils.setIcon(ACTIVITY_ALIAS_2)
//            } else if (icon.equals(ACTIVITY_ALIAS_2)) {
//                toast("ACTIVITY_ALIAS_1")
//                icon = ACTIVITY_ALIAS_1
//                AppUtils.setIcon(ACTIVITY_ALIAS_1)
//            }
//        })


//        doDatabasee()
    }

    fun getTestDeviceInfo(context: Context?): Array<String?> {
        val deviceInfo = arrayOfNulls<String>(2)
        try {
            //{"device_id","ce0717174c6f4c670c7e","mac":"dc:ef:ca:7f:46:e7"}
            if (context != null) {
                deviceInfo[0] = DeviceConfig.getDeviceIdForGeneral(context)
                LOGS.e("device_id : " + deviceInfo[0])
                deviceInfo[1] = DeviceConfig.getMac(context)
                LOGS.e("mac : " + deviceInfo[1])
            }
        } catch (e: Exception) {
        }
        return deviceInfo
    }

    private fun parseInt(str: String): Int? = str.toIntOrNull()

    private fun sums(a: Int = 0, b: Int = 0) = a + b

    private fun getGanksNewsList() = doAsync {
        //        val randoms = (0..479).random()
        news = DataLoader().getGankNewsList("data/all/20/" + pageNum)
//        pindex = newsAll!!.size
        newsAll?.addAll(news!!)
        uiThread {
            //            toast("获取网络数据")
            recycler_view.layoutManager = LinearLayoutManager(context)
            page_layout.setPage(PageState.STATE_SUCCESS)
            //添加Android自带的分割线
            recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            if (newsAll != null) {
                LOGS.e("pindex : " + pindex)
                recycler_view.adapter = GankNewsAdapter(newsAll!!)
                {
                    val intent = Intent()
                    intent.setClass(context, WebActivity::class.java)
//                it.url = "https://wangpeng1987.github.io/AppServer/"
                    intent.putExtra("url", it.url)
                    startActivity(intent)
                }
                recycler_view.scrollY = pindex
            }
        }
    }

    interface RecyclerListener {
        fun loadMore()
        fun refresh()
    }

    fun RecyclerView.setListener(l: RecyclerListener) {
        setOnScrollListener(object : RecyclerView.OnScrollListener() {
            var lastVisibleItem: Int = 0
            val swipeRefreshLayout = this@setListener.parent
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                pindex = dy
                val layoutManager: RecyclerView.LayoutManager = recyclerView.layoutManager!!

                if (layoutManager is LinearLayoutManager) {
                    lastVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                } else if (layoutManager is GridLayoutManager) {
                    lastVisibleItem = (recyclerView.layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                } else if (layoutManager is StaggeredGridLayoutManager) {
                    val staggeredGridLayoutManager = recyclerView.layoutManager as StaggeredGridLayoutManager
                    val lastPositions = IntArray(staggeredGridLayoutManager.spanCount)
                    staggeredGridLayoutManager.findLastVisibleItemPositions(lastPositions)
                    lastVisibleItem = findMax(lastPositions)
                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == recyclerView.adapter?.itemCount) {
                    //下拉刷新的时候不可以加载更多
                    if (swipeRefreshLayout is SwipeRefreshLayout) {
                        if (!swipeRefreshLayout.isRefreshing) {
                            l.loadMore()
                        }
                    } else {
                        l.loadMore()
                    }
                }
            }

        })

        val swipeRefreshLayout = this.parent
        if (swipeRefreshLayout is SwipeRefreshLayout) {
            swipeRefreshLayout.setOnRefreshListener {
                l.refresh()
            }
        }

    }

    /**
     * 取数组中最大值
     *
     * @param lastPositions
     * @return
     */
    fun findMax(lastPositions: IntArray): Int {
        var max = lastPositions[0]
        for (i in lastPositions.indices) {
            val value = lastPositions[i]
            if (value > max) {
                max = value
            }
        }

        return max;
    }
//
//    private fun doDatabasee() = doAsync {
//        val sDao: StudentDao = AppDataBase.instance.getStudentDao()
////        val s_1 = Student(1, "s1", 10,1)
////        val sList: MutableList<Student> = mutableListOf()
////        sList.add(s_1)
////可以直接把list传进去，也可以一个一个单独添加
////        sDao.insertAll(sList)
//        val sList_select_1: MutableList<Student> = sDao.getAllStudents()
//        LOGS.e("sql student size : " + sList_select_1.size)
//        sList_select_1.indices.forEach {
//            LOGS.e("sql student : " + sList_select_1.get(it))
//        }
//
//        val tDao: TeacherDao = AppDataBase.instance.getTeacherDao()
////        val s_1 = Student(1, "s1", 10,1)
////        val sList: MutableList<Student> = mutableListOf()
////        sList.add(s_1)
////可以直接把list传进去，也可以一个一个单独添加
////        sDao.insertAll(sList)
//        val tList_select_1: MutableList<Teacher> = tDao.getAllTeachers()
//        LOGS.e("sql teacher size : " + tList_select_1.size)
//        tList_select_1.indices.forEach {
//            LOGS.e("sql teacher : " + tList_select_1.get(it))
//        }
//
//        val vDao: VideoDao = AppDataBase.instance.getVideoDao()
//        val v_1 = Video(1, "bendi 1 ", "url1")
//
//        val vList: MutableList<Video> = mutableListOf()
//        vList.add(v_1)
////可以直接把list传进去，也可以一个一个单独添加
////       vDao.insertAll(vList)
//
//        val vList_select_1: MutableList<Video> = vDao.getAllVideos()
//        LOGS.e("sql video size : " + vList_select_1.size)
//        vList_select_1.indices.forEach {
//            LOGS.e("sql video: " + vList_select_1.get(it))
//        }
//
//        // AppUtils.setIcon(ACTIVITY_ALIAS_2)
//    }

//    private fun downloadFile(i: Int) = doAsync {
//        LOGS.e("downloadFile : " + i)
//        GlobalScope.launch(Dispatchers.Main) {
//            val randoms = (0..30).random()
//            val randomsSex = (0..2).random()
//            async(Dispatchers.IO) { delay(randoms * 1000L) }.await()
//            val sDao: StudentDao = AppDataBase.instance.getStudentDao()
//            val sList: MutableList<Student> = mutableListOf()
////            val s_1 = Student(i, "s" + i, i,randomsSex,1)
////            sList.add(s_1)
////            sDao.insertAll(sList)
//            val sList_select_1: Student = sDao.getStudnetName("s" + i)
//            LOGS.e("downloadFile : " + sList_select_1)
//            if (i == 25) {
//                student = sList_select_1.studentName.toString();
//
//                if (::student.isInitialized) {
//                    toast("student : " + student)
//                }
//            }
//        }
//    }

}