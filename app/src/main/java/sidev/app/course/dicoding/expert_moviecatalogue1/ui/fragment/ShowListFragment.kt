package sidev.app.course.dicoding.expert_moviecatalogue1.ui.fragment

import android.os.Bundle
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.app.App
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowListViewModel
import javax.inject.Inject

class ShowListFragment: ShowListAbsFragment() {
    @Inject
    public override lateinit var vm: ShowListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (requireActivity().application as App)
            .coreComponent
            .lifecycleOwnerSubComponent()
            .create(this)
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onAfterVmConfigured() {
        vm.apply {
            getShowPopularList(type).observe(this@ShowListFragment) {
                adp.dataList = it
                showLoading(false)
                showNoData(it == null || it.isEmpty())
            }
        }
    }
}