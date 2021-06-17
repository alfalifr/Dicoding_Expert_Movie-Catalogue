package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const

interface IsShowFavUseCase {
    operator fun invoke(type: Const.ShowType, show: Show): Flow<Boolean>
}