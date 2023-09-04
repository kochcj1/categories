package app.api.categories.view.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import app.api.categories.databinding.ItemsFragmentBinding
import app.api.categories.model.items.Item
import app.api.categories.model.items.ItemsRepository
import app.api.categories.viewmodel.items.ItemsViewModel
import app.api.categories.viewmodel.items.ItemsViewModelFactory

class ItemsFragment : Fragment() {

    private val itemsViewModel: ItemsViewModel by viewModels {
        val category = arguments?.let { ItemsFragmentArgs.fromBundle(it).category }
        ItemsViewModelFactory(ItemsRepository(category!!))
    }
    private lateinit var itemsAdapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        itemsAdapter = ItemsAdapter(requireContext(), arrayListOf(), ItemListener())
        return ItemsFragmentBinding.inflate(inflater).apply {
            initializeRecyclerView(this)
        }.root
    }

    private fun initializeRecyclerView(binding: ItemsFragmentBinding) {
        binding.recyclerView.adapter = itemsAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        itemsViewModel.items.observe(viewLifecycleOwner) { items ->
            itemsAdapter.setItems(items)
        }

        itemsViewModel.loading.observe(viewLifecycleOwner) { loading ->
            binding.swipeRefresh.isRefreshing = loading
        }
        binding.swipeRefresh.setOnRefreshListener {
            itemsViewModel.fetchItems()
        }
    }

    private inner class ItemListener: ItemsAdapter.Listener {
        override fun onItemClick(item: Item) {
            val bottomSheetFragment = BottomSheetWebViewFragment(item.url)
            bottomSheetFragment.show(childFragmentManager, bottomSheetFragment.tag)
        }
    }
}