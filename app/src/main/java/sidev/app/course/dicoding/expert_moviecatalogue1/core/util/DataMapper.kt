package sidev.app.course.dicoding.expert_moviecatalogue1.core.util

import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.entity.ShowEntity
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.response.ShowDetailResponse
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.response.ShowListResponse
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.response.ShowResponse
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.ShowDetail

object DataMapper {
    fun ShowResponse.toModel(): Show = Show(
        id, title, posterPath, releaseDate, rating
    )
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


    fun ShowEntity.toModel(): Show = Show(
        id, title, img, release, rating,
    )
    fun Show.toShowEntity(showType: Int): ShowEntity = ShowEntity(
        id, title, img, release, rating, showType,
    )

    fun List<ShowEntity>.entitiesToShowModelList(): List<Show> = map { it.toModel() }
    fun List<Show>.toShowEntityList(showType: Int): List<ShowEntity> = map { it.toShowEntity(showType) }
}