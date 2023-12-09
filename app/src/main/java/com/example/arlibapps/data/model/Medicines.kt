package com.example.arlibapps.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Medicines(
    @SerializedName("medicines")
    val medicines: List<Medicine> = arrayListOf()
)

@Entity(tableName = "tbl_medicines")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val name: String = "",
    @ColumnInfo(name = "benefits")
    @SerializedName("benefits")
    val benefits: String = "",
    @ColumnInfo(name = "disease_cure")
    @SerializedName("disease_cure")
    val diseaseCure: String = "",
    @ColumnInfo(name = "dosage_mg")
    @SerializedName("dosage_mg")
    val dosageMg: Int = 0
)