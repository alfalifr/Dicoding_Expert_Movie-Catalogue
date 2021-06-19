package sidev.app.course.dicoding.expert_moviecatalogue1.search.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import sidev.app.course.dicoding.expert_moviecatalogue1.R
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.search.core.di.DaggerSearchCoreComponent
import sidev.app.course.dicoding.expert_moviecatalogue1.search.databinding.PageSearchBinding
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.activity.DetailActivity
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.adapter.ShowAdp
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.app.App
import sidev.app.course.dicoding.expert_moviecatalogue1.search.ui.viewmodel.ShowSearchViewModel
import sidev.lib.android.std.tool.util.`fun`.loge
import sidev.lib.android.std.tool.util.`fun`.startAct
import javax.inject.Inject

class SearchActivity: AppCompatActivity() {
    private lateinit var binding: PageSearchBinding
    private lateinit var adp: ShowAdp
    @Inject
    lateinit var vm: ShowSearchViewModel
    private lateinit var showType: Const.ShowType

    override fun onCreate(savedInstanceState: Bundle?) {
        val coreComponent = (application as App).coreComponent
        DaggerSearchCoreComponent.factory().create(coreComponent)
            .searchLifecycleOwnerComponent()
            .create(this)
            .inject(this)

        super.onCreate(savedInstanceState)
        binding = PageSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTitle(R.string.search_show)

        adp = ShowAdp().apply {
            setOnItemClick { pos, data ->
                val sm = SplitInstallManagerFactory.create(this@SearchActivity)

                loge("SearchActivity sm.installedModules = ${sm.installedModules}")

                if(Const.MODULE_FAV in sm.installedModules) {
                    startActivity(
                        Intent(this@SearchActivity, Class.forName(Const.ACT_FAV_DETAIL)).apply {
                            putExtra(Const.KEY_SHOW, data)
                            putExtra(Const.KEY_TYPE, showType)
                        }
                    )
                } else {
                    startAct<DetailActivity>(
                        Const.KEY_SHOW to data,
                        Const.KEY_TYPE to showType,
                    )
                }
            }
        }
        binding.apply {
            rv.apply {
                adapter = adp
                layoutManager = GridLayoutManager(this@SearchActivity, 2)
            }
            etSearch.addTextChangedListener {
                if(it != null) {
                    vm.searchShow(showType, it.toString())
                }
            }
            tabs.apply {
                this.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        mapTabPositionToShowType(tab)
                        vm.searchShow(showType, delay = 1)
                    }
                    override fun onTabUnselected(tab: TabLayout.Tab?) {}
                    override fun onTabReselected(tab: TabLayout.Tab?) {
                        mapTabPositionToShowType(tab)
                    }
                })
                selectTab(getTabAt(vm.selectedTabPosition))
                val selectedColor = ContextCompat.getColor(this@SearchActivity, R.color.colorPrimary)
                setSelectedTabIndicatorColor(selectedColor)
                val normalTxtColor = ContextCompat.getColor(this@SearchActivity, R.color.colorText)
                setTabTextColors(normalTxtColor, selectedColor)
            }
        }

        vm.apply {
            onPreAsyncTask {
                showNoData(false)
                showLoading()
            }
            onCallNotSuccess { process, code, e ->
                showLoading(false)
                showNoData(
                    textResId = R.string.error_data,
                    strArgs = arrayOf(
                        "in process = $process e = ${e?.message}",
                        "${e?.cause?.message}, stack = ${e?.stackTrace?.joinToString("\n")}"
                    ),
                )
            }
            searchList.observe(this@SearchActivity) {
                if(it != null) {
                    adp.dataList = it
                    showLoading(false)
                    showNoData(it.isEmpty())
                }
            }
            lifecycleScope.launch {
                searchValidState.collect {
                    binding.etSearch.error = if(it) null else getString(R.string.keyword_is_not_valid)
                }
            }
            showLoading(false)
            showNoData(false)
            showRv(searchList.value?.isNotEmpty() == true)
        }
    }

    private fun showLoading(show: Boolean = true){
        binding.pbLoading.visibility = if(show) View.VISIBLE else View.GONE
        showRv(!show)
    }

    private fun showNoData(
        show: Boolean = true,
        @StringRes textResId: Int = R.string.no_data,
        vararg strArgs: String,
    ){
        binding.tvNoData.visibility = if(show) {
            @SuppressLint("StringFormatInvalid")
            binding.tvNoData.text =
                if(strArgs.isEmpty()) getString(textResId)
                else getString(textResId, *strArgs)
            View.VISIBLE
        } else View.GONE
        showRv(!show)
    }

    private fun showRv(show: Boolean = true) {
        binding.rv.visibility = if(show) View.VISIBLE else View.GONE
    }

    private fun mapTabPositionToShowType(tab: TabLayout.Tab?) {
        showType = when(val pos = tab?.position) {
            0 -> Const.ShowType.TV
            1 -> Const.ShowType.MOVIE
            else -> throw IllegalStateException("No such tab position ($pos)")
        }
        vm.selectedTabPosition = tab.position
    }
}