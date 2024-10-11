package com.test.medmvvm.model


import com.google.gson.annotations.SerializedName

data class Diabete(
    @SerializedName("labs")
    var labs: List<Lab>?,
    @SerializedName("medications")
    var medications: List<Medication>?
)