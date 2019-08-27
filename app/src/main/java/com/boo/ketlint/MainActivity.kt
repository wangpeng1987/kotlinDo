package com.boo.ketlint

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View.INVISIBLE
import android.widget.Toast
import androidx.recyclerview.widget.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.boo.ketlint.adapter.GankNewsAdapter
import com.boo.ketlint.net.DataLoader
import com.boo.ketlint.net.GankNews
import com.boo.ketlint.ui.view.act.WebActivity
import com.boo.ketlint.widget.img.ImageLoader
import com.ljb.page.PageState
import com.umeng.commonsdk.statistics.common.DeviceConfig
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*
import kotlinx.android.synthetic.main.layout_common_title.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        page_layout.setPage(PageState.STATE_LOADING)
        iv_back.visibility = INVISIBLE
        tv_title.text = getText(R.string.app_name)


    }



}
