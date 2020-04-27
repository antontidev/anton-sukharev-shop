package android.example.shop.utils.adapters

import android.example.shop.databinding.ItemCatalogBinding
import android.example.shop.domain.RemoteProduct
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private var data: List<RemoteProduct> = listOf()

    inner class ViewHolder(val binding: ItemCatalogBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RemoteProduct) {
            binding.itemCatalogName.text = item.name
        }

    }

    fun setData(list: List<RemoteProduct>) {
        data = list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCatalogBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}