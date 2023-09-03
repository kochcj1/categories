package app.api.json.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import app.api.json.R
import app.api.json.databinding.CategoriesFragmentBinding
import app.api.json.model.CategoriesRepository
import app.api.json.model.Item
import app.api.json.viewmodel.ItemsViewModel
import app.api.json.viewmodel.ItemsViewModelFactory

class CategoriesFragment : Fragment() {

    private val itemsViewModel: ItemsViewModel by viewModels {
        ItemsViewModelFactory(CategoriesRepository())
    }
    private lateinit var itemsAdapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        itemsAdapter = ItemsAdapter(requireContext(), arrayListOf(), ItemListener())
        return CategoriesFragmentBinding.inflate(inflater).apply {
            initializeRecyclerView(this)
        }.root
    }

    private fun initializeRecyclerView(binding: CategoriesFragmentBinding) {
        binding.recyclerView.adapter = itemsAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        itemsViewModel.items.observe(viewLifecycleOwner) { items ->
            itemsAdapter.setItems(items)
        }
    }

    private inner class ItemListener: ItemsAdapter.Listener {
        override fun onItemClick(item: Item) {
            findNavController().navigate(R.id.items)
        }
    }
}