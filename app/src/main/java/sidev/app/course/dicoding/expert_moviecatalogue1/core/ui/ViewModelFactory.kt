package sidev.app.course.dicoding.expert_moviecatalogue1.core.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetPopularShowListUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetShowDetailUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.LifecycleOwnerScope
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowDetailViewModel
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowListViewModel
import java.lang.IllegalStateException
import javax.inject.Inject

@LifecycleOwnerScope
class ViewModelFactory @Inject constructor(
    private val app: Application?,
    private val getPopularShowListUseCase: GetPopularShowListUseCase,
    private val getShowDetailUseCase: GetShowDetailUseCase,
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(ShowListViewModel::class.java) -> ShowListViewModel(app, getPopularShowListUseCase)
        modelClass.isAssignableFrom(ShowDetailViewModel::class.java) -> ShowDetailViewModel(app, getShowDetailUseCase)
        else -> throw IllegalStateException("No such ViewModel class ($modelClass) for ViewModelFactory")
    } as T
}