package android.example.shop.utils

import android.example.shop.R
import android.example.shop.domain.model.TestShoppingCartItemModel
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(this)
    }
}

private fun formatString(num: Double, unit: String = ""): String {
    val intPrice = num.toInt().toString()

    return "$intPrice $unit"
}


class RvItemClickListener(val clickListener: (itemName: TestShoppingCartItemModel) -> Unit) {
    fun onClick(item: TestShoppingCartItemModel) = clickListener(item)
}