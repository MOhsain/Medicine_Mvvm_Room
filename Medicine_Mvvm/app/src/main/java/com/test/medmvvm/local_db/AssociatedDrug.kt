package com.test.medmvvm.local_db


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "medicine")
data class AssociatedDrug(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    @SerializedName("dose")
    var dose: String? = "",

    @SerializedName("name")
    var name: String? = "",

    @SerializedName("strength")
    var strength: String? = ""
)