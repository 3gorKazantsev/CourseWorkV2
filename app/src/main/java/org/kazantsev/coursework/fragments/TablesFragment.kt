package org.kazantsev.coursework.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.kazantsev.coursework.R
import org.kazantsev.coursework.adapters.TableAdapter
import org.kazantsev.coursework.databinding.FragmentTablesBinding
import org.kazantsev.coursework.viewmodels.TablesViewModel

class TablesFragment : Fragment() {

    // View binding
    private var _binding: FragmentTablesBinding? = null
    private val binding get() = _binding!!

    // ViewModel
    private val viewModel: TablesViewModel by lazy {
        ViewModelProvider(this).get(TablesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Init View binding
        _binding = FragmentTablesBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = TableAdapter(viewModel.getList())

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}