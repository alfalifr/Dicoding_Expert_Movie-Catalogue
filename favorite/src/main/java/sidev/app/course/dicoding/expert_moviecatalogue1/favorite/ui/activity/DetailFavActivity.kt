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
/*
class DetailFavActivity: AppCompatActivity() {
///*
    private lateinit var binding: PageShowDetailBinding
    private lateinit var show: Show
    private lateinit var showType: Const.ShowType
    @Inject
    private lateinit var vm: ShowDetailViewModel
    //private var isFav = false
    private var isError = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PageShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle(R.string.show_detail)

        intent.apply {
            show = intent.getSerializableExtra(Const.KEY_SHOW) as Show
            showType = intent.getSerializableExtra(Const.KEY_TYPE) as Const.ShowType
        }

        val a = DaggerLifecycleOwnerComponent.factory().create(this, this, showType)
        a.inject(this)

        binding.apply {
            tvTitle.text= show.title
            tvRelease.text= show.getFormattedDate()
            tvPb.text = getString(R.string.percent, show.rating)
            pbRating.max = 100
            pbRating.progress = show.rating.times(10).toInt()
            btnFav.setOnClickListener {
                if(isFav) {
                    vm.deleteFav(show)
                } else {
                    vm.insertFav(show)
                }
            }
            Glide.with(this@DetailFavActivity)
                .load(show.imgUrl_300x450())
                .into(ivPoster)
        }

        val dao = ShowFavDb.getInstance(this).dao()
        vm = ShowDetailViewModel.getInstance(this, application, showRepo,
            sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo(
                dao
            ), showType).apply {
            onPreAsyncTask {
                //AppConfig.incUiAsync()
                showError(false)
                showLoading()
            }
            onCallNotSuccess { code, e ->
                showLoading(false)
                showError(true, code, e)
                //AppConfig.decUiAsync()
                isError = true
            }
            showDetail.observe(this@DetailFavActivity){
                if(it != null){
                    binding.apply {
                        tvDuration.text = getDurationString(it) ?: run {
                            tvDuration.visibility = View.GONE
                            "null"
                        }
                        tvGenres.text = it.genres.joinToString()
                        tvTagline.text = it.tagline
                        tvOverviewContent.text = it.overview
                        Glide.with(this@DetailFavActivity)
                            .load(it.backdropImgUrl_533x300())
                            .into(ivBg)
                    }
                }
                isError = false
                showError(false)
                showLoading(false)
                //AppConfig.decUiAsync()
            }
            isFav.observe(this@DetailFavActivity) {
                if(it != null){
                    this@DetailFavActivity.isFav = it
                    binding.btnFav.imageResource = if(it) R.drawable.ic_heart_full else R.drawable.ic_heart
                    showLoading(false)
                    showError(isError)
                    //AppConfig.decUiAsync()
                }
            }
            multipleJob {
                listOf(
                    downloadShowDetail(show.id),
                    isFav(show.id)
                )
            }
        }
    }

    private fun showLoading(show: Boolean = true)= binding.apply {
        if(show){
            pbLoading.visibility = View.VISIBLE
            tvOverview.visibility = View.GONE
            tvOverviewContent.visibility = View.GONE
        } else {
            pbLoading.visibility = View.GONE
            tvOverview.visibility = View.VISIBLE
            tvOverviewContent.visibility = View.VISIBLE
        }
    }

    private fun showError(show: Boolean = true, code: Int = -1, e: Throwable? = null) = binding.apply {
        if(show){
            tvOverview.visibility= View.GONE
            tvOverviewContent.visibility= View.GONE
            tvError.visibility= View.VISIBLE
            val eClass = if(e != null) e::class.java.simpleName else "null"
            binding.tvError.text = getString(R.string.error_data, "$eClass ($code)", e?.message ?: "null")
        } else {
            tvOverview.visibility= View.VISIBLE
            tvOverviewContent.visibility= View.VISIBLE
            tvError.visibility= View.GONE
        }
    }
// */
}

 */

class DetailFavActivity: DetailActivity() {

    @Inject
    lateinit var favVm: ShowDetailFavViewModel
    private var isFav = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val coreComponent = (application as App).coreComponent
        DaggerFavCoreComponent.factory().create(coreComponent)
            .favLifecycleOwnerSubComponent()
            .create(this, showType)
            .inject(this)

        binding.btnFav.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                loge("isFav btn click = $isFav")
                if(isFav) {
                    favVm.deleteFav(show)
                } else {
                    favVm.insertFav(show)
                }
            }
        }

        favVm.apply {
            onCallNotSuccess { process, code, e ->
                isError = true
                loge("DetailFav error proccess= $process code= $code e= $e", e)
            }
            isFav(show.id).observe(this@DetailFavActivity) {
                loge("DetailFav isFav observe() it= $it")
                if(it != null){
                    isFav = it
                    binding.btnFav.imageResource = if(it) R.drawable.ic_heart_full else R.drawable.ic_heart
                    showLoading(false)
                    showError(isError)
                    //AppConfig.decUiAsync()
                }
            }
        }
    }
}