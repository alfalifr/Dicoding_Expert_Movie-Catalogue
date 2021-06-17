package sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import javax.inject.Inject

class GetPopularShowListUseCaseImpl @Inject constructor(private val repo: ShowRepo): GetPopularShowListUseCase {
    override fun invoke(type: Const.ShowType, refreshMillis: Long?): Flow<List<Show>> = when(type) {
        Const.ShowType.TV -> repo.getPopularTvList(refreshMillis)
        Const.ShowType.MOVIE -> repo.getPopularMovieList(refreshMillis)
    }
}