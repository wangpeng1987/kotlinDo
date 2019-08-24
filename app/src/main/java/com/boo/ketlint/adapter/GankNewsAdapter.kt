package com.boo.ketlint.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.boo.ketlint.net.GankNews
import com.boo.ketlint.R
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
        }
    }
}