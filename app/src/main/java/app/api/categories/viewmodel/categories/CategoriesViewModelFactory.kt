package app.api.categories.viewmodel.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.api.categories.model.categories.CategoriesRepository

class CategoriesViewModelFactory(private val repository: CategoriesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriesViewModel::class.java)) {
            return CategoriesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
