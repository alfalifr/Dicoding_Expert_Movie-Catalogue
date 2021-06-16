package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.entity.ShowEntity

@Database(entities = [ShowEntity::class], version = 1)
abstract class ShowFavDb: RoomDatabase() {
    abstract fun dao(): ShowFavDao
}