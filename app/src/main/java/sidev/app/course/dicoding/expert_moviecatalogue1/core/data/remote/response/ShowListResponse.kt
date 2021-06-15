package sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ShowListResponse (
    val page: Int,

    @SerializedName("total_results")
    val totalResults: Int,

    @SerializedName("total_pages")
    val totalPages: Int,

    val results: List<ShowResponse>,
)