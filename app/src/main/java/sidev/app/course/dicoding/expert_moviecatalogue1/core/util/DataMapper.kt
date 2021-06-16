package sidev.app.course.dicoding.expert_moviecatalogue1.core.util

import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.response.ShowDetailResponse
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.response.ShowListResponse
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.response.ShowResponse
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.ShowDetail
import sidev.lib.android.std.tool.util.`fun`.loge

object DataMapper {
    fun ShowResponse.toModel(): Show {
        val release = if(releaseDate?.isNotBlank() == true) releaseDate else null
        return Show(
            id, title, posterPath, release, rating
        )
    }
    fun ShowListResponse.toModel(): List<Show> = results.map { it.toModel() }

    fun ShowDetailResponse.toModel(): Show = Show(
        id, title, posterPath, releaseDate, rating
    )
    fun ShowDetailResponse.toDetailModel(): ShowDetail = ShowDetail(
        show = toModel(),
        genres = genres.map { it.name },
        duration = duration,
        tagline = tagline,
        overview = overview,
        backdropImg = backdropPath,
    )
}