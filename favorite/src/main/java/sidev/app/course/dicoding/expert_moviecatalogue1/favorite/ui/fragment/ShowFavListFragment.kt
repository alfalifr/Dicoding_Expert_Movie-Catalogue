package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.fragment

import android.os.Bundle
import android.view.View
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.di.DaggerFavCoreComponent
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.activity.DetailFavActivity
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.fragment.ShowListAbsFragment
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel.ShowFavListViewModel
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.app.App
import sidev.lib.android.std.tool.util.`fun`.startAct
import javax.inject.Inject

class ShowFavListFragment: ShowListAbsFragment() {
    @Inject
    public override lateinit var vm: ShowFavListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val coreComponent = (requireActivity().application as App).coreComponent
        DaggerFavCoreComponent.factory().create(coreComponent)
            .favLifecycleOwnerSubComponent()
            .create(this, type)
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adp.setOnItemClick { pos, data ->
            startAct<DetailFavActivity>(
                Const.KEY_SHOW to data,
                Const.KEY_TYPE to type,
            )
        }
    }

    override fun onAfterVmConfigured() {
        vm.apply {
            getFavList().observe(this@ShowFavListFragment) {
                adp.dataList = it
                showLoading(false)
                showNoData(it == null || it.isEmpty())
                //AppConfig.decUiAsync()
            }
        }
    }
}