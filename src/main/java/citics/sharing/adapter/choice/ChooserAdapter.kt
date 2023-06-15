package citics.sharing.adapter.choice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import citics.sharing.data.model.others.ChooserItem
import com.sharing.databinding.LayoutJobItemBinding

/**
 * Created by ChinhQT on 19/10/2022.
 */
class ChooserAdapter(
    private val currentItem: ChooserItem?, private val onClick: (ChooserItem) -> Unit
) : ListAdapter<ChooserItem, ChooserAdapter.JobViewHolder>(ChooserDiffCallback) {

    class JobViewHolder(
        private val binding: LayoutJobItemBinding,
        private val currentItem: ChooserItem?,
        val onClick: (ChooserItem) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: ChooserItem) {
            itemView.setOnClickListener {
                onClick(currentItem)
            }

            binding.contentTxt.text = currentItem.name
            this.currentItem?.let {
                if (currentItem.id == this.currentItem.id) {
                    binding.checkIcon.visibility = View.VISIBLE
                } else {
                    binding.checkIcon.visibility = View.GONE
                }
            } ?: kotlin.run {
                binding.checkIcon.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding =
            LayoutJobItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding, currentItem, onClick)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

object ChooserDiffCallback : DiffUtil.ItemCallback<ChooserItem>() {
    override fun areItemsTheSame(oldItem: ChooserItem, newItem: ChooserItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ChooserItem, newItem: ChooserItem): Boolean {
        return oldItem.id == newItem.id
    }

}