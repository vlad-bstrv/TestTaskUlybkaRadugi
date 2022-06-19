package com.vladbstrv.testtaskulybkaradugi.data.dto

import com.google.gson.annotations.SerializedName

data class DataDto(
    @SerializedName("data") val data : List<Data>,
)