package com.boo.ketlint.ui.view.fragment

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.boo.ketlint.R
import com.boo.ketlint.adapter.FollowersDecoration
import com.boo.ketlint.adapter.TodayAdapter
import com.boo.ketlint.net.Android
import com.boo.ketlint.net.Category
import com.boo.ketlint.ui.contract.TodayContract
import com.boo.ketlint.ui.presenter.TodayPresenter
import com.boo.ketlint.ui.view.act.WebActivity
import com.boo.ketlint.widget.img.ImageLoader
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter
import com.ljb.page.PageState
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*
import kotlinx.android.synthetic.main.fragment_today.*
import kotlinx.android.synthetic.main.fragment_today.page_layout
import kotlinx.android.synthetic.main.layout_recycler_view.*
import kotlinx.android.synthetic.main.layout_recycler_view.recycler_view
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

//        ImageLoader.clearImageDiskCache(activity!!)

//        imageView.setOnClickListener({
//            ImageLoader.clearImageDiskCache(activity!!)
//            ImageLoader.load(
//                activity!!,
//                "https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture",
//                imageView,
//                ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
//            )
//        })
//        ImageLoader.load(
//            activity!!,
//            "https://uploadbeta.com/api/pictures/random/?key=BingEverydayWallpaperPicture",
//            imageView,
//            ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
//        )
        
        page_layout.setOnPageErrorClickListener { onReload() }
        recycler_view.apply {
            recycler_view.layoutManager = LinearLayoutManager(context)
            //添加Android自带的分割线
            recycler_view.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))


//            val manager = GridLayoutManager(context, 3)
//            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
//                override fun getSpanSize(position: Int): Int {
//                    val itemViewType = mAdapter.getItemViewType(position)
//                    return if (itemViewType == LoadMoreRecyclerAdapter.TYPE_LOAD_MORE) 3 else 1
//                }
//            }
//            layoutManager = manager
            addItemDecoration(FollowersDecoration())
            adapter = mAdapter
            mAdapter.setOnLoadMoreListener(this@TodayFragment)
            mAdapter.setOnItemClickListener(this@TodayFragment)
        }
    }

    override fun initData() {
        onReload()
    }

    override fun onLoadMore() {
//        page_layout.setPage(PageState.STATE_EMPTY)
//        getPresenter().onLoadMore()
    }

    private fun onReload() {
        page_layout.setPage(PageState.STATE_LOADING)
        getPresenter().onRefresh()
    }

    override fun showPage(data: MutableList<Android>, page: Int) {
        if (page == 1) {
            if (data.isEmpty()) {
                page_layout.setPage(PageState.STATE_EMPTY)
            } else {
                page_layout.setPage(PageState.STATE_SUCCESS)
                mAdapter.mData.clear()
                mAdapter.mData.addAll(data)
                mAdapter.onLoadStatusNoMore()
            }
        } else {
          //  mAdapter.mData.addAll(data)
           // mAdapter.onLoadStatus(data)
        }
    }

    override fun errorPage(t: Throwable, page: Int) {
        if (page == 1) {
            page_layout.setPage(PageState.STATE_ERROR)
        } else {
           // mAdapter.onErrorStatus()
        }
    }

    override fun onItemClick(view: View, position: Int) {
        val itemData = mAdapter.mData[position]
        WebActivity.startActivity(activity!!, itemData.url)
    }
}