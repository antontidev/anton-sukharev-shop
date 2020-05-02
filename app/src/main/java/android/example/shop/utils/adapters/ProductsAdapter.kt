package android.example.shop.utils.adapters

import android.example.shop.databinding.CatalogItemBinding
import android.example.shop.domain.MarsProperty
import android.example.shop.domain.RemoteProduct
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private var data: List<RemoteProduct> = listOf()

    inner class ViewHolder(private val binding: CatalogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RemoteProduct) {
            binding.itemCatalogName.text = item.name
        }
    }

    fun setData(list: List<RemoteProduct>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CatalogItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}