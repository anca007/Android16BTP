package com.example.eni_shop.bo
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
@Entity
data class Article(
    @PrimaryKey(autoGenerate = false)
    var id: Long,
    @Json(name = "title")
    var titre: String,
    var description: String,
    @Json(name = "price")
    var prix: Double,
    @Json(name = "image")
    var urlImage: String,
    @Json(ignore = true)
    var dateSortie: Date? = Date()
) : Parcelable
