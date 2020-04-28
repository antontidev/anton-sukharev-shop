package android.example.shop.utils.adapters

import android.example.shop.databinding.ItemMarsCatalogBinding
import android.example.shop.domain.MarsProperty
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProductsMarsAdapter: RecyclerView.Adapter<ProductsMarsAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemMarsCatalogBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MarsProperty) {
            binding.property = item
            binding.executePendingBindings()
        }
    }

    private var data: List<MarsProperty> = listOf()

    fun setData(list: List<MarsProperty>) {
        data = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsMarsAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMarsCatalogBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: ProductsMarsAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}