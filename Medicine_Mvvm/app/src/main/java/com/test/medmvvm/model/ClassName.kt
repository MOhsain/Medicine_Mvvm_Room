package com.test.medmvvm.model


import com.google.gson.annotations.SerializedName
import com.test.medmvvm.local_db.AssociatedDrug

data class ClassName(
    @SerializedName("associatedDrug")
    var associatedDrug: List<AssociatedDrug>?,
    @SerializedName("associatedDrug#2")
    var associatedDrug2: List<AssociatedDrug>?
)