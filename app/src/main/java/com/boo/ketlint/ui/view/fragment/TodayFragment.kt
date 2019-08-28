package com.boo.ketlint.ui.view.fragment

import android.content.Context
import android.view.Gravity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.boo.ketlint.R
import com.boo.ketlint.net2.Constant
import com.boo.ketlint.ui.contract.TodayContract
import com.boo.ketlint.ui.presenter.TodayPresenter
import com.boo.tabloyout.badgeview.BadgeView
import kotlinx.android.synthetic.main.fragment_today.*
import mvp.ljb.kt.fragment.BaseMvpFragment
import java.util.*

/**
 * Created by L on 2017/7/19.
 */
class TodayFragment : BaseMvpFragment<TodayContract.IPresenter>(), TodayContract.IView {

    override fun getLayoutId() = R.layout.fragment_today

    override fun registerPresenter() = TodayPresenter::class.java

    override fun initView() {
//        stringList.addAll(Arrays.asList(*strArray))
        val fragmentList = ArrayList<Fragment>()
        for (i in Constant.strArrays.indices) {
            val fragment = EmptyFragment.newInstance(i, Constant.strArrays[i])
            fragmentList.add(fragment)
        }

        viewPager.adapter = IndexPagerAdapter(fragmentManager,  Constant.strArrays, fragmentList)
        viewPager.currentItem = 0
        tabLayoutFive.setupWithViewPager(viewPager)

        //add normal badgeView
        val tab1 = tabLayoutFive.getTabAt(0)
        if (tab1 != null && tab1.view != null) {
            var redDotBadgeView = BadgeView(context, tab1.tabView)
            redDotBadgeView.setBadgeMargin(BadgeView.POSITION_TOP_RIGHT)
            redDotBadgeView.setBadgeMargin(0, 0)
            redDotBadgeView.setOvalShape(3)
            redDotBadgeView.show()
        }

        //add number badgeView
        val tab2 = tabLayoutFive.getTabAt(1)
        if (tab2 != null && tab2.tabView != null) {
            var redNumberBadgeView = BadgeView(context, tab2.tabView)
            redNumberBadgeView.setBadgeMargin(BadgeView.POSITION_TOP_RIGHT)
            redNumberBadgeView.setBadgeMargin(dip2px(context, 8f), 0)
            redNumberBadgeView.setGravity(Gravity.CENTER)
            redNumberBadgeView.setText("9")
            redNumberBadgeView.show()
        }
    }

    internal inner class IndexPagerAdapter(
        fm: FragmentManager?,
        private val titleList: Array<String>,
        private val fragmentList: List<Fragment>
    ) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return titleList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }

    }

    fun dip2px(context: Context?, dpValue: Float): Int {
        val scale = context?.resources?.displayMetrics?.density
        return (dpValue * scale!! + 0.5f).toInt()
    }

}