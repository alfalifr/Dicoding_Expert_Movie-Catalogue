package sidev.app.course.dicoding.expert_moviecatalogue1.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import sidev.app.course.dicoding.expert_moviecatalogue1.R
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Util.backdropImgUrl_533x300
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Util.getFormattedDate
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Util.imgUrl_300x450
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Util.getDurationString
import sidev.app.course.dicoding.expert_moviecatalogue1.databinding.PageShowDetailBinding
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.app.App
import sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel.ShowDetailViewModel
import javax.inject.Inject

open class DetailActivity: AppCompatActivity() {
    protected lateinit var binding: PageShowDetailBinding
        private set
    protected lateinit var show: Show
        private set
    protected lateinit var showType: Const.ShowType
        private set
    @Inject
    lateinit var vm: ShowDetailViewModel

    protected var isError = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PageShowDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTitle(R.string.show_detail)

        intent.apply {
            show = intent.getSerializableExtra(Const.KEY_SHOW) as Show
            showType = intent.getSerializableExtra(Const.KEY_TYPE) as Const.ShowType
        }

        (application as App).coreComponent
            .lifecycleOwnerSubComponent()
            .create(this, showType)
            .inject(this)

        binding.apply {
            tvTitle.text= show.title
            tvRelease.apply {
                val dateStr = show.getFormattedDate()
                if(dateStr == null) {
                    visibility = View.GONE
                } else {
                    visibility = View.VISIBLE
                    text = dateStr
                }
            }
            tvPb.text = getString(R.string.percent, show.rating)
            pbRating.max = 100
            pbRating.progress = show.rating.times(10).toInt()
            btnFav.visibility = View.GONE
            Glide.with(this@DetailActivity)
                .load(show.imgUrl_300x450())
                .apply(RequestOptions().apply {
                    error(R.drawable.ic_img_error)
                })
                .into(ivPoster)
        }

        vm.apply {
            onPreAsyncTask {
                showError(false)
                showLoading()
            }
            onCallNotSuccess { process, code, e ->
                showLoading(false)
                showError(true, code, e)
                isError = true
            }
            getShowDetail(show.id).observe(this@DetailActivity){
                if(it != null){
                    binding.apply {
                        tvDuration.text = getDurationString(it) ?: run {
                            tvDuration.visibility = View.GONE
                            "null"
                        }
                        tvGenres.text = it.genres.joinToString()
                        tvTagline.text = it.tagline
                        tvOverviewContent.text =
                            if(it.overview.isNotBlank()) it.overview
                            else getString(R.string.no_data)

                        Glide.with(this@DetailActivity)
                            .load(it.backdropImgUrl_533x300())
                            .into(ivBg)
                    }
                }
                showError(false)
                showLoading(false)
                isError = false
            }
        }
    }

    protected fun showLoading(show: Boolean = true)= binding.apply {
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

    protected fun showError(show: Boolean = true, code: Int = -1, e: Throwable? = null) = binding.apply {
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
}