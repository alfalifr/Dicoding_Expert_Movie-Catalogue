package sidev.app.course.dicoding.expert_moviecatalogue1.ui.app

import com.google.android.play.core.splitcompat.SplitCompatApplication
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.CoreComponent
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.DaggerCoreComponent


class App: SplitCompatApplication() {
    val coreComponent: CoreComponent = DaggerCoreComponent.factory().create(this)
}
