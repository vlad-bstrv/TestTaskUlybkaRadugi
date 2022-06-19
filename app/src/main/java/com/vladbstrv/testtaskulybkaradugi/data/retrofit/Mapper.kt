package com.vladbstrv.testtaskulybkaradugi.data.retrofit

import com.vladbstrv.testtaskulybkaradugi.data.dto.DataDto
import com.vladbstrv.testtaskulybkaradugi.domain.entity.DataEntity

class Mapper {
    fun mapDataDtoToDataEntity(dto: DataDto): List<DataEntity> {
        val list: MutableList<DataEntity> = mutableListOf()
        for (data in dto.data) {
            list.add(
                DataEntity(
                    idPos = data.idPos,
                    idRecord = data.idRecord,
                    nomRoute = data.nomRoute ?: 0,
                    nomZak = data.nomZak ?: 0,
                    idHdRoute = data.idHdRoute ?: 0,
                    nomNakl = data.nomNakl ?: "null",
                )
            )
        }
        return list
    }
}