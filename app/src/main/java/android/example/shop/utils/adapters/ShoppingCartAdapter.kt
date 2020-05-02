package android.example.shop.utils.adapters

import android.example.shop.databinding.CartItemBinding
import android.example.shop.domain.model.TestShoppingCartItemModel
import android.example.shop.utils.RvItemClickListener
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout

class ShoppingCartAdapter(
    val deleteClickListener: RvItemClickListener,
    val detailInfoClickListener: RvItemClickListener
) : RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {
    private var data: List<TestShoppingCartItemModel> = listOf()

    fun setData(list: List<TestShoppingCartItemModel>) {
        data = list
    }

    inner class ViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TestShoppingCartItemModel) {
            binding.shoppingCartItem = item
            binding.executePendingBindings()
        }

        fun setSwipes() {
            binding.swipeLayout.apply {
                dragEdgeMap.clear()
                addDrag(SwipeLayout.DragEdge.Right, binding.bottomWrapper)
            }
            //swipeL.addDrag(SwipeLayout.DragEdge.Right, bottomWrapperS)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CartItemBinding.inflate(layoutInflater, parent, false)

        binding.deleteClickListener = deleteClickListener
        binding.detailInfoClickListener = detailInfoClickListener
        val item = ViewHolder(binding)

        item.setSwipes()
        return item
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}