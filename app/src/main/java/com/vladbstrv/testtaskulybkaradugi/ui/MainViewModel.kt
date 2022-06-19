package com.vladbstrv.testtaskulybkaradugi.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladbstrv.testtaskulybkaradugi.domain.Repository
import com.vladbstrv.testtaskulybkaradugi.domain.entity.DataEntity
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repository): ViewModel() {
    val data: MutableLiveData<List<DataEntity>> = MutableLiveData<List<DataEntity>>()
    fun getData() {
        viewModelScope.launch {
            data.postValue(repo.getData())
        }
    }
}