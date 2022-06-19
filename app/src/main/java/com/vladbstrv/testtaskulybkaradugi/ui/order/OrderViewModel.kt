package com.vladbstrv.testtaskulybkaradugi.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladbstrv.testtaskulybkaradugi.data.AppState
import com.vladbstrv.testtaskulybkaradugi.domain.Repository
import com.vladbstrv.testtaskulybkaradugi.domain.entity.DataEntity
import kotlinx.coroutines.launch

class OrderViewModel(private val repo: Repository) : ViewModel() {
    private val _data: MutableLiveData<AppState> = MutableLiveData<AppState>()
    val data: LiveData<AppState> get() = _data
    private val list: MutableList<DataEntity> = mutableListOf()

    fun getData() {
        _data.postValue(AppState.Loading(null))
        viewModelScope.launch {
            try {
                list.addAll(repo.getData())
                _data.postValue(AppState.Success(list))
            } catch (e: Throwable) {
                _data.postValue(AppState.Error(e))
            }
        }
    }

    fun addDataTop(dataItem: List<DataEntity>) {
        list.addAll(0, dataItem)
        _data.value = AppState.Success(list)
    }

    fun addDataBottom(dataItem: List<DataEntity>) {
        list.addAll(dataItem)
        _data.value = AppState.Success(list)
    }

    fun sortData() {
        list.sortBy { it.idPos }
    }
}