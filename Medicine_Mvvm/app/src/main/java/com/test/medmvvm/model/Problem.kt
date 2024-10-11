package com.test.medmvvm.model


import com.google.gson.annotations.SerializedName

data class Problem(
    @SerializedName("Asthma")
    var asthma: List<Asthma>?,
    @SerializedName("Diabetes")
    var diabetes: List<Diabete>?
)