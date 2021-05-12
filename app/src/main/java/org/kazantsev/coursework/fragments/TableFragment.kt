package org.kazantsev.coursework.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import org.kazantsev.coursework.adapters.*
import org.kazantsev.coursework.data.Client
import org.kazantsev.coursework.data.Table
import org.kazantsev.coursework.data.TempData
import org.kazantsev.coursework.databinding.FragmentTableBinding
import org.kazantsev.coursework.viewmodels.TableViewModel

class TableFragment : Fragment() {

    // View Binding
    private var _binding: FragmentTableBinding? = null
    private val binding get() = _binding!!

    // Safe Args
    private val args: TableFragmentArgs by navArgs()

    // ViewModel
    private val viewModel: TableViewModel by lazy {
        ViewModelProvider(this).get(TableViewModel::class.java)
    }

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
        viewModel.allClients.value?.let { setAdapter(args.selectedTable, it) }
        // hide the FAB on scroll down
        hideFabOnScrollDown()

        // FAB click listener
        binding.fab.setOnClickListener {
            navigateToNewRecordFragment()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // observer
        viewModel.allClients.observe(viewLifecycleOwner) { clients ->
            setAdapter(args.selectedTable, clients)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // function for choosing the adapter depending on the safe args
    private fun setAdapter(selectedTable: Table, list: List<Any>) {
        binding.recyclerView.adapter = when (selectedTable) {
            Table.CLIENTS_TABLE -> ClientAdapter(list as List<Client>)
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

    // navigate to the detail fragment for create a new record in the database function
    private fun navigateToNewRecordFragment() {
        val action = when (args.selectedTable) {
            Table.CLIENTS_TABLE -> TableFragmentDirections.actionTableFragmentToClientFragment()
            Table.DELIVERIES_TABLE -> TableFragmentDirections.actionTableFragmentToDeliveryFragment()
            Table.EMPLOYEES_TABLE -> TableFragmentDirections.actionTableFragmentToEmployeeFragment()
            Table.ORDERS_TABLE -> TableFragmentDirections.actionTableFragmentToOrderFragment()
            Table.PRODUCTS_TABLE -> TableFragmentDirections.actionTableFragmentToProductFragment()
            else -> TableFragmentDirections.actionTableFragmentToSupplierFragment() // SUPPLIERS_TABLE
        }
        findNavController().navigate(action)
    }
}