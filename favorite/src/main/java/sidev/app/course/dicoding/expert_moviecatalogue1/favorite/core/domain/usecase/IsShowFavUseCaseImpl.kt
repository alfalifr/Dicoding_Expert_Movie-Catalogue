package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
import javax.inject.Inject

class IsShowFavUseCaseImpl @Inject constructor(private val repo: ShowFavRepo): IsShowFavUseCase {
    override fun invoke(type: Const.ShowType, show: Show): Flow<Boolean> = flow {
        repo.getFavShowById(type.ordinal, show.id).collect {
            emit(it != null)
        }
    }
}