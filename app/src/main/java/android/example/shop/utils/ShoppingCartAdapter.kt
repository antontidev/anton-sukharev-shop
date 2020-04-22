package android.example.shop.utils

import android.example.shop.R
import android.example.shop.model.TestShoppingCartItemModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_shopping_cart_layout.view.*

class ShoppingCartAdapter(
    private val onClickAction: (string: TestShoppingCartItemModel) -> Unit
): RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {
    private var data: List<TestShoppingCartItemModel> = listOf()

    fun setData(list: List<TestShoppingCartItemModel>) {
        data = list
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(text: TestShoppingCartItemModel) {
            itemView.nameProduct.text = text.name
            itemView.descriptionProduct.text = text.description
            itemView.imageProduct.setBackgroundResource(text.id)
            itemView.priceProduct.text = text.price.toString()
            itemView.imageProduct.setOnClickListener {
                onClickAction(text)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_shopping_cart_layout, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}