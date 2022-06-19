package com.vladbstrv.testtaskulybkaradugi.domain.entity

import com.google.gson.annotations.SerializedName

data class DataEntity(
    val idPos : Int,
    val idRecord : Long,
    val nomRoute : Long,
    val nomZak : Long,
    val idHdRoute: Long,
    val nomNakl: String,
)
