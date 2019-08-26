package com.boo.ketlint.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boo.ketlint.R
import com.boo.ketlint.net.GankNews
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter

/**
 */
class TodayAdapter(mContext: Context, mData: MutableList<GankNews>) : LoadMoreRecyclerAdapter<GankNews>(mContext, mData) {


    override fun getItemHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
            FollowersViewHolder(mLayoutInflater.inflate(R.layout.item_view_list_layout, parent, false))

    override fun onBindData(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FollowersViewHolder) {
            val item = mData[position]
//            ImageLoader.load(mContext, item.avatar_url, holder.iv_avatar, ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL))
//            holder.tv_follower_name.text = item.login
        }
    }


    class FollowersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val iv_avatar by lazy { itemView.findViewById<ImageView>(R.id.iv_avatar) }
//        val tv_follower_name by lazy { itemView.findViewById<TextView>(R.id.tv_follower_name) }
    }

}