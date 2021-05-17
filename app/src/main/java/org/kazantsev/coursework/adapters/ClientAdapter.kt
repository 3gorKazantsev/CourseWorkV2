package org.kazantsev.coursework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.kazantsev.coursework.R
import org.kazantsev.coursework.data.Client
import org.kazantsev.coursework.databinding.ListItemClientBinding
import org.kazantsev.coursework.fragments.TableFragmentDirections

class ClientAdapter(
    private val clients: List<Client>
) : RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    inner class ClientViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        // View binding
        private val binding = ListItemClientBinding.bind(item)

        // Current client
        private lateinit var client: Client

        // Context
        private val ctx = itemView.context

        init {
            // onClick listener
            item.setOnClickListener {
                // navigate to ClientFragment
                val action = TableFragmentDirections.actionTableFragmentToClientFragment(client.id)
                item.findNavController().navigate(action)
            }
        }

        fun bind(client: Client) {
            // binding widgets and client properties
            binding.apply {
                name.text = client.name
                inn.text = ctx.getString(R.string.inn, client.inn)
                addr.text = ctx.getString(R.string.addr, client.addr)
                phone.text = ctx.getString(R.string.phone, client.phone)
                email.text = client.email
            }
            // setting current client
            this.client = client
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_client, parent, false)

        return ClientViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = clients[position]
        holder.bind(client)
    }

    override fun getItemCount() = clients.size
}