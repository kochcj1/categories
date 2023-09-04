package app.api.categories.viewmodel.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.api.categories.model.items.ItemsRepository

class ItemsViewModelFactory(private val repository: ItemsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemsViewModel::class.java)) {
            return ItemsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
