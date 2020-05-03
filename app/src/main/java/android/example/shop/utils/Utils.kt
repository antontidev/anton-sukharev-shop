package android.example.shop.utils

import android.example.shop.R
import android.example.shop.domain.RemoteProduct
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("priceFormatted")
fun TextView.formatPrice(item: RemoteProduct) {
    val priceUnit = resources.getString(R.string.price_units)
    text = formatString(item.price, priceUnit)
}

@BindingAdapter("discountFormatted")
fun TextView.formatDiscount(product: RemoteProduct) {
    text = formatString(product.discountPercent, "%")
}

@BindingAdapter("priceWithDiscountFormatted")
fun TextView.formatPriceWithDiscount(item: RemoteProduct) {
    val totalPrice: Double = item.price * (1 - item.discountPercent / 100.0)
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

private fun formatString(num: Int, unit: String = ""): String {
    val intPrice = num.toString()

    return "$intPrice $unit"
}

class RvItemClickListener(val clickListener: (itemName: RemoteProduct) -> Unit) {
    fun onClick(item: RemoteProduct) = clickListener(item)
}