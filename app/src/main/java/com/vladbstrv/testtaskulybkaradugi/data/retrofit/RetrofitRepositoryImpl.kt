package com.vladbstrv.testtaskulybkaradugi.data.retrofit

import com.vladbstrv.testtaskulybkaradugi.domain.Repository
import com.vladbstrv.testtaskulybkaradugi.domain.entity.DataEntity

class RetrofitRepositoryImpl(private val api: DataApi): Repository {
    override suspend fun getData(): List<DataEntity> {
        return Mapper().mapDataDtoToDataEntity(api.getData("").await())
    }
}