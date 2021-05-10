package org.kazantsev.coursework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.kazantsev.coursework.R
import org.kazantsev.coursework.data.Table
import org.kazantsev.coursework.databinding.ListItemTableBinding
import org.kazantsev.coursework.fragments.TablesFragmentDirections

class TableAdapter(
    private val tables: List<Table>
) : RecyclerView.Adapter<TableAdapter.TableViewHolder>() {

    inner class TableViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        // View binding
        private val binding = ListItemTableBinding.bind(item)

        // Current table
        private lateinit var table: Table

        init {
            // onClick listener
            item.setOnClickListener {
                // Navigate to TableFragment along the table name
                val action =
                    TablesFragmentDirections.actionTablesFragmentToTableFragment(table)
                item.findNavController().navigate(action)
            }
        }

        fun bind(table: Table) {
            // binding widgets and table properties
            binding.apply {
                name.text = table.ruName
            }
            // setting current table
            this.table = table
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TableViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_table, parent, false)

        return TableViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        val table = tables[position]
        holder.bind(table)
    }

    override fun getItemCount() = tables.size
}