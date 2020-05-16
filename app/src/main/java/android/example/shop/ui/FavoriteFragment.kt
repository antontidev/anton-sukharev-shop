package android.example.shop.ui

import android.example.shop.App
import android.example.shop.NavGraphXmlDirections
import android.example.shop.R
import android.example.shop.domain.RemoteProduct
import android.example.shop.presenter.FavoritePresenter
import android.example.shop.presenter.view.FavoriteView
import android.example.shop.utils.FavoriteChangedEvent
import android.example.shop.utils.adapters.ProductsAdapter
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.ui.BaseActivity
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.greenrobot.eventbus.Subscribe
import javax.inject.Inject

class FavoriteFragment : BaseFragment(), FavoriteView {

    @Inject
    lateinit var favoritePresenter: FavoritePresenter

    lateinit var deleteFavorite: MenuItem

    private val favoriteAdapter = ProductsAdapter(
        onProductClick = {
            favoritePresenter.showProductDetail(it)
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        App.appComponent.inject(this)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritePresenter.attachView(this)

        favoriteRv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        favoriteRv.adapter = favoriteAdapter

        //for right title name
        val activity = (requireActivity() as BaseActivity)
        activity.setSupportActionBar(toolBar)
        val title = resources.getString(R.string.favorite_header)
        toolBar.title = title
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_menu, menu)
        deleteFavorite = menu.findItem(R.id.action_delete_favorite)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete_favorite -> {
                favoritePresenter.showDeleteAllFavoriteDialog()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @Subscribe
    fun onFavoriteCapacityChanged(event: FavoriteChangedEvent) =
        favoritePresenter.setFavoriteMenu(event.count)

    override fun showFavoriteMenu(isEmpty: Boolean) {
        requireActivity().invalidateOptionsMenu()
        deleteFavorite.isVisible = isEmpty
    }

    override fun deleteAllFavorite() {
        val builder = AlertDialog.Builder(requireActivity())

        val title = resources.getString(R.string.delete_favorite_alert_title)
        builder.setTitle(title)

        val deleteConfirm = resources.getString(R.string.do_you_wanna_delete)
        builder.setMessage(deleteConfirm)

        // Set a positive button and its click listener on alert dialog
        val positiveText = resources.getString(R.string.yes_word)
        val negativeText = resources.getString(R.string.no_word)
        builder.setPositiveButton(positiveText) { dialog, which ->
            favoritePresenter.deleteAllFavorite()
            favoriteAdapter.notifyDataSetChanged()
        }
        // Display a negative button on alert dialog
        builder.setNegativeButton(negativeText) { dialog, which ->

        }
        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }

    override fun navigateToProductDetail(product: RemoteProduct) {

        val action = NavGraphXmlDirections.actionGlobalDetailFragment(product)

        findNavController().navigate(action)
    }

    override fun showFavoriteProducts(products: List<RemoteProduct>) {
        favoriteAdapter.setData(products)
    }
}
