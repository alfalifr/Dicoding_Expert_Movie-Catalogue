package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.entity.ShowEntity

@Database(entities = [ShowEntity::class], version = 1)
abstract class ShowFavDb: RoomDatabase() {
    abstract fun dao(): ShowFavDao

    companion object {
        private var instace: ShowFavDb?= null
        private const val DB_NAME = "user_db"

        fun getInstance(ctx: Context): ShowFavDb {
            if(instace == null){
                synchronized(ShowFavDb::class){
                    instace = Room.databaseBuilder(
                        ctx,
                        ShowFavDb::class.java,
                        "$DB_NAME.db"
                    ).build()
                }
            }
            return instace!!
        }

    }
}