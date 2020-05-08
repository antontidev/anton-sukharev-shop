package android.example.shop.utils.adapters

import android.example.shop.databinding.CategoryProductPreviewBinding
import android.example.shop.domain.RemoteProduct
import android.example.shop.utils.bindImage
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CategoryProductPreviewAdapter(
    categoryProducts: List<RemoteProduct>,
    private val onProductClick: (product: RemoteProduct) -> Unit
) : RecyclerView.Adapter<CategoryProductPreviewAdapter.ViewHolder>() {

    private var products: List<RemoteProduct> = categoryProducts

    fun setData(list: List<RemoteProduct>) {
        products = list
    }

    inner class ViewHolder(
        private val binding: CategoryProductPreviewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: RemoteProduct) {
            binding.categoryIv.bindImage(product.imageUrl)
            binding.categoryTv.text = product.name
            binding.root.setOnClickListener {
                onProductClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val item = CategoryProductPreviewBinding.inflate(inflater, parent, false)

        return ViewHolder(item)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("Adapter", "OnBind")
        holder.bind(products[position])
    }
}