package app.api.json.view.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import app.api.json.BuildConfig
import app.api.json.R
import app.api.json.databinding.CategoriesFragmentBinding
import app.api.json.model.categories.CategoriesRepository
import app.api.json.model.categories.Category
import app.api.json.configuration.CategoryType
import app.api.json.viewmodel.categories.CategoriesViewModel
import app.api.json.viewmodel.categories.CategoriesViewModelFactory


class CategoriesFragment : Fragment() {

    private val categoriesViewModel: CategoriesViewModel by viewModels {
        CategoriesViewModelFactory(CategoriesRepository(requireContext()))
    }
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        categoriesAdapter = CategoriesAdapter(arrayListOf(), CategoryListener())
        setHasOptionsMenu(true)
        return CategoriesFragmentBinding.inflate(inflater).apply {
            initializeRecyclerView(this)
        }.root
    }

    private fun initializeRecyclerView(binding: CategoriesFragmentBinding) {
        binding.recyclerView.adapter = categoriesAdapter
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        categoriesViewModel.categories.observe(viewLifecycleOwner) { categories ->
            categoriesAdapter.setCategories(categories)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_button -> {
                showCategorySelector()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private inner class CategoryListener: CategoriesAdapter.Listener {
        override fun onItemClick(category: Category) {
            val action = CategoriesFragmentDirections.actionCategoriesToItems(category.title)
            findNavController().navigate(action)
        }
    }

    private fun showCategorySelector() {
        val categories = CategoryType.values().map { it.name }.toTypedArray()
        AlertDialog.Builder(requireContext()).apply {
            setTitle(BuildConfig.CHOOSE_CATEGORY_PROMPT)
            setItems(categories) { _, position ->
                categoriesViewModel.addCategory(CategoryType.valueOf(categories[position]))
            }
        }.show()
    }
}