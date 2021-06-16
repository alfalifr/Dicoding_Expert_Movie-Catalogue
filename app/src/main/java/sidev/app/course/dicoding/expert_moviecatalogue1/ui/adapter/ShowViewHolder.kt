package sidev.app.course.dicoding.expert_moviecatalogue1.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import sidev.app.course.dicoding.expert_moviecatalogue1.R
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Util.getFormattedDate
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Util.imgUrl_300x450
import sidev.app.course.dicoding.expert_moviecatalogue1.databinding.ItemMainListBinding

class ShowViewHolder(
    private val binding: ItemMainListBinding,
    private val onItemClick: OnItemClick?= null
): RecyclerView.ViewHolder(binding.root) {

    fun interface OnItemClick {
        fun onItemClick(pos: Int, data: Show)
    }

    fun bind(data: Show) = binding.apply {
        tvTitle.text = data.title
        tvRelease.apply {
            val dateStr = data.getFormattedDate()
            if(dateStr == null) {
                visibility = View.GONE
            } else {
                visibility = View.VISIBLE
                text = dateStr
            }
        }
        tvPb.text = root.context.getString(R.string.percent, data.rating)
        pb.max = 100
        pb.progress = data.rating.times(10).toInt()
        Glide.with(iv)
            .load(data.imgUrl_300x450())
            .into(iv)

        root.setOnClickListener {
            onItemClick?.onItemClick(adapterPosition, data)
        }
    }
}