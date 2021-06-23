package sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test

import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.UnitTestingUtil.randomSubList
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.response.GenreResponse
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.response.ShowDetailResponse
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.response.ShowListResponse
import sidev.app.course.dicoding.expert_moviecatalogue1.core.data.remote.response.ShowResponse
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.DataMapper.toDetailModel
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.DataMapper.toModel

object DummyData {
    val genresAll = listOf(
        GenreResponse("0", "Rock"),
        GenreResponse("1", "Stone"),
        GenreResponse("2", "Metal"),
        GenreResponse("3", "Diamond"),
        GenreResponse("4", "Horror"),
        GenreResponse("5", "Comedy"),
    )

    val tvResponses = List(5) {
        ShowResponse(it, "TV Title $it", "link $it", "10-10-10 $it", 3.0 + it)
    }
    val movieResponses = List(7) {
        ShowResponse(it, "Movie Title $it", "link $it ++", "11-12-14 - $it", 5.5 +it)
    }

    val tvListResponse = ShowListResponse(1, tvResponses.size, 1, tvResponses)
    val movieListResponse = ShowListResponse(1, movieResponses.size, 1, movieResponses)

    val tvSearchListResponse = tvResponses.randomSubList().run {
        ShowListResponse(1, size, 1, this)
    }
    val movieSearchListResponse = movieResponses.randomSubList().run {
        ShowListResponse(1, size, 1, this)
    }

    val tvDetailResponses = tvResponses.mapIndexed { i, data ->
        data.run {
            ShowDetailResponse(
                id, title, posterPath, releaseDate, rating,
                null, "backLink $i", "overview $i", "No tagline $i",
                genresAll.randomSubList(),
            )
        }
    }
    val movieDetailResponses = movieResponses.mapIndexed { i, data ->
        data.run {
            ShowDetailResponse(
                id, title, posterPath, releaseDate, rating,
                null, "backLink $i -", "overview $i +", "No tagline $i ++",
                genresAll.randomSubList(),
            )
        }
    }

    val tvDomains = tvResponses.map { it.toModel() }
    val movieDomains = movieResponses.map { it.toModel() }
    val showDomains = tvDomains + movieDomains

    val tvDetailDomains = tvDetailResponses.map { it.toDetailModel() }
    val movieDetailDomains = movieDetailResponses.map { it.toDetailModel() }
}