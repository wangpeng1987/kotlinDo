package com.boo.ketlint.ui.view.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.boo.ketlint.LOGS
import com.boo.ketlint.R
import com.boo.ketlint.adapter.TodayAndroidAdapter
import com.boo.ketlint.net2.Constant
import com.boo.ketlint.net2.domain.*
import com.boo.ketlint.ui.contract.EmptyContract
import com.boo.ketlint.ui.presenter.EmptyPresenter
import com.boo.ketlint.ui.view.act.WebActivity
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter
import com.ljb.page.PageState
import kotlinx.android.synthetic.main.activity_main_content.*
import kotlinx.android.synthetic.main.fragment_empty.*
import mvp.ljb.kt.fragment.BaseMvpFragment


internal class EmptyFragment : BaseMvpFragment<EmptyContract.IPresenter>(), EmptyContract.IView,
    LoadMoreRecyclerAdapter.LoadMoreListener, LoadMoreRecyclerAdapter.OnItemClickListener {

    var dataAll: Category? = null
    var strArray: MutableList<String>? = null

    override fun getLayoutId() = R.layout.fragment_empty

    override fun registerPresenter() = EmptyPresenter::class.java

    private var title: String = ""
    private var index: Int = 0
    override fun initView() {
        title = arguments!!.getString("title")
        index = arguments!!.getInt("index")
        LOGS.e("EmptyFragment...title ...." + title)
        onReload()
        System.gc()
    }

    companion object {

        fun newInstance(index: Int, title: String): EmptyFragment {
            val fragment = EmptyFragment()
            val args = Bundle()
            args.putString("title", title)
            args.putInt("index", index)
            fragment.arguments = args
            return fragment
        }
    }


    private val mTodayAndroidAdapter by lazy { TodayAndroidAdapter(activity!!, mutableListOf()) }

    override fun onLoadMore() {
    }

    private fun onReload() {
        page_layout.setPage(PageState.STATE_LOADING)
        getPresenter().onRefresh()
    }

    override fun showPage(data: Category, page: Int) {
        dataAll = data
        strArray = data.category
        val dataShow: MutableList<Android> = mutableListOf()
        if (title.equals(Constant.strArrays[0])) {
            dataShow.addAll(data.results.Android)
        } else if (title.equals(Constant.strArrays[1])) {
            val appList: MutableList<App> = data.results.App
            for (app: App in appList) {
                var ai: MutableList<String> = app.images
                if (ai == null) {
                    ai = mutableListOf()
                }
                var android = Android(
                    app._id,
                    app.createdAt,
                    app.desc,
                    ai,
                    app.publishedAt,
                    app.source,
                    app.type,
                    app.url,
                    app.used,
                    app.who
                )
                dataShow.add(android)
            }
        } else if (title.equals(Constant.strArrays[2])) {
            val appList: MutableList<IOS> = data.results.iOS
            for (app: IOS in appList) {
                var ai: MutableList<String> = app.images
                if (ai == null) {
                    ai = mutableListOf()
                }
                var android = Android(
                    app._id,
                    app.createdAt,
                    app.desc,
                    ai,
                    app.publishedAt,
                    app.source,
                    app.type,
                    app.url,
                    app.used,
                    app.who
                )
                dataShow.add(android)
            }
        } else if (title.equals(Constant.strArrays[3])) {
            val appList: MutableList<前端> = data.results.前端
            for (app: 前端 in appList) {
                var ai: MutableList<String> = app.images
                if (ai == null) {
                    ai = mutableListOf()
                }
                var android = Android(
                    app._id,
                    app.createdAt,
                    app.desc,
                    ai,
                    app.publishedAt,
                    app.source,
                    app.type,
                    app.url,
                    app.used,
                    app.who
                )
                dataShow.add(android)
            }
        } else if (title.equals(Constant.strArrays[4])) {
            val appList: MutableList<拓展资源> = data.results.拓展资源
            for (app: 拓展资源 in appList) {
                var ai: MutableList<String> = app.images
                if (ai == null) {
                    ai = mutableListOf()
                }
                var android = Android(
                    app._id,
                    app.createdAt,
                    app.desc,
                    ai,
                    app.publishedAt,
                    app.source,
                    app.type,
                    app.url,
                    app.used,
                    app.who
                )
                dataShow.add(android)
            }
        } else if (title.equals(Constant.strArrays[5])) {
            val appList: MutableList<瞎推荐> = data.results.瞎推荐
            for (app: 瞎推荐 in appList) {
                var ai: MutableList<String> = app.images
                if (ai == null) {
                    ai = mutableListOf()
                }
                var android = Android(
                    app._id,
                    app.createdAt,
                    app.desc,
                    ai,
                    app.publishedAt,
                    app.source,
                    app.type,
                    app.url,
                    app.used,
                    app.who
                )
                dataShow.add(android)
            }
        } else if (title.equals(Constant.strArrays[6])) {
            val appList: MutableList<休息视频> = data.results.休息视频
            for (app: 休息视频 in appList) {
                var ai: MutableList<String> = app.images
                if (ai == null) {
                    ai = mutableListOf()
                }
                var android = Android(
                    app._id,
                    app.createdAt,
                    app.desc,
                    ai,
                    app.publishedAt,
                    app.source,
                    app.type,
                    app.url,
                    app.used,
                    app.who
                )
                dataShow.add(android)
            }
        } else if (title.equals(Constant.strArrays[7])) {
            val appList: MutableList<福利> = data.results.福利
            for (app: 福利 in appList) {
                var ai: MutableList<String> = app.images
                if (ai == null) {
                    ai = mutableListOf()
                }
                var android = Android(
                    app._id,
                    app.createdAt,
                    app.desc,
                    ai,
                    app.publishedAt,
                    app.source,
                    app.type,
                    app.url,
                    app.used,
                    app.who
                )
                dataShow.add(android)
            }
        }
        setAdapter(dataShow, mTodayAndroidAdapter)
    }

    override fun errorPage(t: Throwable, page: Int) {
        page_layout.setPage(PageState.STATE_ERROR)
    }

    override fun onItemClick(view: View, positio: Int) {
        val itemData = mTodayAndroidAdapter.mData[positio]
        WebActivity.startActivity(activity!!, itemData.url)
    }

    private fun setAdapter(data: MutableList<Android>, mAdapter: TodayAndroidAdapter) {
        page_layout.setOnPageErrorClickListener { onReload() }
        recycler_view.apply {
            recycler_view.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            //添加Android自带的分割线
            recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
//            addItemDecoration(FollowersDecoration())
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@EmptyFragment)
            mAdapter.setOnItemClickListener(this@EmptyFragment)
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
