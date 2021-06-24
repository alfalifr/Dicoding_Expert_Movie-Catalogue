package sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
)