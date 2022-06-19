package com.vladbstrv.testtaskulybkaradugi.domain

import com.vladbstrv.testtaskulybkaradugi.domain.entity.DataEntity

interface Repository {
    suspend fun getData(): List<DataEntity>
}