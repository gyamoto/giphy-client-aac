package com.gyamoto.giphy_client_aac.ui.common

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

abstract class AbstractViewHolder<out T : ViewDataBinding>(val binding: T) :
    RecyclerView.ViewHolder(binding.root) {

    constructor(parent: ViewGroup, @LayoutRes layout: Int) : this(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), layout, parent, false)
    )
}
