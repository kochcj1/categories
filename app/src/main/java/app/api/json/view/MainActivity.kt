package app.api.json.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.api.json.R
import app.api.json.databinding.ActivityMainBinding
import app.api.json.model.Item
import app.api.json.model.ItemsRepository
import app.api.json.viewmodel.ItemsViewModel
import app.api.json.viewmodel.ItemsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val itemsViewModel: ItemsViewModel by viewModels {
        ItemsViewModelFactory(ItemsRepository())
    }
    private val itemsAdapter = ItemsAdapter(this, arrayListOf(), ItemListener())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeActionBar()
        initializeRecyclerView()
    }

    private fun initializeActionBar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun initializeRecyclerView() {
        binding.itemsRecyclerView.adapter = itemsAdapter
        binding.itemsRecyclerView.layoutManager = LinearLayoutManager(this)
        itemsViewModel.items.observe(this) { items ->
            itemsAdapter.setItems(items)
        }

        val swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
        itemsViewModel.loading.observe(this) { loading ->
            swipeRefreshLayout.isRefreshing = loading
        }
        swipeRefreshLayout.setOnRefreshListener {
            itemsViewModel.fetchItems()
        }
    }

    private inner class ItemListener: ItemsAdapter.Listener {
        override fun onItemClick(item: Item) {
            val bottomSheetFragment = BottomSheetWebViewFragment(item.url)
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
    }
}