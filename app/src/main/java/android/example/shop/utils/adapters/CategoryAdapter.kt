package android.example.shop.utils.adapters

import android.content.Context
import android.example.shop.databinding.CategoryItemBinding
import android.example.shop.domain.RemoteCategory
import android.example.shop.domain.RemoteProduct
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(
    private val context: Context,
    private val onCategoryClick: (category: String) -> Unit,
    private val onProductClick: (product: RemoteProduct) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var categories: List<RemoteCategory> = listOf()

    fun setData(list: List<RemoteCategory>) {
        categories = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: CategoryItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(category: RemoteCategory) {
            binding.categoryName.text = category.name
            binding.productsPreview.adapter =
                CategoryProductPreviewAdapter(category.products, onProductClick)
            binding.productsPreview.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.viewAll.setOnClickListener {
                onCategoryClick(category.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = CategoryItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(item)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("Adapter", "OnBind")
        holder.bind(categories[position])
    }
}