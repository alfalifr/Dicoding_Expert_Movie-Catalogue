package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
import javax.inject.Inject

class GetFavShowListUseCaseImpl @Inject constructor(private val repo: ShowFavRepo): GetFavShowListUseCase {
    override fun invoke(type: Const.ShowType): Flow<List<Show>> = repo.getFavShowList(type.ordinal)
}