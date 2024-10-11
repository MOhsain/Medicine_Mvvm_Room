package com.test.medmvvm.model


import com.google.gson.annotations.SerializedName

data class Lab(
    @SerializedName("missing_field")
    var missingField: String?
)