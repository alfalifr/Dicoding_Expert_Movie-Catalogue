package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.di

import androidx.lifecycle.ViewModelStoreOwner
import dagger.BindsInstance
import dagger.Component
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.di.LifecycleOwnerComponent
import sidev.app.course.dicoding.expert_moviecatalogue1.di.LifecycleOwnerScope
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.di.FavCoreComponent
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.activity.DetailFavActivity
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.fragment.ShowFavListFragment

@LifecycleOwnerScope
interface FavLifecycleOwnerComponent {
    @Component.Factory
    interface Factory {
        fun create(
            coreComponent: FavCoreComponent,
            @BindsInstance owner: ViewModelStoreOwner,
            @BindsInstance type: Const.ShowType,
        ): LifecycleOwnerComponent
    }

    fun inject(act: DetailFavActivity)
    fun inject(frag: ShowFavListFragment)
}