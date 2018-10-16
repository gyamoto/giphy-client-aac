package com.gyamoto.giphy_client_aac.ui.gif

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.gyamoto.giphy_client_aac.R
import com.gyamoto.giphy_client_aac.api.model.Gif
import com.gyamoto.giphy_client_aac.databinding.ActivityGifBinding

class GifActivity : AppCompatActivity() {

    private val gif: Gif by lazy { intent.getParcelableExtra<Gif>(EXTRA_GIF) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityGifBinding>(this, R.layout.activity_gif)
        binding.gif = gif

        Glide.with(this)
            .load(gif.images.original.url)
            .into(binding.image)

        binding.copy.setOnClickListener {
            (getSystemService(Context.CLIPBOARD_SERVICE) as? ClipboardManager)?.primaryClip =
                    ClipData.newPlainText(gif.title, gif.images.original.url)
            Toast.makeText(this, R.string.copied, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {

        private const val EXTRA_GIF = "gif"

        fun start(context: Context, gif: Gif) {
            context.startActivity(
                Intent(context, GifActivity::class.java).putExtras(
                    bundleOf(EXTRA_GIF to gif)
                )
            )
        }
    }
}
