package com.boo.ketlint.adapter

import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boo.ketlint.R
import com.boo.ketlint.net2.domain.*
import com.boo.ketlint.widget.img.ImageLoader
import com.ljb.mvp.kotlin.widget.loadmore.LoadMoreRecyclerAdapter
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

/**
 * @author
 */
class TodayAndroidAdapter(mContext: Context, mData: MutableList<Android>) :
    LoadMoreRecyclerAdapter<Android>(mContext, mData) {

    class TodayHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title by lazy { itemView.findViewById<TextView>(R.id.title) }
        val desc by lazy { itemView.findViewById<TextView>(R.id.desc) }
        val createTime by lazy { itemView.findViewById<TextView>(R.id.createTime) }
        val author by lazy { itemView.findViewById<TextView>(R.id.author) }
        val imLayout by lazy { itemView.findViewById<LinearLayout>(R.id.imLayout) }
        val imageView1 by lazy { itemView.findViewById<ImageView>(R.id.imageView1) }
        val imageView2 by lazy { itemView.findViewById<ImageView>(R.id.imageView2) }
        val imageView3 by lazy { itemView.findViewById<ImageView>(R.id.imageView3) }
        val imageView4 by lazy { itemView.findViewById<ImageView>(R.id.imageView4) }
        val imageView5 by lazy { itemView.findViewById<ImageView>(R.id.imageView5) }
    }

    override fun getItemHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
        TodayHolder(mLayoutInflater.inflate(R.layout.item_view_list_layout, parent, false))

    override fun onBindData(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TodayHolder) {
            val item = mData[position]
            holder.title.text = "分类 :" + item.type
            holder.desc.text = "简介 :" + item.desc
            holder.author.text = "作者 :" + item.who
            var ct = item.createdAt
            val index = ct.indexOf("T")
            holder.createTime.text = "创建时间 :" + ct.subSequence(0, index)
//            holder.setOnClickListener {
//                itemClickListener(news)
//            }
            var ims = item.images
            holder.imLayout.visibility = GONE
            if (!ims.isNullOrEmpty()) {
                setImageView(ims, holder)
            }
        }
    }

    private fun setImageView(ims: MutableList<String>, holder: TodayHolder) {
        val imSize = ims.size
//        LOGS.e(" 图片数量 : " + imSize)
        holder.imageView1.visibility = GONE
        holder.imageView2.visibility = GONE
        holder.imageView3.visibility = GONE
        holder.imageView4.visibility = GONE
        holder.imageView5.visibility = GONE
        if (imSize > 0) {
            holder.imLayout.visibility = VISIBLE
            holder.imageView1.visibility = VISIBLE
            ImageLoader.load(
                mContext,
                ims[0],
                holder.imageView1,
                ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
            )

            if (imSize > 1) {
                holder.imageView2.visibility = VISIBLE
                ImageLoader.load(
                    mContext,
                    ims[1],
                    holder.imageView2,
                    ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
                )

                if (imSize > 2) {
                    holder.imageView3.visibility = VISIBLE
                    ImageLoader.load(
                        mContext,
                        ims[2],
                        holder.imageView3,
                        ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
                    )

                    if (imSize > 3) {
                        holder.imageView4.visibility = VISIBLE
                        ImageLoader.load(
                            mContext,
                            ims[3],
                            holder.imageView4,
                            ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
                        )

                        if (imSize > 4) {
                            holder.imageView5.visibility = VISIBLE
                            ImageLoader.load(
                                mContext,
                                ims[4],
                                holder.imageView5,
                                ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
                            )
                        }
                    }
                }
            }

        }
    }


}

/**
 * @author
 */
