package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.CoreModule
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.ModuleScope
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.room.ShowFavDao
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.room.ShowFavDb
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.util.FavConst

@Module(includes = [CoreModule::class])
class DbModule {
    companion object {
        private var instace: ShowFavDb?= null

        @ModuleScope
        @Provides
        fun getInstance(c: Context): ShowFavDb {
            if(instace == null){
                synchronized(ShowFavDb::class){
                    instace = Room.databaseBuilder(
                        c,
                        ShowFavDb::class.java,
                        "${FavConst.DB_NAME}.db"
                    ).build()
                }
            }
            return instace!!
        }
    }

    @ModuleScope
    @Provides
    fun getShowFavDao(db: ShowFavDb): ShowFavDao = db.dao()
}