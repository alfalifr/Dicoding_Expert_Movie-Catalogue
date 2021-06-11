package sidev.app.course.dicoding.expert_movicatalogue1.core.data.local.entity

import androidx.room.Entity
import sidev.app.course.dicoding.expert_movicatalogue1.core.util.Const
import java.io.Serializable


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