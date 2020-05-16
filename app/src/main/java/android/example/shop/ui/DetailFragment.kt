package android.example.shop.ui

import android.example.shop.App
import android.example.shop.R
import android.example.shop.presenter.DetailPresenter
import android.example.shop.presenter.view.DescriptionView
import android.example.shop.utils.bindImage
import android.example.shop.utils.formatPrice
import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.navArgs
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : BaseFragment(),
    DescriptionView {
    private val args: DetailFragmentArgs by navArgs()

    @Inject
    lateinit var detailPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //if (!isSampleData(args.product)) {
        setHasOptionsMenu(true)
        //}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as BaseActivity).setSupportActionBar(toolBar)

        detailPresenter.attachView(this)
        detailPresenter.addToViewed(args.product)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_product_items, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_menu_favorite -> {
                setupFavoriteMenuItem(item)
            }
            R.id.action_menu_cart -> {
                setupCartMenuItem(item)
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun getFavoriteIcon(state: Boolean): Int {
        return when (state) {
            true -> R.drawable.ic_favorite
            else -> R.drawable.ic_favorite_border
        }
    }

    private fun getCartIcon(state: Boolean): Int {
        return when (state) {
            false -> R.drawable.ic_add_shopping_cart
            else -> R.drawable.ic_remove_shopping_cart_menu
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val inFavorite = detailPresenter.inFavorite(args.product)
        val iconFavorite = getFavoriteIcon(inFavorite)
        val itemFavorite = menu.findItem(R.id.action_menu_favorite)
        itemFavorite.isChecked = inFavorite
        itemFavorite.setIcon(iconFavorite)

        val inCart = detailPresenter.inCart(args.product)
        val iconCart = getCartIcon(inCart)
        val itemCart = menu.findItem(R.id.action_menu_cart)
        itemCart.isChecked = inCart
        itemCart.setIcon(iconCart)
    }

    override fun showDetail() {
        args.apply {
            description.text = product.description

            image.bindImage(product.imageUrl)
            price.formatPrice(product)
            (requireActivity() as BaseActivity).supportActionBar?.title = product.name
        }
    }

    private fun setupFavoriteMenuItem(item: MenuItem): Boolean {
        item.isChecked = !item.isChecked
        val icon = getFavoriteIcon(item.isChecked)

        if (item.isChecked) detailPresenter.addToFavorite(args.product)
        else detailPresenter.removeFromFavorite(args.product)

        item.setIcon(icon)
        return true
    }

    private fun setupCartMenuItem(item: MenuItem): Boolean {
        item.isChecked = !item.isChecked
        val icon = getCartIcon(item.isChecked)

        if (item.isChecked) detailPresenter.addToCart(args.product)
        else detailPresenter.removeFromCart(args.product)

        item.setIcon(icon)
        return true
    }
}