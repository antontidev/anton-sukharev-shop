package android.example.shop.utils

import android.example.shop.R
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(
    private val onDeleteClick: (string: String) -> Unit
): RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var categories: List<String> = listOf()

    fun setData(list: List<String>) {
        categories = list
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(text: String) {
            itemView.categoryTv.text = text
            itemView.deleteIv.setOnClickListener {
                onDeleteClick(text)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i("Adapter", "OnCreate")
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("Adapter", "OnBind")
        holder.bind(categories[position])
    }
}