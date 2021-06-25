package sidev.app.course.dicoding.expert_moviecatalogue1.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import org.jetbrains.anko.support.v4.startActivity
import sidev.app.course.dicoding.expert_moviecatalogue1.R
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.databinding.PageShowListBinding
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.activity.DetailActivity
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.adapter.ShowAdp
import sidev.app.course.dicoding.expert_moviecatalogue1.core.ui.AsyncVm

abstract class ShowListAbsFragment: Fragment() {
    private lateinit var binding: PageShowListBinding

    protected lateinit var adp: ShowAdp
        private set
    protected abstract val vm: AsyncVm
    protected var type: Const.ShowType = Const.ShowType.TV
        private set

    protected abstract fun onAfterVmConfigured()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            type = getSerializable(Const.KEY_TYPE) as Const.ShowType
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PageShowListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adp = ShowAdp().apply {
            setOnItemClick { _, data ->
                val sm = SplitInstallManagerFactory.create(requireContext())

                Log.e("ShowListAbsFragment","FavList sm.installedModules = ${sm.installedModules}")

                //Check whether favorite module is installed
                if(Const.MODULE_FAV in sm.installedModules) {
                    startActivity(
                        Intent(requireContext(), Class.forName(Const.ACT_FAV_DETAIL)).apply {
                            putExtra(Const.KEY_SHOW, data)
                            putExtra(Const.KEY_TYPE, this@ShowListAbsFragment.type)
                        }
                    )
                } else {
                    startActivity<DetailActivity>(
                        Const.KEY_SHOW to data,
                        Const.KEY_TYPE to type,
                    )
                }
            }
        }
        binding.apply {
            rv.apply {
                adapter = adp
                layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }

        vm.apply {
            onPreAsyncTask {
                Log.e("ShowListAbsFragment", "onPreAsyncTask() AppConfig.incUiAsync()")
                showNoData(false)
                showLoading()
            }
            onCallNotSuccess { _, code, e ->
                showLoading(false)
                showDataError(true, code, e)
            }
            onAfterVmConfigured()
        }
    }

    protected fun showNoData(show: Boolean = true) {
        showDataAnomaly(show)
        binding.tvNoData.text = getString(R.string.no_data)
    }

    @Suppress("SameParameterValue")
    protected  fun showDataError(show: Boolean = true, code: Int = -1, e: Throwable? = null) {
        showDataAnomaly(show)
        val eClass = if(e != null) e::class.java.simpleName else "null"
        binding.tvNoData.text = getString(R.string.error_data, "$eClass ($code)", e?.message ?: "null")
    }

    private fun showDataAnomaly(show: Boolean = true) = binding.apply {
        if(show){
            tvNoData.visibility = View.VISIBLE
            rv.visibility = View.GONE
        } else {
            tvNoData.visibility = View.GONE
            rv.visibility = View.VISIBLE
        }
    }

    protected  fun showLoading(show: Boolean = true) = binding.apply {
        if(show){
            pbLoading.visibility = View.VISIBLE
            rv.visibility = View.GONE
        } else {
            pbLoading.visibility = View.GONE
            rv.visibility = View.VISIBLE
        }
    }
}