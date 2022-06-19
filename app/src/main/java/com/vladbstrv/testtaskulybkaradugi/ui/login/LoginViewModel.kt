package com.vladbstrv.testtaskulybkaradugi.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladbstrv.testtaskulybkaradugi.data.AppState
import com.vladbstrv.testtaskulybkaradugi.domain.Repository
import kotlinx.coroutines.launch

class LoginViewModel(private val repo: Repository) : ViewModel() {
    private val _data: MutableLiveData<AppState> = MutableLiveData<AppState>()
    val data: LiveData<AppState> get() = _data
    fun getData() {
        _data.postValue(AppState.Loading(null))
        viewModelScope.launch {
            try {
                _data.postValue(AppState.Success(repo.getData()))
            } catch (e: Throwable) {
                _data.postValue(AppState.Error(e))
            }
        }
    }
}