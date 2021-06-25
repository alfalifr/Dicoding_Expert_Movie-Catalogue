package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.fragment

import android.os.Bundle
import android.view.View
import org.jetbrains.anko.support.v4.startActivity
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.di.DaggerFavCoreComponent
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.activity.DetailFavActivity
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.fragment.ShowListAbsFragment
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel.ShowFavListViewModel
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.app.App
import javax.inject.Inject

class ShowFavListFragment: ShowListAbsFragment() {
    @Inject
    public override lateinit var vm: ShowFavListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val coreComponent = (requireActivity().application as App).coreComponent
        DaggerFavCoreComponent.factory().create(coreComponent)
            .favLifecycleOwnerSubComponent()
            .create(this)
            .inject(this)

        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adp.setOnItemClick { _, data ->
            startActivity<DetailFavActivity>(
                Const.KEY_SHOW to data,
                Const.KEY_TYPE to type,
            )
        }
    }

    override fun onAfterVmConfigured() {
        vm.apply {
            getFavList(type).observe(this@ShowFavListFragment) {
                adp.dataList = it
                showLoading(false)
                showNoData(it == null || it.isEmpty())
            }
        }
    }
}