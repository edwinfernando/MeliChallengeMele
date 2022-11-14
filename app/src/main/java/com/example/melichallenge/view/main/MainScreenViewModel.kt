package com.example.melichallenge.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.melichallenge.model.Categories
import com.example.melichallenge.repository.CategoriesMainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val categoriesMain: CategoriesMainRepository
) : ViewModel() {

    private val _lCategories= MutableLiveData<List<Categories>>()
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getCategories(): LiveData<List<Categories>> {
        viewModelScope.launch (Dispatchers.IO) {
            _isLoading.postValue(true)
            val categories = categoriesMain.getCategoriesApi()
            _lCategories.postValue(categories)
            _isLoading.postValue(false)
        }

        return _lCategories
    }
}