package android.example.shop.utils

import android.example.shop.R
import android.example.shop.model.TestShoppingCartItemModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import kotlinx.android.synthetic.main.item_shopping_cart.view.*
import kotlinx.android.synthetic.main.item_shopping_cart.view.bottomWrapper

class ShoppingCartAdapter(
    private val onClickAction: (string: TestShoppingCartItemModel) -> Unit
): RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {
    private var data: List<TestShoppingCartItemModel> = listOf()

    fun setData(list: List<TestShoppingCartItemModel>) {
        data = list
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: TestShoppingCartItemModel) {
            itemView.deleteButton.setOnClickListener {
                onClickAction(item)
            }
            itemView.priceText.text = item.price.toString()
            itemView.discountPercentText.text = 0.toString()

            itemView.productNameText.text = item.name
            itemView.descriptionText.text = item.description
//            itemView.imageProduct.setOnClickListener {
//                onClickAction(text)
//            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         return ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_shopping_cart, parent, false))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}