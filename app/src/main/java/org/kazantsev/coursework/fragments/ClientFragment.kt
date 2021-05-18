package org.kazantsev.coursework.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.kazantsev.coursework.R
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
        // attach ViewBinding
        _binding = FragmentClientBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        val view = binding.root

        // set imeOptions for the edit texts
        setImeOptions()

        // enable app bar menu
        setHasOptionsMenu(true)

        return view
    }

    // observers
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

    // detach ViewBinding
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // inflate app bar menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_menu, menu)
    }

    // on menu item selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menuDelete -> viewModel.deleteClient(client)
        }

        findNavController().popBackStack()

        return super.onOptionsItemSelected(item)
    }

    // listeners
    override fun onStart() {
        super.onStart()

        // EditText listeners
        binding.apply {
            name.doAfterTextChanged { client.name = it.toString() }
            inn.doAfterTextChanged { client.inn = it.toString() }
            addr.doAfterTextChanged { client.addr = it.toString() }
            phone.doAfterTextChanged { client.phone = it.toString() }
            email.doAfterTextChanged { client.email = it.toString() }
        }

        // FAB click listener
        binding.fab.setOnClickListener {
            if (args.id == 0)
                viewModel.insertClient(client)
            else
                viewModel.updateClient(client)

            findNavController().navigateUp()
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

    // if it is not a creating a new client then set the imeOptions to DONE
    private fun setImeOptions() {
        if (args.id != 0) {
            val opt = EditorInfo.IME_ACTION_DONE
            binding.apply {
                name.imeOptions = opt
                inn.imeOptions = opt
                addr.imeOptions = opt
                phone.imeOptions = opt
                email.imeOptions = opt
            }
        }
    }

}