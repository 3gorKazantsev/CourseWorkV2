package org.kazantsev.coursework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.kazantsev.coursework.R
import org.kazantsev.coursework.data.Product
import org.kazantsev.coursework.databinding.ListItemProductBinding
import org.kazantsev.coursework.fragments.TableFragmentDirections

class ProductAdapter(
    private val products: List<Product>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        // View binding
        private val binding = ListItemProductBinding.bind(item)

        // Current product
        private lateinit var product: Product

        // Context
        private val ctx = itemView.context

        init {
            // onClick listener
            item.setOnClickListener {
                // Navigate to ProductFragment
                val action = TableFragmentDirections.actionTableFragmentToProductFragment()
                item.findNavController().navigate(action)
            }
        }

        fun bind(product: Product) {
            // binding widgets and product properties
            binding.apply {
                name.text = product.name
                id.text = ctx.getString(R.string.id, product.id)
                rest.text = ctx.getString(R.string.rest, product.rest)
                discount.text = ctx.getString(R.string.discount, product.discount)
                price.text = ctx.getString(R.string.price, product.price)
                inStock.text = ctx.getString(R.string.in_stock, product.inStock)
            }
            // setting current product
            this.product = product
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_product, parent, false)

        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
    }

    override fun getItemCount() = products.size
}