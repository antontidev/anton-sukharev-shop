package android.example.shop.utils.adapters

import android.example.shop.databinding.HorizontalProductsItemBinding
import android.example.shop.domain.RemoteProduct
import android.example.shop.utils.RvItemClickListener
import android.example.shop.utils.bindImage
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class HorizontalProductsAdapter(
    private val onClickDescriptionListener: RvItemClickListener
) : RecyclerView.Adapter<HorizontalProductsAdapter.ViewHolder>() {
    private var data: List<RemoteProduct> = listOf()

    fun setData(list: List<RemoteProduct>) {
        data = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: HorizontalProductsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RemoteProduct) {
            binding.productImage.bindImage(item.imageUrl)
            binding.viewedRecentlyItem.setOnClickListener {
                onClickDescriptionListener.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HorizontalProductsItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}