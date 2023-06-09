package kg.kunduznbkva.inventoryapplication.utils

import androidx.recyclerview.widget.DiffUtil
import kg.kunduznbkva.inventoryapplication.model.Product

class ProductsDiffUtils(
    private val oldList: List<Product>,
    private val newList: List<Product>
) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}
