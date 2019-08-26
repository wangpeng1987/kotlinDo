package com.boo.ketlint.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.boo.ketlint.util.UIUtils

class FollowersDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = UIUtils.dip2px(view.context, 2.5f)
        outRect.bottom = UIUtils.dip2px(view.context, 2.5f)
        outRect.left = UIUtils.dip2px(view.context, 5f)
        outRect.right = UIUtils.dip2px(view.context, 5f)
    }
}