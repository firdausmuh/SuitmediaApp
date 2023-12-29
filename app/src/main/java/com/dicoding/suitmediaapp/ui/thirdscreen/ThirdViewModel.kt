package com.dicoding.suitmediaapp.ui.thirdscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.data.DataItem
import com.dicoding.repository.Repository

class ThirdViewModel(repository: Repository) : ViewModel() {
    val listUser: LiveData<PagingData<DataItem>> = repository.getUsers().cachedIn(viewModelScope)
}