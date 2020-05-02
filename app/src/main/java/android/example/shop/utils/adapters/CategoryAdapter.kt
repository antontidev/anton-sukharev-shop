package android.example.shop.utils.adapters

import android.example.shop.R
import android.example.shop.databinding.CategoryItemBinding
import android.example.shop.databinding.CategoryItemBindingImpl
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(
    private val onNavigateClick: (string: String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var categories: List<String> = listOf()

    fun setData(list: List<String>) {
        categories = list
    }

    inner class ViewHolder(val binding: CategoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(text: String) {
            binding.categoryTv.text = text
            binding.root.setOnClickListener {
                onNavigateClick(text)
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