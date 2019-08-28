package com.boo.ketlint.ui.view.fragment

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.boo.ketlint.LOGS
import com.boo.ketlint.R
import com.boo.ketlint.adapter.MeResultAdapter
import com.boo.ketlint.adapter.TodaySearchResultAdapter
import com.boo.ketlint.net2.domain.MeResult
import com.boo.ketlint.net2.domain.Result
import com.boo.ketlint.net2.domain.SearchList
import com.boo.ketlint.ui.contract.MeContract
import com.boo.ketlint.ui.contract.SearchContract
import com.boo.ketlint.ui.presenter.MePresenter
import com.boo.ketlint.ui.presenter.SearchPresenter
import com.boo.ketlint.ui.view.act.WebActivity
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter
import com.ljb.page.PageState
import kotlinx.android.synthetic.main.activity_main_content.*
import kotlinx.android.synthetic.main.fragment_me.*
import kotlinx.android.synthetic.main.fragment_today.*
import mvp.ljb.kt.fragment.BaseMvpFragment

/**
 * Created by L on 2017/7/19.
 */
class MeFragment : BaseMvpFragment<MeContract.IPresenter>(), MeContract.IView,
    LoadMoreRecyclerAdapter.LoadMoreListener, LoadMoreRecyclerAdapter.OnItemClickListener {

    private val mAdapter by lazy { MeResultAdapter(activity!!, mutableListOf()) }

    override fun getLayoutId() = R.layout.fragment_me

    override fun registerPresenter() = MePresenter::class.java

    override fun initView() {
        System.gc()
        page_layout_me.setOnPageErrorClickListener { onReload() }
        recycler_view.apply {
            val manager = GridLayoutManager(context, 5)
            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val itemViewType = mAdapter.getItemViewType(position)
                    return if (itemViewType == LoadMoreRecyclerAdapter.TYPE_LOAD_MORE) 5 else 1
                }
            }
            layoutManager = manager
//            addItemDecoration(FollowersDecoration())
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@MeFragment)
            mAdapter.setOnItemClickListener(this@MeFragment)
        }
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {//输入后的监听
                LOGS.e("MeFragment 写入:    " + s)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {//输入后的监听
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {//输入文字产生变化的监听
            }
        })
        button.setOnClickListener({
            WebActivity.startActivity(activity!!, editText.text.toString())
        })
    }

    override fun initData() {
        getPresenter().onRefresh()
    }

    override fun onLoadMore() {
        getPresenter().onLoadMore()
    }

    private fun onReload() {
        page_layout_me.setPage(PageState.STATE_LOADING)
        getPresenter().onRefresh()
    }

    override fun showPage(data: MutableList<MeResult>) {
//        if (page == 1) {
        if (data != null && data.size > 0) {
            page_layout_me.setPage(PageState.STATE_SUCCESS)
            mAdapter.mData.clear()
            mAdapter.mData.addAll(data)
            mAdapter.onLoadStatus(data)
        } else {
            page_layout_me.setPage(PageState.STATE_EMPTY)
        }
//        } else {
//            mAdapter.mData.addAll(data.results)
        mAdapter.onLoadStatusNoMore()
//        }
    }

    override fun errorPage(t: Throwable) {
//        if (page == 1) {
        page_layout_me.setPage(PageState.STATE_ERROR)
//        } else {
//            mAdapter.onErrorStatus()
//        }
    }

    override fun onItemClick(view: View, position: Int) {
        val itemData = mAdapter.mData[position]
        WebActivity.startActivity(activity!!, itemData.url)
    }

}