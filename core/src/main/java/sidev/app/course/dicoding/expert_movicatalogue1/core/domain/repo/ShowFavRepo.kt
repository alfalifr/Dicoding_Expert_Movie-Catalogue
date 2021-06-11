package sidev.app.course.dicoding.expert_movicatalogue1.core.domain.repo

import sidev.app.course.dicoding.expert_movicatalogue1.core.domain.model.Show

interface ShowFavRepo {
    fun getPopularMovieList(): List<Show>
    fun getPopularTvList(): List<Show>
    fun isShowFav(type: Int, id: Int): Boolean
    fun insertFav(show: Show, type: Int)
    fun deleteFav(show: Show, type: Int): Int
    fun getFavMovie(id: Int): Show?
    fun getFavTv(id: Int): Show?
}