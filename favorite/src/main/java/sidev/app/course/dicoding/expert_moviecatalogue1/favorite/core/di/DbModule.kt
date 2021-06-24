package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.di

import android.content.Context
import androidx.core.content.edit
import androidx.room.Room
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.CoreModule
import sidev.app.course.dicoding.expert_moviecatalogue1.core.di.ModuleScope
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Util
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
                    // Create or get pswd for passPhrase from EncryptedSharedPref.
                    val pref = Util.getSharedPref(c)
                    val pswd = pref.getString(Const.KEY_PASS_PHRASE, null)
                        ?: Util.getRandomString().also {
                            pref.edit { putString(Const.KEY_PASS_PHRASE, it) }
                        }
                    // Use the passPhrase to encrypt DB.
                    val passPhrase = SQLiteDatabase.getBytes(pswd.toCharArray())
                    val factory = SupportFactory(passPhrase)
                    instace = Room.databaseBuilder(
                        c,
                        ShowFavDb::class.java,
                        "${FavConst.DB_NAME}.db"
                    )
                        .openHelperFactory(factory)
                        .build()
                }
            }
            return instace!!
        }
    }

    @ModuleScope
    @Provides
    fun getShowFavDao(db: ShowFavDb): ShowFavDao = db.dao()
}