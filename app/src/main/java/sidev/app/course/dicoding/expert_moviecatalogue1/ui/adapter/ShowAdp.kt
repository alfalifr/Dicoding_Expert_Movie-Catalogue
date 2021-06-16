package sidev.app.course.dicoding.expert_moviecatalogue1.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.ShowAdpDiffUtilCallback
import sidev.app.course.dicoding.expert_moviecatalogue1.databinding.ItemMainListBinding


class ShowAdp: RecyclerView.Adapter<ShowViewHolder>(), ShowViewHolder.OnItemClick {

    var dataList: List<Show>?= null
        set(v) {
            val diffCallback = ShowAdpDiffUtilCallback(field ?: emptyList(), v ?: emptyList())
            DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(this)
            field = v
        }

    private var onItemClick: ShowViewHolder.OnItemClick?= null
    fun setOnItemClick(l: ShowViewHolder.OnItemClick?) {
        onItemClick = l
    }

    override fun getItemCount(): Int = dataList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val binding = ItemMainListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShowViewHolder(binding, this)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        //when the `dataList` is null, then this method won't be invoked.
        val data = dataList!![position]
        holder.bind(data)
    }

    override fun onItemClick(pos: Int, data: Show) {
        onItemClick?.onItemClick(pos, data)
    }
}