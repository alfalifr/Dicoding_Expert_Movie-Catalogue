package sidev.app.course.dicoding.expert_movicatalogue1.core.data.local.room

import androidx.room.*
import sidev.app.course.dicoding.expert_movicatalogue1.core.data.local.entity.ShowEntity
import sidev.app.course.dicoding.expert_movicatalogue1.core.util.Const

@Dao
interface ShowFavDao {
    @Query("DELETE FROM show_fav")
    fun clear()

    @Query("SELECT * FROM show_fav WHERE type = :type")
    fun getShows(type: Int): List<ShowEntity>

    @Query("SELECT * FROM show_fav WHERE type = :type AND id = :id")
    fun getShowById(type: Int, id: Int): ShowEntity?

    fun getFavMovies(): List<ShowEntity> = getShows(Const.ShowType.MOVIE.ordinal)
    fun getFavTv(): List<ShowEntity> = getShows(Const.ShowType.TV.ordinal)

    fun getFavMovieById(id: Int): ShowEntity? = getShowById(Const.ShowType.MOVIE.ordinal, id)
    fun getFavTvById(id: Int): ShowEntity? = getShowById(Const.ShowType.TV.ordinal, id)

    fun isFav(type: Int, id: Int): Boolean = getShowById(type, id) != null
    fun isFav(show: ShowEntity): Boolean = isFav(show.type, show.id)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(show: ShowEntity)

    @Delete
    fun delete(show: ShowEntity): Int

    fun deleteMovieById(id: Int): Int = getFavMovieById(id)?.let { delete(it) } ?: 0
    fun deleteTvById(id: Int): Int = getFavTvById(id)?.let { delete(it) } ?: 0
}