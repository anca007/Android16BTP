package com.example.eni_shop.bo
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity
data class Article(
    @PrimaryKey(autoGenerate = false)
    var id: Long,
    var titre: String,
    var description: String,
    var prix: Double,
    var urlImage: String,
    var dateSortie: Date?
) : Parcelable
