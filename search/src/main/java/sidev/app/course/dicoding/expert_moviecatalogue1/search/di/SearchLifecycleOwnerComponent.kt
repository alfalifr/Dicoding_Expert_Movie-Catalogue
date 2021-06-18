package sidev.app.course.dicoding.expert_moviecatalogue1.search.di

import androidx.lifecycle.ViewModelStoreOwner
import dagger.BindsInstance
import dagger.Subcomponent
import sidev.app.course.dicoding.expert_moviecatalogue1.di.LifecycleOwnerScope
import sidev.app.course.dicoding.expert_moviecatalogue1.search.ui.activity.SearchActivity

@LifecycleOwnerScope
@Subcomponent(modules = [SearchViewModelModule::class])
interface SearchLifecycleOwnerComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance owner: ViewModelStoreOwner): SearchLifecycleOwnerComponent
    }

    fun inject(act: SearchActivity)
}