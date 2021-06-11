package sidev.app.course.dicoding.expert_movicatalogue1.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ShowResponse(
    val id: Int,
    @SerializedName("original_title", alternate = ["original_name"])
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date", alternate = ["first_air_date"])
    val releaseDate: String,
    @SerializedName("vote_average")
    val rating: Double,
)