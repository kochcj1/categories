package app.api.categories.viewmodel.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.api.categories.model.categories.Categories
import app.api.categories.model.categories.CategoriesRepository
import app.api.categories.configuration.CategoryFactory
import app.api.categories.configuration.CategoryType

class CategoriesViewModel(private val repository: CategoriesRepository): ViewModel() {

    private val _categories = MutableLiveData<Categories>()
    val categories: LiveData<Categories> = _categories

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        _categories.value = ArrayList(repository.fetch())
    }

    fun addCategory(categoryType: CategoryType) {
        val category = CategoryFactory.from(categoryType)
        val currentList = _categories.value ?: emptyList()
        val newList = currentList + category
        _categories.value = ArrayList(newList)
        repository.add(category)
    }

}