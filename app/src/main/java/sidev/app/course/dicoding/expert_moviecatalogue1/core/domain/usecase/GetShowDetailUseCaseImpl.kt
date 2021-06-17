package sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.ShowDetail
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import javax.inject.Inject

class GetShowDetailUseCaseImpl @Inject constructor(private val repo: ShowRepo): GetShowDetailUseCase {
    override fun invoke(type: Const.ShowType, showId: Int): Flow<ShowDetail> = when(type) {
        Const.ShowType.TV -> repo.getTvDetail(showId)
        Const.ShowType.MOVIE -> repo.getMovieDetail(showId)
    }
}