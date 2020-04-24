package android.example.shop.utils

import android.example.shop.databinding.ItemShoppingCartBinding
import android.example.shop.domain.model.TestShoppingCartItemModel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout
import kotlinx.android.synthetic.main.item_shopping_cart.view.*

class ShoppingCartAdapter(
     val deleteClickListener: ShoppingItemClickListener,
     val detailInfoClickListener: ShoppingItemClickListener
): RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {
    private var data: List<TestShoppingCartItemModel> = listOf()

    fun setData(list: List<TestShoppingCartItemModel>) {
        data = list
    }

    inner class ViewHolder(private val binding: ItemShoppingCartBinding): RecyclerView.ViewHolder(binding.root) {
        private val swipeL: SwipeLayout = binding.swipeLayout
        private val bottomW = binding.bottomWrapper
        private val bottomWrapperS = binding.bottomWrapperSync

        fun bind(item: TestShoppingCartItemModel) {
            binding.shoppingCartItem = item
            binding.deleteClickListener = deleteClickListener
            binding.detailInfoClickListener = detailInfoClickListener
            binding.executePendingBindings()
        }

        fun setSwipes() {
            swipeL.dragEdgeMap.clear()
            swipeL.addDrag(SwipeLayout.DragEdge.Left, bottomW)
            swipeL.addDrag(SwipeLayout.DragEdge.Right, bottomWrapperS)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var binding = ItemShoppingCartBinding.inflate(layoutInflater, parent, false)

        val item =  ViewHolder(binding)

        item.setSwipes()
        return item
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class ShoppingItemClickListener(val clickListener: (itemName: TestShoppingCartItemModel) -> Unit) {
        fun onClick(item: TestShoppingCartItemModel) = clickListener(item)
    }
}