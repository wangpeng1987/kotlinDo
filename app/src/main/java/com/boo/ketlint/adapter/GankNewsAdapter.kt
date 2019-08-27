package com.boo.ketlint.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boo.ketlint.R
import com.boo.ketlint.net.GankNews
import com.boo.ketlint.widget.img.ImageLoader
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.item_view_list_layout.view.*

class GankNewsAdapter(val items: List<GankNews>, val itemClickListener: (GankNews) -> Unit) :
    RecyclerView.Adapter<GankNewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_list_layout, parent, false)
        return ViewHolder(view, itemClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(val view: View, val itemClickListener: (GankNews) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bind(news: GankNews) {
            view.title.text = "分类 :" + news.type
            view.desc.text = "简介 :" + news.desc
            view.setOnClickListener {
                itemClickListener(news)
            }

            view.author.text = "作者 :" + news.who
            var ct = news.createdAt
            val index = ct.indexOf("T")
            view.createTime.text = "创建时间 :" + ct.subSequence(0, index)
//            holder.setOnClickListener {
//                itemClickListener(news)
//            }
            var ims = news.images
            view.imLayout.visibility = View.GONE
            if (!ims.isNullOrEmpty()) {
                setImageView(ims, view)
            }
        }

        private fun setImageView(ims: MutableList<String>, view: View) {
            val imSize = ims.size
//        LOGS.e(" 图片数量 : " + imSize)
            view.imageView1.visibility = View.GONE
            view.imageView2.visibility = View.GONE
            view.imageView3.visibility = View.GONE
            view.imageView4.visibility = View.GONE
            view.imageView5.visibility = View.GONE
            if (imSize > 0) {
                view.imLayout.visibility = View.VISIBLE
                view.imageView1.visibility = View.VISIBLE
                ImageLoader.load(
                    view.context,
                    ims[0],
                    view.imageView1,
                    ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
                )

                if (imSize > 1) {
                    view.imageView2.visibility = View.VISIBLE
                    ImageLoader.load(
                        view.context,
                        ims[1],
                        view.imageView2,
                        ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
                    )

                    if (imSize > 2) {
                        view.imageView3.visibility = View.VISIBLE
                        ImageLoader.load(
                            view.context,
                            ims[2],
                            view.imageView3,
                            ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
                        )

                        if (imSize > 3) {
                            view.imageView4.visibility = View.VISIBLE
                            ImageLoader.load(
                                view.context,
                                ims[3],
                                view.imageView4,
                                ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
                            )

                            if (imSize > 4) {
                                view.imageView5.visibility = View.VISIBLE
                                ImageLoader.load(
                                    view.context,
                                    ims[4],
                                    view.imageView5,
                                    ImageLoader.getRoundRequest(10, RoundedCornersTransformation.CornerType.ALL)
                                )
                            }
                        }
                    }
                }

            }
        }
    }


}