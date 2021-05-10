package org.kazantsev.coursework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.kazantsev.coursework.R
import org.kazantsev.coursework.data.Delivery
import org.kazantsev.coursework.databinding.ListItemDeliveryBinding
import org.kazantsev.coursework.fragments.TableFragmentDirections
import java.text.SimpleDateFormat
import java.util.*

class DeliveryAdapter(
    private val deliveries: List<Delivery>
) : RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder>() {

    inner class DeliveryViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        // View binding
        private val binding = ListItemDeliveryBinding.bind(item)

        // Current delivery
        private lateinit var delivery: Delivery

        // Context
        private val ctx = itemView.context

        init {
            // onClick listener
            item.setOnClickListener {
                // navigate to DeliveryFragment
                val action = TableFragmentDirections.actionTableFragmentToDeliveryFragment()
                item.findNavController().navigate(action)
            }
        }

        fun bind(delivery: Delivery) {
            // binding widgets and delivery properties
            binding.apply {
                id.text = ctx.getString(R.string.delivery, delivery.id)
                // get the name by id from the DB -------------------------------!!!!!!!!
                productName.text = "Название поставляемого товара: " + delivery.productId.toString()
                number.text = ctx.getString(R.string.number, delivery.number)
                pricePerPiece.text = ctx.getString(R.string.price_per_piece, delivery.pricePerPiece)
                // get the name by id from the DB -------------------------------!!!!!!!!
                supplierName.text = "Название поставщика" + delivery.id.toString()
                date.text = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(delivery.date)
            }
            // setting current client
            this.delivery = delivery
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_delivery, parent, false)

        return DeliveryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        val delivery = deliveries[position]
        holder.bind(delivery)
    }

    override fun getItemCount() = deliveries.size
}