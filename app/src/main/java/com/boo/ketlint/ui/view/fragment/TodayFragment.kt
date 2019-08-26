package com.boo.ketlint.ui.view.fragment

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.boo.ketlint.R
import com.boo.ketlint.adapter.FollowersDecoration
import com.boo.ketlint.adapter.TodayAdapter
import com.boo.ketlint.net.GankNews
import com.boo.ketlint.ui.contract.TodayContract
import com.boo.ketlint.ui.presenter.TodayPresenter
import com.boo.ketlint.ui.view.act.WebActivity
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter
import com.ljb.page.PageState
import kotlinx.android.synthetic.main.fragment_today.*
import kotlinx.android.synthetic.main.layout_recycler_view.*
import mvp.ljb.kt.fragment.BaseMvpFragment

/**
 * Created by L on 2017/7/19.
 */
class TodayFragment : BaseMvpFragment<TodayContract.IPresenter>(), TodayContract.IView,
        LoadMoreRecyclerAdapter.LoadMoreListener, LoadMoreRecyclerAdapter.OnItemClickListener {

    private val mAdapter by lazy { TodayAdapter(activity!!, mutableListOf()) }

    override fun getLayoutId() = R.layout.fragment_today

    override fun registerPresenter() = TodayPresenter::class.java

    override fun initView() {
        page_layout.setOnPageErrorClickListener { onReload() }
        recycler_view.apply {
            val manager = GridLayoutManager(context, 3)
            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val itemViewType = mAdapter.getItemViewType(position)
                    return if (itemViewType == LoadMoreRecyclerAdapter.TYPE_LOAD_MORE) 3 else 1
                }
            }
            layoutManager = manager
            addItemDecoration(FollowersDecoration())
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@TodayFragment)
            mAdapter.setOnItemClickListener(this@TodayFragment)
        }
    }

    override fun initData() {
        getPresenter().onRefresh()
    }

    override fun onLoadMore() {
        getPresenter().onLoadMore()
    }

    private fun onReload() {
        page_layout.setPage(PageState.STATE_LOADING)
        getPresenter().onRefresh()
    }

    override fun showPage(data: MutableList<GankNews>, page: Int) {
        if (page == 1) {
            if (data.isEmpty()) {
                page_layout.setPage(PageState.STATE_EMPTY)
            } else {
                page_layout.setPage(PageState.STATE_SUCCESS)
                mAdapter.mData.clear()
                mAdapter.mData.addAll(data)
                mAdapter.onLoadStatus(data)
            }
        } else {
            mAdapter.mData.addAll(data)
            mAdapter.onLoadStatus(data)
        }
    }

    override fun errorPage(t: Throwable, page: Int) {
        if (page == 1) {
            page_layout.setPage(PageState.STATE_ERROR)
        } else {
            mAdapter.onErrorStatus()
        }
    }

    override fun onItemClick(view: View, position: Int) {
        val itemData = mAdapter.mData[position]
        WebActivity.startActivity(activity!!, itemData.url)
    }
}