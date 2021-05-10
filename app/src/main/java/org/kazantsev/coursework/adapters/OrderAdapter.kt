package org.kazantsev.coursework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.kazantsev.coursework.R
import org.kazantsev.coursework.data.Order
import org.kazantsev.coursework.databinding.ListItemOrderBinding
import org.kazantsev.coursework.fragments.TableFragmentDirections

class OrderAdapter(
    private val orders: List<Order>
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        // View binding
        private val binding = ListItemOrderBinding.bind(item)

        // Current order
        private lateinit var order: Order

        // Context
        private val ctx = itemView.context

        init {
            // onClick listener
            item.setOnClickListener {
                // Navigate to OrderFragment
                val action = TableFragmentDirections.actionTableFragmentToOrderFragment()
                item.findNavController().navigate(action)
            }
        }

        fun bind(order: Order) {
            // binding widgets and order properties
            binding.apply {
                id.text = ctx.getString(R.string.order, order.id)
                // get the name by id from the DB -------------------------------!!!!!!!!
                productName.text = "Название продукта" + order.productId.toString()
                // get the name by id from the DB -------------------------------!!!!!!!!
                employeeName.text = "ФИО работника" + order.employeeId.toString()
                // get the name by id from the DB -------------------------------!!!!!!!!
                clientName.text = ctx.getString(R.string.client, order.id.toString())
                state.text = ctx.getString(R.string.state, order.state)
                number.text = ctx.getString(R.string.number, order.number)
                price.text = ctx.getString(R.string.price, order.price)
            }
            // setting current order
            this.order = order
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_order, parent, false)

        return OrderViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.bind(order)
    }

    override fun getItemCount() = orders.size
}