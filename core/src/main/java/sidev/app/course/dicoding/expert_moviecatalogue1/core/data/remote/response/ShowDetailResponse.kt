package sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.response

import com.google.gson.annotations.SerializedName
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const

data class ShowDetailResponse(
    val id: Int,
    @SerializedName(Const.KEY_ORIGINAL_TITLE, alternate = [Const.KEY_ORIGINAL_NAME])
    val title: String,
    @SerializedName(Const.KEY_IMG)
    val posterPath: String?,
    @SerializedName(Const.KEY_RELEASE, alternate = [Const.KEY_FIRST_AIR_DATE])
    val releaseDate: String?,
    @SerializedName(Const.KEY_RATING)
    val rating: Double,

    @SerializedName(Const.KEY_MOVIE_DURATION)
    val duration: Int?,
    @SerializedName(Const.KEY_BACKDROP)
    val backdropPath: String?,
    val overview: String,
    val tagline: String,
    val genres: List<GenreResponse>,
)