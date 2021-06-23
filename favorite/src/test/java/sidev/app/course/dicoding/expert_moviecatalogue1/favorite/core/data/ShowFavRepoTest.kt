package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mockito.*
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.FavDummyData
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.room.ShowFavDao
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.repo.ShowFavRepoImpl
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.repo.ShowFavRepo
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.util.DataMapper.toModel

class ShowFavRepoTest {
    companion object {
        private val dao: ShowFavDao by lazy { mock(ShowFavDao::class.java) }
        private val repo: ShowFavRepo by lazy { ShowFavRepoImpl(dao) }

        private val randomFav = FavDummyData.favList.random()

        @JvmStatic
        @BeforeClass
        fun init(): Unit = runBlocking {
            `when`(dao.getShows(anyInt())).thenReturn(
                flow { emit(FavDummyData.favList) }
            )
            `when`(dao.getShowById(anyInt(), anyInt())).thenReturn(
                flow { emit(randomFav) }
            )
            `when`(dao.insert(randomFav)).thenReturn(1)
            `when`(dao.delete(randomFav)).thenReturn(1)
        }
    }


    @Test
    fun getFavShowList(): Unit = runBlocking {
        repo.getFavShowList(1).collect { data ->
            verify(dao).getShows(1)
            assertEquals(FavDummyData.favList.map { it.toModel() }, data)
        }
    }

    @Test
    fun getFavShowById(): Unit = runBlocking {
        repo.getFavShowById(1, 2).collect {
            verify(dao).getShowById(1, 2)
            assertEquals(randomFav.toModel(), it)
        }
    }

    @Test
    fun insertFav(): Unit = runBlocking {
        val bool = repo.insertFav(randomFav.toModel(), randomFav.type)
        verify(dao).insert(randomFav)
        assertEquals(true, bool)
    }

    @Test
    fun deleteFav(): Unit = runBlocking {
        val deletedCount = repo.deleteFav(randomFav.toModel(), randomFav.type)
        verify(dao).delete(randomFav)
        assertEquals(1, deletedCount)
    }
}