package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.util

import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.entity.ShowEntity

object DataMapper {
    fun ShowEntity.toModel(): Show = Show(
        id, title, img, release, rating,
    )
    fun Show.toShowEntity(showType: Int): ShowEntity = ShowEntity(
        id, title, img, release, rating, showType,
    )

    fun List<ShowEntity>.entitiesToShowModelList(): List<Show> = map { it.toModel() }
    fun List<Show>.toShowEntityList(showType: Int): List<ShowEntity> = map { it.toShowEntity(showType) }
}