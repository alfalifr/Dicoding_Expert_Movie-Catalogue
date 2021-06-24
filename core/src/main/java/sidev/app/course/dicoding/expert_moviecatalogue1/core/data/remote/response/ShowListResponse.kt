package sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.response

import com.google.gson.annotations.SerializedName
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const

data class ShowListResponse (
    @SerializedName("page")
    val page: Int,

    @SerializedName(Const.KEY_TOTAL_RESULTS)
    val totalResults: Int,

    @SerializedName(Const.KEY_TOTAL_PAGES)
    val totalPages: Int,

    @SerializedName("results")
    val results: List<ShowResponse>,
)