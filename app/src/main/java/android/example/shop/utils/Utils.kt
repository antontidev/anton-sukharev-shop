package android.example.shop.utils

import android.example.shop.R
import android.example.shop.domain.model.TestShoppingCartItemModel
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("priceFormatted")
fun TextView.formatPrice(item: TestShoppingCartItemModel) {
    val priceUnit = resources.getString(R.string.price_units)
    text = formatString(item.price, priceUnit)
}

@BindingAdapter("discountFormatted")
fun TextView.formatDiscount(item: TestShoppingCartItemModel) {
    text = formatString(item.discount, "%")
}

@BindingAdapter("priceWithDiscountFormatted")
fun TextView.formatPriceWithDiscount(item: TestShoppingCartItemModel) {
    val totalPrice: Double = item.price * (1 - item.discount / 100.0)
    val priceUnit = resources.getString(R.string.price_units)

    text = formatString(totalPrice, priceUnit)
}


private fun formatString(num: Double, unit: String = ""): String {
    val intPrice = num.toInt().toString()

    return "$intPrice $unit"
}


class RvItemClickListener(val clickListener: (itemName: TestShoppingCartItemModel) -> Unit) {
    fun onClick(item: TestShoppingCartItemModel) = clickListener(item)
}