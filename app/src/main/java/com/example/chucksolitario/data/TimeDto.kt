package com.example.chucksolitario.data

import com.google.gson.annotations.SerializedName

data class TimeDto(
    @SerializedName("date_time", alternate = ["datetime"]) val datetime: String?,
    @SerializedName("timezone", alternate = ["timeZone"]) val timezone: String?
)
