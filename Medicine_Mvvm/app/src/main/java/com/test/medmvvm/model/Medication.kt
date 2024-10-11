package com.test.medmvvm.model


import com.google.gson.annotations.SerializedName

data class Medication(
    @SerializedName("medicationsClasses")
    var medicationsClasses: List<MedicationsClasse>?
)