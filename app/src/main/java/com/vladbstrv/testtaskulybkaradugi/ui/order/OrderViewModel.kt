package com.vladbstrv.testtaskulybkaradugi.ui.order

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladbstrv.testtaskulybkaradugi.data.AppState
import com.vladbstrv.testtaskulybkaradugi.domain.Repository
import com.vladbstrv.testtaskulybkaradugi.domain.entity.DataEntity
import kotlinx.coroutines.launch

class OrderViewModel(private val repo: Repository) : ViewModel() {
    val data: MutableLiveData<AppState> = MutableLiveData<AppState>()

    fun getData() {
        data.postValue(AppState.Loading(null))
        viewModelScope.launch {
            try {
                data.postValue(AppState.Success(repo.getData()))
            } catch (e: Throwable) {
                data.postValue(AppState.Error(e))
            }
        }
    }

    fun addData(dataItem: List<DataEntity>) {

    }
}