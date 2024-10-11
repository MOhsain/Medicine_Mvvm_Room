package com.test.medmvvm.model


import com.google.gson.annotations.SerializedName

data class JsonParserModel(
    @SerializedName("problems")
    var problems: List<Problem>?
)