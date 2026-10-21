package com.example.chucksolitario.data.holiday

import com.google.gson.annotations.SerializedName

data class HolidayDto(
    @SerializedName("date") val date: String?,
    @SerializedName("localName") val localName: String?
)