class TodaySearchResultAdapter(mContext: Context, mData: MutableList<Result>) :
    LoadMoreRecyclerAdapter<Result>(mContext, mData) {

    override fun getItemHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
        TodayHolder(mLayoutInflater.inflate(R.layout.item_view_list_layout, parent, false))

    override fun onBindData(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TodayHolder) {
            val item = mData[position]
            holder.title.text = "分类 :" + item.type
            holder.desc.text = "简介 :" + item.desc
            holder.author.text = "作者 :" + item.who
            var ct = item.createdAt
            val index = ct.indexOf("T")
            holder.createTime.text = "创建时间 :" + ct.subSequence(0, index)
//            holder.setOnClickListener {
//                itemClickListener(news)
//            }
            var ims = item.images
            holder.imLayout.visibility = GONE
            if (!ims.isNullOrEmpty()) {
                setImageView(ims, holder)
            }
        }
    }

    private fun setImageView(ims: MutableList<String>, holder: TodayHolder) {
        val imSize = ims.size
//        LOGS.e(" 图片数量 : " + imSize)
        holder.imageView1.visibility = GONE
        holder.imageView2.visibility = GONE
        holder.imageView3.visibility = GONE
        holder.imageView4.visibility = GONE
        holder.imageView5.visibility = GONE
        if (imSize > 0) {
            holder.imLayout.visibility = VISIBLE
            holder.imageView1.visibility = VISIBLE
            ImageLoader.load(
                mContext,
                ims[0],
                holder.imageView1,
                ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
            )

            if (imSize > 1) {
                holder.imageView2.visibility = VISIBLE
                ImageLoader.load(
                    mContext,
                    ims[1],
                    holder.imageView2,
                    ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
                )

                if (imSize > 2) {
                    holder.imageView3.visibility = VISIBLE
                    ImageLoader.load(
                        mContext,
                        ims[2],
                        holder.imageView3,
                        ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
                    )

                    if (imSize > 3) {
                        holder.imageView4.visibility = VISIBLE
                        ImageLoader.load(
                            mContext,
                            ims[3],
                            holder.imageView4,
                            ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
                        )

                        if (imSize > 4) {
                            holder.imageView5.visibility = VISIBLE
                            ImageLoader.load(
                                mContext,
                                ims[4],
                                holder.imageView5,
                                ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
                            )
                        }
                    }
                }
            }

        }
    }


    class TodayHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title by lazy { itemView.findViewById<TextView>(R.id.title) }
        val desc by lazy { itemView.findViewById<TextView>(R.id.desc) }
        val createTime by lazy { itemView.findViewById<TextView>(R.id.createTime) }
        val author by lazy { itemView.findViewById<TextView>(R.id.author) }
        val imLayout by lazy { itemView.findViewById<LinearLayout>(R.id.imLayout) }
        val imageView1 by lazy { itemView.findViewById<ImageView>(R.id.imageView1) }
        val imageView2 by lazy { itemView.findViewById<ImageView>(R.id.imageView2) }
        val imageView3 by lazy { itemView.findViewById<ImageView>(R.id.imageView3) }
        val imageView4 by lazy { itemView.findViewById<ImageView>(R.id.imageView4) }
        val imageView5 by lazy { itemView.findViewById<ImageView>(R.id.imageView5) }
    }

}


/**
 * @author
 */
class MeResultAdapter(mContext: Context, mData: MutableList<MeResult>) :
    LoadMoreRecyclerAdapter<MeResult>(mContext, mData) {

    override fun getItemHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder =
        TodayHolder(mLayoutInflater.inflate(R.layout.item_view_list_layout_me, parent, false))

    override fun onBindData(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TodayHolder) {
            val item = mData[position]
            ImageLoader.load(
                mContext,
                item.images,
                holder.imageView1,
                ImageLoader.getRoundRequest(60, RoundedCornersTransformation.CornerType.ALL)
            )
            holder.title.text = item.name
        }
    }

    class TodayHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title by lazy { itemView.findViewById<TextView>(R.id.title) }
        val imageView1 by lazy { itemView.findViewById<ImageView>(R.id.imageView1) }
    }

}
