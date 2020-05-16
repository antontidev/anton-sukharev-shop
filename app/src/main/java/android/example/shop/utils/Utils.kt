package android.example.shop.utils

import android.example.shop.R
import android.example.shop.domain.RemoteProduct
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun isSampleData(product: RemoteProduct): Boolean {
    return product.price < 0.001
}

fun EditText.setListener(lambda: (text: String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            lambda(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

fun viewVisibilityToBoolean(visible: Boolean): Int {
    return when (visible) {
        false -> View.VISIBLE
        else -> View.GONE
    }
}

fun EditText.showError(visible: Boolean) {
    val drawable = if (visible) R.drawable.ic_error
    else 0

    this.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
}

@BindingAdapter("priceFormatted")
fun TextView.formatPrice(item: RemoteProduct) {
    val priceUnit = resources.getString(R.string.price_units)
    text = formatString(item.price, priceUnit)
}

fun TextView.formatPrice(price: Double) {
    val priceUnit = resources.getString(R.string.price_units)
    text = formatString(price, priceUnit)
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