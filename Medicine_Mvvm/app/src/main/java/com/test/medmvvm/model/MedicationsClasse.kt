package com.test.medmvvm.model


import com.google.gson.annotations.SerializedName

data class MedicationsClasse(
    @SerializedName("className")
    var className: List<ClassName>?,
    @SerializedName("className2")
    var className2: List<ClassName>?
)