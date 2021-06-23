package sidev.app.course.dicoding.expert_moviecatalogue1.favorite

import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.entity.ShowEntity

object FavDummyData {
    val movieFavList = List(3) {
        ShowEntity(
            it, "Movie Title $it", "img movie $it",
            "10-10-10 movie $it", 4.2 + it, Const.ShowType.MOVIE.ordinal
        )
    }
    val tvFavList = List(4) {
        ShowEntity(
            it, "TV Title $it", "img tv $it",
            "11-12-14 tv $it", 5.1 + it, Const.ShowType.TV.ordinal
        )
    }
    val favList = movieFavList + tvFavList
}