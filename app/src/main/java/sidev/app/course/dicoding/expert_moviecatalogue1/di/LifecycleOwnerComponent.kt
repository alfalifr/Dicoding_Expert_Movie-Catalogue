package sidev.app.course.dicoding.expert_moviecatalogue1.di

import androidx.lifecycle.ViewModelStoreOwner
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.CoreComponent
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.activity.DetailActivity
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.activity.SearchActivity
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.fragment.ShowListFragment

@LifecycleOwnerScope
@Subcomponent(modules = [ViewModelModule::class])
interface LifecycleOwnerComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance owner: ViewModelStoreOwner,
            @BindsInstance type: Const.ShowType,
        ): LifecycleOwnerComponent
    }

    fun inject(act: DetailActivity)
    fun inject(frag: ShowListFragment)
    fun inject(act: SearchActivity)
}