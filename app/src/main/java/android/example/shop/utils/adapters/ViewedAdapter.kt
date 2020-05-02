package android.example.shop.utils.adapters

import android.example.shop.databinding.ViewedItemBinding
import android.example.shop.domain.model.TestShoppingCartItemModel
import android.example.shop.utils.RvItemClickListener
import android.example.shop.utils.formatPrice
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ViewedAdapter(
    private val onClickDescriptionListener: RvItemClickListener
) : RecyclerView.Adapter<ViewedAdapter.ViewHolder>() {
    /**
     * In future [data] will contain Long id's [TestShoppingCartItemModel.productId]
     * But now without persistent database it should contain list of [TestShoppingCartItemModel]
     */
    private var data: List<TestShoppingCartItemModel> = listOf()

    fun setData(list: List<TestShoppingCartItemModel>) {
        data = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ViewedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TestShoppingCartItemModel) {
            binding.recentlyVisitedName.text = item.name
            binding.recentlyVisitedPrice.formatPrice(item)
            binding.viewedRecentlyItem.setOnClickListener {
                onClickDescriptionListener.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ViewedItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}