package sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const

interface GetPopularShowListUseCase {
    operator fun invoke(type: Const.ShowType, refreshMillis: Long? = null): Flow<List<Show>>
}