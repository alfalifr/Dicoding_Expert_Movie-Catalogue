package sidev.app.course.dicoding.expert_moviecatalogue1.di

import androidx.lifecycle.ViewModelStoreOwner
import dagger.BindsInstance
import dagger.Component
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.CoreComponent
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.LifecycleOwnerScope
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.activity.DetailActivity
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.fragment.ShowListFragment

@LifecycleOwnerScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [ViewModelModule::class],
)
interface LifecycleOwnerComponent {
    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: CoreComponent,
            @BindsInstance owner: ViewModelStoreOwner,
        ): LifecycleOwnerComponent
    }

    fun inject(act: DetailActivity)
    fun inject(frag: ShowListFragment)
}