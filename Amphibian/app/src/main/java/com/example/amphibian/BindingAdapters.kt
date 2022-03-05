package com.example.amphibian

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.amphibian.network.Amphibian
import com.example.amphibian.ui.AmphibianApiStatus
import com.example.amphibian.ui.AmphibianListAdapter

/**
 * Updates the data shown in the [RecyclerView]
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Amphibian>?) {
    val adapter = recyclerView.adapter as AmphibianListAdapter
    adapter.submitList(data)
}

/**
 * This binding adapter displays the [AmphibianApiStatus] of the network request in an image view.
 * When the request is loading, it displays a loading_animation.  If the request has an error, it
 * displays a broken image to reflect the connection error.  When the request is finished, it
 * hides the image view.
 */
@BindingAdapter("apiStatus")
fun bindStatus(statusImageView: ImageView, status: AmphibianApiStatus?) {
    when(status) {
        AmphibianApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        AmphibianApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        else ->
        statusImageView.visibility = View.GONE
    }
}
