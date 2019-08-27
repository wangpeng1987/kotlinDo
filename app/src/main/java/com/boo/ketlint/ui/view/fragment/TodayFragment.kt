package com.boo.ketlint.ui.view.fragment

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.boo.ketlint.LOGS
import com.boo.ketlint.R
import com.boo.ketlint.adapter.*
import com.boo.ketlint.net.*
import com.boo.ketlint.ui.contract.TodayContract
import com.boo.ketlint.ui.presenter.TodayPresenter
import com.boo.ketlint.ui.view.act.WebActivity
import com.google.android.material.tabs.TabLayout
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter
import com.ljb.page.PageState
import kotlinx.android.synthetic.main.fragment_today.*
import kotlinx.android.synthetic.main.layout_recycler_view.*
import mvp.ljb.kt.fragment.BaseMvpFragment

/**
 * Created by L on 2017/7/19.
 */
class TodayFragment : BaseMvpFragment<TodayContract.IPresenter>(), TodayContract.IView,
    LoadMoreRecyclerAdapter.LoadMoreListener, LoadMoreRecyclerAdapter.OnItemClickListener,
    TabLayout.OnTabSelectedListener {

    var position: Int = 0

    override fun onTabReselected(p0: TabLayout.Tab?) {
        LOGS.e("onTabReselected  :  " + p0!!.position)
    }

    override fun onTabUnselected(p0: TabLayout.Tab?) {
        LOGS.e("onTabUnselected  :  " + p0!!.position)
    }

    override fun onTabSelected(p0: TabLayout.Tab?) {
        LOGS.e("onTabSelected  : " + p0!!.position)
        if (dataAll != null && strArray != null) {
            position = p0!!.position
            if (p0!!.position == 0) {
                var data: MutableList<Android> = dataAll!!.results.Android
                setAdapter(data, mTodayAndroidAdapter)
            } else if (p0!!.position == 1) {
                var data: MutableList<App> = dataAll!!.results.App
                setAdapter(data, mTodayAppAdapter)
            } else if (p0!!.position == 2) {
                var data: MutableList<IOS> = dataAll!!.results.iOS
                setAdapter(data, mTodayIOSAdapter)
//            } else if (p0!!.position == 3) {
//                var data: MutableList<休息视频> = dataAll!!.results.休息视频
//                setAdapter(data, mToday休息视频Adapter)
            } else if (p0!!.position == 3) {
                var data: MutableList<前端> = dataAll!!.results.前端
                setAdapter(data, mToday前端Adapter)
            } else if (p0!!.position == 4) {
                var data: MutableList<拓展资源> = dataAll!!.results.拓展资源
                setAdapter(data, mToday拓展资源Adapter)
            } else if (p0!!.position == 5) {
                var data: MutableList<瞎推荐> = dataAll!!.results.瞎推荐
                setAdapter(data, mToday瞎推荐Adapter)
//            } else if (p0!!.position == 7) {
//                var data: MutableList<福利> = dataAll!!.results.福利
//                setAdapter(data, mToday福利Adapter)
            }
        }
    }

    var dataAll: Category? = null
    var strArray: MutableList<String>? = null

    private val mTodayAndroidAdapter by lazy { TodayAndroidAdapter(activity!!, mutableListOf()) }
    private val mTodayAppAdapter by lazy { TodayAppAdapter(activity!!, mutableListOf()) }
    private val mTodayIOSAdapter by lazy { TodayIOSAdapter(activity!!, mutableListOf()) }
    private val mToday休息视频Adapter by lazy { Today休息视频Adapter(activity!!, mutableListOf()) }
    private val mToday前端Adapter by lazy { Today前端Adapter(activity!!, mutableListOf()) }
    private val mToday拓展资源Adapter by lazy { Today拓展资源Adapter(activity!!, mutableListOf()) }
    private val mToday瞎推荐Adapter by lazy { Today瞎推荐Adapter(activity!!, mutableListOf()) }
    private val mToday福利Adapter by lazy { Today福利Adapter(activity!!, mutableListOf()) }

    override fun getLayoutId() = R.layout.fragment_today

    override fun registerPresenter() = TodayPresenter::class.java

    override fun initView() {
        tab_title.addOnTabSelectedListener(this@TodayFragment)
        onReload()
    }

    override fun onLoadMore() {
    }

    private fun onReload() {
        page_layout.setPage(PageState.STATE_LOADING)
        getPresenter().onRefresh()
    }

    override fun showPage(data: Category, page: Int) {
        dataAll = data
        strArray = data.category
        setAdapter(data.results.Android, mTodayAndroidAdapter)
    }

    override fun errorPage(t: Throwable, page: Int) {
        page_layout.setPage(PageState.STATE_ERROR)
    }

    override fun onItemClick(view: View, positio: Int) {
        try {
            if (position == 0) {
                val itemData = mTodayAndroidAdapter.mData[positio]
                WebActivity.startActivity(activity!!, itemData.url)
            } else if (position == 1) {
                val itemData = mTodayAppAdapter.mData[positio]
                WebActivity.startActivity(activity!!, itemData.url)
            } else if (position == 2) {
                val itemData = mTodayIOSAdapter.mData[positio]
                WebActivity.startActivity(activity!!, itemData.url)
    //        } else if (position == 3) {
    //            val itemData = mToday休息视频Adapter.mData[positio]
    //            WebActivity.startActivity(activity!!, itemData.url)
            } else if (position == 3) {
                val itemData = mToday前端Adapter.mData[positio]
                WebActivity.startActivity(activity!!, itemData.url)
            } else if (position == 4) {
                val itemData = mToday拓展资源Adapter.mData[positio]
                WebActivity.startActivity(activity!!, itemData.url)
            } else if (position == 5) {
                val itemData = mToday瞎推荐Adapter.mData[positio]
                WebActivity.startActivity(activity!!, itemData.url)
    //        } else if (position == 7) {
    //            val itemData = mToday福利Adapter.mData[positio]
    //            WebActivity.startActivity(activity!!, itemData.url)
            }
        } catch (e: Exception) {
        } finally {
        }
    }

    private fun setAdapter(data: MutableList<Android>, mAdapter: TodayAndroidAdapter) {

        page_layout.setOnPageErrorClickListener { onReload() }
        recycler_view.apply {
            recycler_view.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            //添加Android自带的分割线
            recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
//            addItemDecoration(FollowersDecoration())
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@TodayFragment)
            mAdapter.setOnItemClickListener(this@TodayFragment)
        }

        if (data.isEmpty()) {
            page_layout.setPage(PageState.STATE_EMPTY)
        } else {
            page_layout.setPage(PageState.STATE_SUCCESS)
            mAdapter.mData.clear()
            mAdapter.mData.addAll(data)
            mAdapter.onLoadStatusNoMore()
        }
    }

    private fun setAdapter(data: MutableList<App>, mAdapter: TodayAppAdapter) {
        page_layout.setOnPageErrorClickListener { onReload() }
        recycler_view.apply {
            recycler_view.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            //添加Android自带的分割线
            recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
//            addItemDecoration(FollowersDecoration())
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@TodayFragment)
            mAdapter.setOnItemClickListener(this@TodayFragment)
        }

        if (data.isEmpty()) {
            page_layout.setPage(PageState.STATE_EMPTY)
        } else {
            page_layout.setPage(PageState.STATE_SUCCESS)
            mAdapter.mData.clear()
            mAdapter.mData.addAll(data)
            mAdapter.onLoadStatusNoMore()
        }
    }

    private fun setAdapter(data: MutableList<IOS>, mAdapter: TodayIOSAdapter) {
        page_layout.setOnPageErrorClickListener { onReload() }
        recycler_view.apply {
            recycler_view.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            //添加Android自带的分割线
            recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
//            addItemDecoration(FollowersDecoration())
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@TodayFragment)
            mAdapter.setOnItemClickListener(this@TodayFragment)
        }

        if (data.isEmpty()) {
            page_layout.setPage(PageState.STATE_EMPTY)
        } else {
            page_layout.setPage(PageState.STATE_SUCCESS)
            mAdapter.mData.clear()
            mAdapter.mData.addAll(data)
            mAdapter.onLoadStatusNoMore()
        }
    }

    private fun setAdapter(data: MutableList<休息视频>, mAdapter: Today休息视频Adapter) {
        page_layout.setOnPageErrorClickListener { onReload() }
        recycler_view.apply {
            recycler_view.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            //添加Android自带的分割线
            recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
//            addItemDecoration(FollowersDecoration())
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@TodayFragment)
            mAdapter.setOnItemClickListener(this@TodayFragment)
        }

        if (data.isEmpty()) {
            page_layout.setPage(PageState.STATE_EMPTY)
        } else {
            page_layout.setPage(PageState.STATE_SUCCESS)
            mAdapter.mData.clear()
            mAdapter.mData.addAll(data)
            mAdapter.onLoadStatusNoMore()
        }
    }

    private fun setAdapter(data: MutableList<前端>, mAdapter: Today前端Adapter) {
        page_layout.setOnPageErrorClickListener { onReload() }
        recycler_view.apply {
            recycler_view.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            //添加Android自带的分割线
            recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
//            addItemDecoration(FollowersDecoration())
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@TodayFragment)
            mAdapter.setOnItemClickListener(this@TodayFragment)
        }

        if (data.isEmpty()) {
            page_layout.setPage(PageState.STATE_EMPTY)
        } else {
            page_layout.setPage(PageState.STATE_SUCCESS)
            mAdapter.mData.clear()
            mAdapter.mData.addAll(data)
            mAdapter.onLoadStatusNoMore()
        }
    }

    private fun setAdapter(data: MutableList<拓展资源>, mAdapter: Today拓展资源Adapter) {
        page_layout.setOnPageErrorClickListener { onReload() }
        recycler_view.apply {
            recycler_view.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            //添加Android自带的分割线
            recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
//            addItemDecoration(FollowersDecoration())
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@TodayFragment)
            mAdapter.setOnItemClickListener(this@TodayFragment)
        }

        if (data.isEmpty()) {
            page_layout.setPage(PageState.STATE_EMPTY)
        } else {
            page_layout.setPage(PageState.STATE_SUCCESS)
            mAdapter.mData.clear()
            mAdapter.mData.addAll(data)
            mAdapter.onLoadStatusNoMore()
        }
    }

    private fun setAdapter(data: MutableList<瞎推荐>, mAdapter: Today瞎推荐Adapter) {
        page_layout.setOnPageErrorClickListener { onReload() }
        recycler_view.apply {
            recycler_view.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            //添加Android自带的分割线
            recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
//            addItemDecoration(FollowersDecoration())
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@TodayFragment)
            mAdapter.setOnItemClickListener(this@TodayFragment)
        }

        if (data.isEmpty()) {
            page_layout.setPage(PageState.STATE_EMPTY)
        } else {
            page_layout.setPage(PageState.STATE_SUCCESS)
            mAdapter.mData.clear()
            mAdapter.mData.addAll(data)
            mAdapter.onLoadStatusNoMore()
        }
    }

    private fun setAdapter(data: MutableList<福利>, mAdapter: Today福利Adapter) {
        page_layout.setOnPageErrorClickListener { onReload() }
        recycler_view.apply {
            recycler_view.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            //添加Android自带的分割线
            recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
//            addItemDecoration(FollowersDecoration())
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@TodayFragment)
            mAdapter.setOnItemClickListener(this@TodayFragment)
        }

        if (data.isEmpty()) {
            page_layout.setPage(PageState.STATE_EMPTY)
        } else {
            page_layout.setPage(PageState.STATE_SUCCESS)
            mAdapter.mData.clear()
            mAdapter.mData.addAll(data)
            mAdapter.onLoadStatusNoMore()
        }
    }
}