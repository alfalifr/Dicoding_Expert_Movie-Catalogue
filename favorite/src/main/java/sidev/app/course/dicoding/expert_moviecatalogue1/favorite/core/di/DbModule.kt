package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.room.ShowFavDao
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.room.ShowFavDb

@Module
object DbModule {
    @Provides
    fun getShowFavDao(c: Context): ShowFavDao = ShowFavDb.getInstance(c).dao()
}