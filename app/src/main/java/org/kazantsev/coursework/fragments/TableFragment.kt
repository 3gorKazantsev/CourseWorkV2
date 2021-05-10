package org.kazantsev.coursework.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import org.kazantsev.coursework.adapters.*
import org.kazantsev.coursework.data.Table
import org.kazantsev.coursework.data.TempData
import org.kazantsev.coursework.databinding.FragmentTableBinding
import kotlin.math.abs

class TableFragment : Fragment() {

    // View Binding
    private var _binding: FragmentTableBinding? = null
    private val binding get() = _binding!!

    // Safe Args
    private val args: TableFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // init View Binding
        _binding = FragmentTableBinding.inflate(inflater, container, false)
        // inflate the layout for this fragment
        val view = binding.root

        // setting RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        setAdapter(args.selectedTable)
        // hide the FAB on scroll down
        hideFabOnScrollDown()

        // FAB click listener
        binding.fab.setOnClickListener {

        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // function for choosing the adapter depending on the safe args
    private fun setAdapter(selectedTable: Table) {
        binding.recyclerView.adapter = when (selectedTable) {
            Table.CLIENTS_TABLE -> ClientAdapter(TempData.getClientList())
            Table.DELIVERIES_TABLE -> DeliveryAdapter(TempData.getDeliveryList())
            Table.EMPLOYEES_TABLE -> EmployeeAdapter(TempData.getEmployeeList())
            Table.ORDERS_TABLE -> OrderAdapter(TempData.getOrderList())
            Table.PRODUCTS_TABLE -> ProductAdapter(TempData.getProductList())
            Table.SUPPLIERS_TABLE -> SupplierAdapter(TempData.getSupplierList())
        }
    }

    // function for hiding the FAB while scrolling the RecyclerView down
    private fun hideFabOnScrollDown() {
        binding.recyclerView.setOnScrollChangeListener { _, _, _, _, oldY ->
            when {
                oldY < 0 -> binding.fab.hide()
                else -> binding.fab.show()
            }
        }
    }
}