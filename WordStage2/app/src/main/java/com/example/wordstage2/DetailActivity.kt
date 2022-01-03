package com.example.wordstage2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordstage2.databinding.ActivityDetailBinding
import com.example.wordstage2.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {

    lateinit var activityBinding : ActivityDetailBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        recyclerView = activityBinding.recyclerView

        val letterId = intent.extras?.get(KEY_LETTER).toString()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(letterId ,this)

        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        title = getString(R.string.detail_prefix,letterId)
    }
}