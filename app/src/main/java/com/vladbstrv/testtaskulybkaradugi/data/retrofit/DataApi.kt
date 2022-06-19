package com.vladbstrv.testtaskulybkaradugi.data.retrofit

import com.vladbstrv.testtaskulybkaradugi.data.dto.DataDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Header

interface DataApi {
@GET("api/getdocumentlist")
    fun getData(@Header("Authorization") token: String): Deferred<DataDto>
}