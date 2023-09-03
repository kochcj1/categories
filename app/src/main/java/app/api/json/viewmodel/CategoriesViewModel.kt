package app.api.json.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.api.json.model.Items
import app.api.json.model.ItemsRepository
import kotlinx.coroutines.launch

class ItemsViewModel(private val repository: ItemsRepository): ViewModel() {

    private val _items = MutableLiveData<Items>()
    val items: LiveData<Items> = _items

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        fetchItems()
    }

    fun fetchItems() {
        viewModelScope.launch {
            _loading.value = true
            _items.postValue(ArrayList(repository.fetch()))
            _loading.value = false
        }
    }

}