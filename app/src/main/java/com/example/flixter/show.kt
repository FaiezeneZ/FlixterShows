package com.example.flixter
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class show(
    @SerializedName("id")
    var id : Int,
    @SerializedName("name")
    var title: String? = null,
    @SerializedName("poster_path")
    var showCoverUrl: String? = null,
    @SerializedName("overview")
    var description: String? = null) : java.io.Serializable