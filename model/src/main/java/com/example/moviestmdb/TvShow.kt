package com.example.moviestmdb

import com.google.gson.annotations.SerializedName

data class TvShow (
    @SerializedName("poster_path")
    val posterPath : String? = null,
    @SerializedName("adult")
    val isAdult : Boolean = false,
    @SerializedName("overview")
    val overView : String = "",
    @SerializedName("first_air_date")
    val firstAirDate : String? = null,
    @SerializedName("genre_ids")
    val genreList: List<Int> = listOf(),
    @SerializedName("id")
    val id : Int,
    @SerializedName("original_title")
    val originalTitle : String? = null,
    @SerializedName("original_language")
    val originalLanguage : String? = null,
    @SerializedName("name")
    val name : String? = null,
    @SerializedName("backdrop_path")
    val backdropPath : String? = null,
    @SerializedName("popularity")
    val popularity : Double? = null,
    @SerializedName("vote_count")
    val voteCount : Int,
    @SerializedName("video")
    val isVideo : Boolean = false,
    @SerializedName("vote_average")
    val voteAverage : Double,
) {
    val popularityPrecentage : Int get() = voteAverage.let { (it * 10).toInt() }
}