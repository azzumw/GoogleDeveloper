package com.example.marsphotos.overview
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marsphotos.databinding.GridItemViewBinding
import com.example.marsphotos.network.MarsPhoto
import com.example.marsphotos.overview.PhotoGridAdapter

class PhotoGridAdapter : ListAdapter<MarsPhoto,PhotoGridAdapter.MarsPhotoViewHolder>(DiffCallback){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.MarsPhotoViewHolder {
        return MarsPhotoViewHolder(
            GridItemViewBinding.inflate(
            LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.MarsPhotoViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }

    class MarsPhotoViewHolder(private var binding:
                              GridItemViewBinding
    ):
        RecyclerView.ViewHolder(binding.root) {
            fun bind(marsPhoto: MarsPhoto){
                binding.photo = marsPhoto
                //Call executePendingBindings() after setting the property, which causes the update to execute immediately.
                binding.executePendingBindings()
            }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

}