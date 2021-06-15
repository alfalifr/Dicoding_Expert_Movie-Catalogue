package sidev.app.course.dicoding.expert_moviecatalogue1.ui.fragment

import sidev.app.course.dicoding.expert_moviecatalogue1.ui.app.App
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowListViewModel
import javax.inject.Inject

class ShowListFragment: ShowListAbsFragment() {
    @Inject
    override lateinit var vm: ShowListViewModel

    override fun onAfterVmConfigured() {
        (requireActivity().application as App).getLifecycleOwnerComponent(this, type).inject(this)
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