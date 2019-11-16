package com.gyamoto.giphy_client_aac.ui.trend

import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.AttributeSet
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.gyamoto.giphy_client_aac.R
import com.gyamoto.giphy_client_aac.api.model.Gif
import com.gyamoto.giphy_client_aac.databinding.ItemTrendBinding
import com.gyamoto.giphy_client_aac.ui.common.AbstractViewHolder

class TrendRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : RecyclerView(context, attrs, defStyle) {

    object Diff : DiffUtil.ItemCallback<Gif>() {

        override fun areItemsTheSame(old: Gif, new: Gif): Boolean = old.id == new.id

        override fun areContentsTheSame(old: Gif, new: Gif): Boolean = old == new
    }

    private val listAdapter = Adapter()

    var items: PagedList<Gif>? = null
        set(value) {
            field = value
            listAdapter.submitList(value)
        }

    var onClick: (Gif) -> Unit = {}

    init {
        adapter = listAdapter
        layoutManager = GridLayoutManager(
            context,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
    }

    inner class Adapter : PagedListAdapter<Gif, ViewHolder>(Diff) {

        override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
            return ViewHolder(parent)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            viewHolder.bind(getItem(position))
        }
    }

    inner class ViewHolder(parent: ViewGroup) :
        AbstractViewHolder<ItemTrendBinding>(parent, R.layout.item_trend) {

        fun bind(gif: Gif?) {

            gif ?: return

            Glide.with(binding.root)
                .load(gif.images.fixedHeight.url)
                .into(binding.gif)

            binding.gif.setOnClickListener {
                onClick(gif)
            }
        }
    }
}
