package android.example.shop.utils.adapters

import android.example.shop.databinding.CartItemBinding
import android.example.shop.domain.RemoteProduct
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.daimajia.swipe.SwipeLayout

class ShoppingCartAdapter(
    val deleteClickListener: (product: RemoteProduct) -> Unit,
    val detailInfoClickListener: (product: RemoteProduct) -> Unit
) : RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {
    private var data: List<RemoteProduct> = listOf()

    fun setData(list: List<RemoteProduct>) {
        data = list
    }

    inner class ViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RemoteProduct) {
            binding.shoppingCartItem = item
            binding.executePendingBindings()
            binding.deleteButton.setOnClickListener {
                deleteClickListener(item)
            }
            binding.userPart.setOnClickListener {
                detailInfoClickListener(item)
            }
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

        val item = ViewHolder(binding)

        item.setSwipes()
        return item
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}