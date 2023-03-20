package com.example.flixter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var overviewTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mediaImageView = findViewById(R.id.showImage)
        titleTextView = findViewById(R.id.showName)
        overviewTextView = findViewById(R.id.showDescription)



        val show = intent.getSerializableExtra(SHOWS_EXTRA) as show

        titleTextView.text = show.title
        overviewTextView.text = show.description

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/" + show.showCoverUrl)
            .into(mediaImageView)

    }
}