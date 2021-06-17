package sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.repo.ShowRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import javax.inject.Inject

class SearchShowUseCaseImpl @Inject constructor(private val repo: ShowRepo): SearchShowUseCase {
    override fun invoke(type: Const.ShowType, keyword: String): Flow<List<Show>> = when(type) {
        Const.ShowType.TV -> repo.searchTv(keyword)
        Const.ShowType.MOVIE -> repo.searchMovie(keyword)
    }
}