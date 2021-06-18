package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.di

import androidx.lifecycle.ViewModelStoreOwner
import dagger.BindsInstance
import dagger.Subcomponent
import sidev.app.course.dicoding.expert_moviecatalogue1.di.LifecycleOwnerScope
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.activity.DetailFavActivity
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.fragment.ShowFavListFragment

@LifecycleOwnerScope
@Subcomponent(modules = [FavViewModelModule::class])
interface FavLifecycleOwnerComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance owner: ViewModelStoreOwner,
        ): FavLifecycleOwnerComponent
    }

    fun inject(act: DetailFavActivity)
    fun inject(frag: ShowFavListFragment)
}