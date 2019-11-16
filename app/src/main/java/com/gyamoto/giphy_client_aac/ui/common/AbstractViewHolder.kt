package com.gyamoto.giphy_client_aac.ui.common

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class AbstractViewHolder<out T : ViewDataBinding>(val binding: T) :
    RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup, @LayoutRes layout: Int) : this(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), layout, parent, false)
    )
}
