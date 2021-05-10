package org.kazantsev.coursework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.kazantsev.coursework.R
import org.kazantsev.coursework.data.Client
import org.kazantsev.coursework.data.Supplier
import org.kazantsev.coursework.databinding.ListItemSupplierBinding
import org.kazantsev.coursework.fragments.TableFragmentDirections

class SupplierAdapter(
    private val suppliers: List<Supplier>
) : RecyclerView.Adapter<SupplierAdapter.SupplierViewHolder>() {

    inner class SupplierViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        // View binding
        private val binding = ListItemSupplierBinding.bind(item)

        // Current supplier
        private lateinit var supplier: Supplier

        // Context
        private val ctx = itemView.context

        init {
            // onClick listener
            item.setOnClickListener {
                // Navigate to SupplierFragment
                val action = TableFragmentDirections.actionTableFragmentToSupplierFragment()
                item.findNavController().navigate(action)
            }
        }

        fun bind(supplier: Supplier) {
            // binding widgets and supplier properties
            binding.apply {
                name.text = supplier.name
                inn.text = ctx.getString(R.string.inn, supplier.inn)
                phone.text = ctx.getString(R.string.phone, supplier.phone)
                email.text = supplier.email
            }
            // setting current supplier
            this.supplier = supplier
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupplierViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_supplier, parent, false)

        return SupplierViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SupplierViewHolder, position: Int) {
        val supplier = suppliers[position]
        holder.bind(supplier)
    }

    override fun getItemCount() = suppliers.size
}