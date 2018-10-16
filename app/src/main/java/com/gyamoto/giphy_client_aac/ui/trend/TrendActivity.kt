package com.gyamoto.giphy_client_aac.ui.trend

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gyamoto.giphy_client_aac.R
import com.gyamoto.giphy_client_aac.databinding.ActivityTrendBinding
import com.gyamoto.giphy_client_aac.ui.gif.GifActivity
import com.gyamoto.giphy_client_aac.uti.observe
import dagger.android.AndroidInjection
import javax.inject.Inject

class TrendActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: TrendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityTrendBinding>(this, R.layout.activity_trend)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel

        binding.trendList.onClick = {
            GifActivity.start(this, it)
        }

        viewModel.images.observe(this) {
            Log.i("YoSuccess", it.toString())
        }

        viewModel.error.observe(this) {
            Log.i("YoError", it.toString())
        }

        viewModel.loadImages()
    }

//    override fun onStart() {
//        super.onStart()
//
////        viewModel.loadImages()
//    }

}
