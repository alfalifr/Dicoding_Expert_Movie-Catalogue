package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
import javax.inject.Inject

class DeleteFavShowUseCaseImpl @Inject constructor(private val repo: ShowFavRepo): DeleteFavShowUseCase {
    override fun invoke(type: Const.ShowType, show: Show): Flow<Boolean> = flow {
        val deletedCount = repo.deleteFav(show, type.ordinal)
        emit(deletedCount == 1)
    }
}