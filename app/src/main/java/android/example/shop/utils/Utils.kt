package android.example.shop.utils

import android.example.shop.R
import android.example.shop.domain.model.TestShoppingCartItemModel
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("priceFormatted")
fun TextView.formatPrice(item: TestShoppingCartItemModel?) {
    item?.let {
        val intPrice = it.price.toInt().toString()
        val priceUnit = resources.getString(R.string.price_units)

        text =  "$intPrice $priceUnit"
    }
}