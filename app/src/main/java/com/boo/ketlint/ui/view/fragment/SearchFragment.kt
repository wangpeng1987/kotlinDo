package com.boo.ketlint.ui.view.fragment

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.boo.ketlint.R
import com.boo.ketlint.adapter.TodaySearchResultAdapter
import com.boo.ketlint.net2.domain.SearchList
import com.boo.ketlint.ui.contract.SearchContract
import com.boo.ketlint.ui.presenter.SearchPresenter
import com.boo.ketlint.ui.view.act.WebActivity
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter
import com.ljb.page.PageState
import kotlinx.android.synthetic.main.activity_main_content.*
import kotlinx.android.synthetic.main.fragment_today.*
import mvp.ljb.kt.fragment.BaseMvpFragment

/**
 * Created by L on 2017/7/19.
 */
class SearchFragment : BaseMvpFragment<SearchContract.IPresenter>(), SearchContract.IView,
    LoadMoreRecyclerAdapter.LoadMoreListener, LoadMoreRecyclerAdapter.OnItemClickListener {

    private val mAdapter by lazy { TodaySearchResultAdapter(activity!!, mutableListOf()) }

    override fun getLayoutId() = R.layout.fragment_search

    override fun registerPresenter() = SearchPresenter::class.java

    override fun initView() {
        page_layout.setOnPageErrorClickListener { onReload() }
        recycler_view.apply {
            recycler_view.layoutManager = LinearLayoutManager(context) as RecyclerView.LayoutManager?
            //添加Android自带的分割线
            recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
//            layoutManager = manager
//            addItemDecoration(FollowersDecoration())
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@SearchFragment)
            mAdapter.setOnItemClickListener(this@SearchFragment)
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

    override fun showPage(data: SearchList, page: Int) {
        if (page == 1) {
            if (data != null && data.results.size > 0) {
                page_layout.setPage(PageState.STATE_SUCCESS)
                mAdapter.mData.clear()
                mAdapter.mData.addAll(data.results)
                mAdapter.onLoadStatus(data.results)
            } else {
                page_layout.setPage(PageState.STATE_EMPTY)
            }
        } else {
            mAdapter.mData.addAll(data.results)
            mAdapter.onLoadStatus(data.results)
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