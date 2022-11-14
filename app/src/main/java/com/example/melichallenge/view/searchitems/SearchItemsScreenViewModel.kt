package com.example.melichallenge.view.searchitems

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.melichallenge.model.Items
import com.example.melichallenge.repository.SearchItemsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchItemsScreenViewModel @Inject constructor(
    private val searchItemsRepository: SearchItemsRepository,
) : ViewModel() {

    private val _itemsSearched = MutableLiveData<List<Items>>()
    private val _totalPaging: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(-1)
    }
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getItemsSearched(searchItem: String): LiveData<List<Items>> {
        viewModelScope.launch (Dispatchers.IO) {
            _isLoading.postValue(true)
            val itemsSearched = searchItemsRepository.getItemsSearchedApi(searchItem)
            _itemsSearched.postValue(itemsSearched)

            val totalPaging = searchItemsRepository.getTotalPaging()
            _totalPaging.postValue(totalPaging)
            _isLoading.postValue(false)
        }

        return _itemsSearched
    }

    fun getTotalPaging(): LiveData<Int> {
        return _totalPaging
    }
}