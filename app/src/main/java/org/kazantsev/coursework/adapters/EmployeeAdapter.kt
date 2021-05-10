package org.kazantsev.coursework.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import org.kazantsev.coursework.R
import org.kazantsev.coursework.data.Employee
import org.kazantsev.coursework.databinding.ListItemEmployeeBinding
import org.kazantsev.coursework.fragments.TableFragmentDirections

class EmployeeAdapter(
    private val employees: List<Employee>
) : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    inner class EmployeeViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        // View binding
        private val binding = ListItemEmployeeBinding.bind(item)

        // Current employee
        private lateinit var employee: Employee

        // Context
        private val ctx = itemView.context

        init {
            // onClick listener
            item.setOnClickListener {
                // Navigate to EmployeeFragment
                val action = TableFragmentDirections.actionTableFragmentToEmployeeFragment()
                item.findNavController().navigate(action)
            }
        }

        fun bind(employee: Employee) {
            // binding widgets and employee properties
            binding.apply {
                fullName.text = employee.fullName
                post.text = ctx.getString(R.string.post, employee.post)
                phone.text = ctx.getString(R.string.phone, employee.phone)
            }
            // setting current employee
            this.employee = employee
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_employee, parent, false)

        return EmployeeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val employee = employees[position]
        holder.bind(employee)
    }

    override fun getItemCount() = employees.size
}