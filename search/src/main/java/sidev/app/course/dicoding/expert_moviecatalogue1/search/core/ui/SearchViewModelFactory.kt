package sidev.app.course.dicoding.expert_moviecatalogue1.search.core.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.ModuleScope
import sidev.app.course.dicoding.expert_moviecatalogue1.search.core.domain.usecase.SearchShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.search.ui.viewmodel.ShowSearchViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

@ModuleScope
class SearchViewModelFactory @Inject constructor(
    private val app: Application?,
    private val useCase: SearchShowUseCase,
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when{
        modelClass.isAssignableFrom(ShowSearchViewModel::class.java) -> ShowSearchViewModel(app, useCase)
        else -> throw IllegalArgumentException("No such ViewModel class ($modelClass) for SearchViewModelFactory")
    } as T
}