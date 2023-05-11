package kg.kunduznbkva.inventoryapplication.presenter

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kg.kunduznbkva.inventoryapplication.database.local.ProductDatabase
import kg.kunduznbkva.inventoryapplication.database.local.RepositoryProduct
import kg.kunduznbkva.inventoryapplication.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PresenterMain(
    context: Context
) : ViewModel(),LifecycleObserver, IMainPresenter {
    private val repositoryProduct: RepositoryProduct
    private var view: IViewProducts? = null
    private var productsMutableList = MutableLiveData<List<Product>>()
    @SuppressLint("StaticFieldLeak")
    private var viewLifecycle: Lifecycle? = null

    init {
        val productDao = ProductDatabase.getInstance(context)?.productDao()
        repositoryProduct = productDao?.let { RepositoryProduct(it) }!!
    }

    fun observeProductsList(): MutableLiveData<List<Product>> {
        return productsMutableList
    }

    override fun getAllProducts() {
        CoroutineScope(Dispatchers.IO).launch {
            val productsList = repositoryProduct.getAllProducts()
            withContext(Dispatchers.Main) {
                productsMutableList.value = productsList
                view?.viewProducts(productsList)
            }
        }
    }

    override fun insertProduct(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            repositoryProduct.insertProduct(product)
        }
    }

    override fun deleteProduct(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            repositoryProduct.deleteProduct(product)
        }
    }

    override fun updateProduct(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            repositoryProduct.updateProduct(product)
        }
    }

    override fun searchProduct(searchQuery: String) {
        CoroutineScope(Dispatchers.Main).launch {
            val productsList = repositoryProduct.searchProduct(searchQuery)
            view?.viewProducts(productsList)
        }
    }

    override fun attachView(view: IViewProducts) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
        viewLifecycle = null
    }
}