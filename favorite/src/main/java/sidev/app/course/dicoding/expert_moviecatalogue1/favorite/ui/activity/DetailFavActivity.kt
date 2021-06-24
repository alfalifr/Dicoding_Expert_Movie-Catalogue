package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.activity

import android.os.Bundle
import android.view.View
import org.jetbrains.anko.imageResource
import sidev.app.course.dicoding.expert_moviecatalogue1.R
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.di.DaggerFavCoreComponent
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel.ShowDetailFavViewModel
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.activity.DetailActivity
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.app.App
import sidev.lib.android.std.tool.util.`fun`.loge
import javax.inject.Inject

class DetailFavActivity: DetailActivity() {

    @Inject
    lateinit var favVm: ShowDetailFavViewModel
    private var isFav = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val coreComponent = (application as App).coreComponent
        DaggerFavCoreComponent.factory().create(coreComponent)
            .favLifecycleOwnerSubComponent()
            .create(this)
            .inject(this)

        super.onCreate(savedInstanceState)

        binding.btnFav.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                loge("isFav btn click = $isFav")
                if(isFav) {
                    favVm.deleteFav(showType, show)
                } else {
                    favVm.insertFav(showType, show)
                }
            }
        }

        favVm.apply {
            onCallNotSuccess { process, code, e ->
                isError = true
                loge("DetailFav error proccess= $process code= $code e= $e", e)
            }
            isFav(showType, show).observe(this@DetailFavActivity) {
                loge("DetailFav isFav observe() it= $it")
                if(it != null){
                    this@DetailFavActivity.isFav = it
                    binding.btnFav.imageResource = if(it) R.drawable.ic_heart_full else R.drawable.ic_heart
                    showLoading(false)
                    showError(isError)
                }
            }
        }
    }
}