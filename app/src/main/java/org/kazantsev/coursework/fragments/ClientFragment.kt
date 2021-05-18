package org.kazantsev.coursework.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.kazantsev.coursework.data.Client
import org.kazantsev.coursework.databinding.FragmentClientBinding
import org.kazantsev.coursework.viewmodels.ClientViewModel

class ClientFragment : Fragment() {

    // current client
    private var client = Client()

    // View Binding
    private var _binding: FragmentClientBinding? = null
    private val binding get() = _binding!!

    // SafeArgs
    private val args: ClientFragmentArgs by navArgs()

    // ViewModel
    private val viewModel: ClientViewModel by lazy {
        ViewModelProvider(this).get(ClientViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set received from args client's id
        viewModel.loadClient(args.id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // init ViewBinding
        _binding = FragmentClientBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root

        // FAB click listener
        binding.fab.setOnClickListener {
            if (args.id == 0)
                viewModel.insertClient(client)
            else
                viewModel.updateClient(client)

            findNavController().navigateUp()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // observer for viewModel's client LiveData
        viewModel.client.observe(viewLifecycleOwner) { client ->
            client?.let {
                this.client = client
                updateUI()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()

        // listeners
        binding.apply {
            name.doAfterTextChanged { client.name = it.toString() }
            inn.doAfterTextChanged { client.inn = it.toString() }
            addr.doAfterTextChanged { client.addr = it.toString() }
            phone.doAfterTextChanged { client.phone = it.toString() }
            email.doAfterTextChanged { client.email = it.toString() }
        }
    }

    // function for update the widgets according to client properties
    private fun updateUI() {
        binding.name.setText(client.name)
        binding.inn.setText(client.inn)
        binding.addr.setText(client.addr)
        binding.phone.setText(client.phone)
        binding.email.setText(client.email)
    }
}