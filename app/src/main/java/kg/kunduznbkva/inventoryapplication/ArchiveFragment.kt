package kg.kunduznbkva.inventoryapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kg.kunduznbkva.inventoryapplication.model.Product
import kg.kunduznbkva.inventoryapplication.databinding.FragmentArchiveBinding
import kg.kunduznbkva.inventoryapplication.utils.BottomSheetDialog

class ArchiveFragment : Fragment(), OnLongItemClick, OnItemClickListener {

    private lateinit var binding: FragmentArchiveBinding
    private lateinit var adapter : ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ProductAdapter(this,this)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArchiveBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        getArchiveProductsFromLocalDB()
    }
    private fun initRecycler() {
        binding.mainRecycler.adapter = adapter
    }

    private fun getArchiveProductsFromLocalDB() {
        val listAllTasks = App.db.productDao().getAllArchiveProducts()
        adapter.addAllTasksRoom(listAllTasks)
    }

    override fun longClick(position: Int,productModel: Product) {
        createBottomSheet(productModel)
    }

    private fun createBottomSheet(product: Product){
        val dialog = BottomSheetDialog(product)
        dialog.show(parentFragmentManager,"ModalBottomSheet")
    }


    override fun onItemClick(productModel: Product, position: Int) {
        Toast.makeText(requireContext(),"Short CLick", Toast.LENGTH_SHORT).show()
    }


}