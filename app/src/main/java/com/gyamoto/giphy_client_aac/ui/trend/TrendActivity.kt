package com.gyamoto.giphy_client_aac.ui.trend

import androidx.databinding.DataBindingUtil
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
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

        viewModel.error.observe(this) { error ->
            error?.let {
                Snackbar.make(binding.trendList, it.localizedMessage, Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

}
