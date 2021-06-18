package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.entity.ShowEntity

@Dao
interface ShowFavDao {
    @Query("DELETE FROM show_fav")
    fun clear()

    @Query("SELECT * FROM show_fav WHERE type = :type")
    fun getShows(type: Int): Flow<List<ShowEntity>>

    @Query("SELECT * FROM show_fav WHERE type = :type AND id = :id")
    fun getShowById(type: Int, id: Int): Flow<ShowEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(show: ShowEntity): Long

    @Delete
    suspend fun delete(show: ShowEntity): Int
}