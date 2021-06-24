@file:Suppress("TestFunctionName")

package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.flow
import org.junit.*
import org.junit.Assert.assertEquals
import org.mockito.Mockito.*
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.UnitTestingUtil.waitForValue
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.FavDummyData
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.DeleteFavShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.InsertFavShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.IsShowFavUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.util.DataMapper.toModel
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel.ShowDetailFavViewModel
import sidev.lib.`val`.SuppressLiteral

class ShowDetailFavViewModelTest {
    companion object {
        private val isShowFavUseCase: IsShowFavUseCase by lazy { mock(IsShowFavUseCase::class.java) }
        private val insertFavShowUseCase: InsertFavShowUseCase by lazy { mock(InsertFavShowUseCase::class.java) }
        private val deleteFavShowUseCase: DeleteFavShowUseCase by lazy { mock(DeleteFavShowUseCase::class.java) }

        private val vm: ShowDetailFavViewModel by lazy {
            ShowDetailFavViewModel(null, isShowFavUseCase, insertFavShowUseCase, deleteFavShowUseCase)
        }

        private val randomFavEntity = FavDummyData.favList.random()
        private val randomFav = randomFavEntity.toModel()
        private val randomShowType = Const.ShowType.values()[randomFavEntity.type]

        private lateinit var liveData: LiveData<Boolean>

        @JvmStatic
        @BeforeClass
        fun init() {
            `when`(isShowFavUseCase(randomShowType, randomFav)).thenReturn(
                flow { emit(true) }
            )
            `when`(insertFavShowUseCase(randomShowType, randomFav)).thenReturn(
                flow { emit(true) }
            )
            `when`(deleteFavShowUseCase(randomShowType, randomFav)).thenReturn(
                flow { emit(true) }
            )
            liveData = vm.isFav
        }
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mockObserver: Observer<Boolean>

    @Before
    fun beforeEach() {
        @Suppress(SuppressLiteral.UNCHECKED_CAST)
        mockObserver = mock(androidx.lifecycle.Observer::class.java) as Observer<Boolean>
        liveData.observeForever(mockObserver)
    }

    @Test
    fun isFav() {
        vm.isFav(randomShowType, randomFav)
        val data = liveData.waitForValue(delay = 500)

        verify(mockObserver).onChanged(data)
        verify(isShowFavUseCase).invoke(randomShowType, randomFav)
        assertEquals(true, data)
    }

    @Test
    fun insertFav() {
        vm.insertFav(randomShowType, randomFav)
        val data = liveData.waitForValue(delay = 500)

        verify(mockObserver).onChanged(data)
        verify(insertFavShowUseCase).invoke(randomShowType, randomFav)
        assertEquals(true, data)
    }

    @Test
    fun deleteFav() {
        vm.deleteFav(randomShowType, randomFav)

        val data = liveData.waitForValue(delay = 500)

        verify(mockObserver).onChanged(data)
        verify(deleteFavShowUseCase).invoke(randomShowType, randomFav)
        assertEquals(false, data)
    }
}