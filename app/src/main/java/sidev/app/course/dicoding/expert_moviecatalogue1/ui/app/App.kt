package sidev.app.course.dicoding.expert_moviecatalogue1.ui.app

import android.app.Application
import androidx.lifecycle.ViewModelStoreOwner
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.CoreComponent
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.di.DaggerLifecycleOwnerComponent
import sidev.app.course.dicoding.expert_moviecatalogue1.di.LifecycleOwnerComponent

class App: Application() {
    val coreComponent: CoreComponent = DaggerLifecycleOwnerComponent.factory().create()
    fun getLifecycleOwnerComponent(
        owner: ViewModelStoreOwner,
        type: Const.ShowType,
    ): LifecycleOwnerComponent = coreComponent.
}