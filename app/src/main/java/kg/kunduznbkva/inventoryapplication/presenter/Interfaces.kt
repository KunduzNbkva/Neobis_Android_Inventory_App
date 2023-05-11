package kg.kunduznbkva.inventoryapplication.presenter

import androidx.lifecycle.Lifecycle
import kg.kunduznbkva.inventoryapplication.model.Product


interface IViewProducts {
    fun viewProducts(products: List<Product>)
}

interface IMainPresenter {
    fun insertProduct(product: Product)
    fun deleteProduct(product: Product)
    fun getAllProducts()
    fun updateProduct(product: Product)
    fun searchProduct(searchQuery: String)
    fun attachView(view: IViewProducts)
    fun detachView()
}

interface IBottomSheetPresenter{
    fun deleteProduct(product: Product)
    fun searchProduct(searchQuery: String)
    fun restoreProduct(product: Product)
    fun archiveProduct(product: Product)
}




