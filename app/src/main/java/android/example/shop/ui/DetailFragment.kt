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
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : BaseFragment(),
    DescriptionView {
    private val args: DetailFragmentArgs by navArgs()

    @Inject
    lateinit var detailPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
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

        detailPresenter.attachView(this)
        detailPresenter.addToViewed(args.product)

        addToCartButton.setOnClickListener {
            detailPresenter.addToCart(args.product)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_product_items, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_menu_favorite -> {
                setupFavorite(item)
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

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val inFavorite = detailPresenter.inFavorite(args.product)

        val icon = getFavoriteIcon(inFavorite)

        val item = menu.findItem(R.id.action_menu_favorite)
        item.isChecked = inFavorite
        item.setIcon(icon)
    }

    override fun showDetail() {
        args.apply {
            description.text = product.description

            image.bindImage(product.imageUrl)
            price.formatPrice(product)
            goodName.text = product.name
        }
    }

    private fun setupFavorite(item: MenuItem): Boolean {
        item.isChecked = !item.isChecked
        val icon = getFavoriteIcon(item.isChecked)

        if (item.isChecked) detailPresenter.addToFavorite(args.product)
        else detailPresenter.removeFromFavorite(args.product)

        item.setIcon(icon)
        return true
    }
}