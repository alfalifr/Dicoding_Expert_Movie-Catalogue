package sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.ShowDetail
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const

interface GetShowDetailUseCase {
    operator fun invoke(type: Const.ShowType, showId: Int): Flow<ShowDetail>
}