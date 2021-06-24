package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity


/**
 * For both TV Show and Movie because they have same structure.
 */
@Entity(tableName = "show_fav", primaryKeys = ["id", "type"])
data class ShowEntity(
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "img")
    val img: String?,
    @ColumnInfo(name = "release")
    val release: String?,
    @ColumnInfo(name = "rating")
    val rating: Double,
    @ColumnInfo(name = "type")
    val type: Int, //The value is in range of Const.ShowType.ordinal.
)