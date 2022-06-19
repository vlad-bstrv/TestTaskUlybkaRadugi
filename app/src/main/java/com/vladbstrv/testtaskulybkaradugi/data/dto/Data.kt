package com.vladbstrv.testtaskulybkaradugi.data.dto

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id_pos") val idPos : Int,
    @SerializedName("id_record") val idRecord : Long,
    @SerializedName("nom_route") val nomRoute : Long?,
    @SerializedName("nom_zak") val nomZak : Long?,
    @SerializedName("id_hd_route") val idHdRoute: Long?,
    @SerializedName("nom_nakl") val nomNakl: String?,
)