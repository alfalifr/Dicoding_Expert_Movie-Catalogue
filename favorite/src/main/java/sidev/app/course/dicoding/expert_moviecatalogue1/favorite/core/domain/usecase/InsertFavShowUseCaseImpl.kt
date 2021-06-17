package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
import javax.inject.Inject

class InsertFavShowUseCaseImpl @Inject constructor(private val repo: ShowFavRepo): InsertFavShowUseCase {
    override fun invoke(type: Const.ShowType, show: Show): Flow<Boolean> = flow {
        emit(repo.insertFav(show, type.ordinal))
    }
}