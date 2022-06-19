package com.vladbstrv.testtaskulybkaradugi.data

import com.vladbstrv.testtaskulybkaradugi.domain.entity.DataEntity

sealed class AppState {

    data class Success(val data: List<DataEntity>) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?): AppState()
}
