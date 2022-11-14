package com.example.melichallenge.view.detailsitem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.melichallenge.model.ItemDetailsAndDescription
import com.example.melichallenge.repository.ItemsDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsItemScreenViewModel @Inject constructor(
    private val itemsDetailRepository: ItemsDetailRepository
) : ViewModel() {

    private val _itemDescription = MutableLiveData<ItemDetailsAndDescription>()
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getItemDescriptionById(idItem: String): LiveData<ItemDetailsAndDescription> {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val itemDescription = itemsDetailRepository.getDetailsAndDescriptionItemApi(idItem)
            _itemDescription.postValue(itemDescription)
            _isLoading.postValue(false)
        }

        return _itemDescription
    }
}