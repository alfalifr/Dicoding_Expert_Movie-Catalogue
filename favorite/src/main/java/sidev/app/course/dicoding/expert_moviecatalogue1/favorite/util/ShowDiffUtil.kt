package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.util

import androidx.recyclerview.widget.DiffUtil
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show

object ShowDiffUtil: DiffUtil.ItemCallback<Show>() {
    override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean = oldItem == newItem
}