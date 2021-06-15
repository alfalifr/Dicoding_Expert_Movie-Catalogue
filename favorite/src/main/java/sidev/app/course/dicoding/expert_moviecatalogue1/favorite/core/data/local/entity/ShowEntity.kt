package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.entity

import androidx.room.Entity


/**
 * For both TV Show and Movie because they have same structure.
 */
@Entity(tableName = "show_fav", primaryKeys = ["id", "type"])
data class ShowEntity(
    val id: Int,
    val title: String,
    val img: String,
    val release: String,
    val rating: Double,
    val type: Int, //The value is in range of Const.ShowType.ordinal.
)