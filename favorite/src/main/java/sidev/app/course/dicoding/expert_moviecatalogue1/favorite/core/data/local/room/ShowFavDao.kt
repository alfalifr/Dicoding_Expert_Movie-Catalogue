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
/*
    fun getFavMovies(): Flow<List<ShowEntity>> = getShows(Const.ShowType.MOVIE.ordinal)
    fun getFavTv(): Flow<List<ShowEntity>> = getShows(Const.ShowType.TV.ordinal)

    fun getFavMovieById(id: Int): Flow<ShowEntity?> = getShowById(Const.ShowType.MOVIE.ordinal, id)
    fun getFavTvById(id: Int): Flow<ShowEntity?> = getShowById(Const.ShowType.TV.ordinal, id)
/ *
    fun isFav(type: Int, id: Int): Flow<Boolean> = flow {
        getShowById(type, id)
            .catch { this@flow.emit(false) }
            .collect {
                loge("dao fav isFav() value= $it")
                emit(it != null)
            }
    }
    fun isFav(show: ShowEntity): Flow<Boolean> = isFav(show.type, show.id)
 */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(show: ShowEntity): Long

    @Delete
    suspend fun delete(show: ShowEntity): Int
}