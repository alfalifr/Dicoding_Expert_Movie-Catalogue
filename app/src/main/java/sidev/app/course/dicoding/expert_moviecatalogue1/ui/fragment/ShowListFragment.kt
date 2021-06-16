package sidev.app.course.dicoding.expert_moviecatalogue1.ui.fragment

import android.os.Bundle
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.app.App
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowListViewModel
import javax.inject.Inject

class ShowListFragment: ShowListAbsFragment() {
    @Inject
    public override lateinit var vm: ShowListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as App)
            .coreComponent
            .lifecycleOwnerSubComponent()
            .create(this, type)
            .inject(this)
    }

    override fun onAfterVmConfigured() {
        vm.apply {
            getShowPopularList().observe(this@ShowListFragment) {
                adp.dataList = it
                showLoading(false)
                showNoData(it == null || it.isEmpty())
                //AppConfig.decUiAsync()
            }
        }
    }
}