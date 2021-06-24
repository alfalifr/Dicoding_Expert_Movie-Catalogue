@file:Suppress("TestFunctionName")

package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.flow
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runners.MethodSorters
import org.mockito.Mockito.*
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.UnitTestingUtil.waitForValue
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.FavDummyData
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.data.local.entity.ShowEntity
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.DeleteFavShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.InsertFavShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.IsShowFavUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.util.DataMapper.toModel
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel.ShowDetailFavViewModel
import sidev.lib.`val`.SuppressLiteral

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ShowDetailFavViewModelTest {
    companion object {
        private var mIsShowFavUseCase: IsShowFavUseCase? = mock(IsShowFavUseCase::class.java)
        private var mInsertFavShowUseCase: InsertFavShowUseCase? = mock(InsertFavShowUseCase::class.java)
        private var mDeleteFavShowUseCase: DeleteFavShowUseCase? = mock(DeleteFavShowUseCase::class.java)

        private val isShowFavUseCase: IsShowFavUseCase get() = mIsShowFavUseCase!!
        private val insertFavShowUseCase: InsertFavShowUseCase get() = mInsertFavShowUseCase!!
        private val deleteFavShowUseCase: DeleteFavShowUseCase get() = mDeleteFavShowUseCase!!

        private val mVm: ShowDetailFavViewModel? by lazy {
            ShowDetailFavViewModel(null, isShowFavUseCase, insertFavShowUseCase, deleteFavShowUseCase)
        }
        private val vm: ShowDetailFavViewModel get() = mVm!!

        private var mRandomFavEntity: ShowEntity? = FavDummyData.favList.random()
        private var mRandomFav: Show? = mRandomFavEntity!!.toModel()
        private var mRandomShowType: Const.ShowType? = Const.ShowType.values()[randomFavEntity!!.type]

        private val randomFavEntity get() = mRandomFavEntity!!
        private val randomFav get() = mRandomFav!!
        private val randomShowType get() = mRandomShowType!!

        @Suppress(SuppressLiteral.UNCHECKED_CAST)
        private var mMockObserver: Observer<Boolean>? = mock(Observer::class.java) as Observer<Boolean>
        private val mockObserver get() = mMockObserver!!

        private var mLiveData: LiveData<Boolean>? = null
        private val liveData get() = mLiveData!!

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
        }

        @JvmStatic
        @AfterClass
        fun clear() {
            mIsShowFavUseCase = null
            mInsertFavShowUseCase = null
            mDeleteFavShowUseCase = null
            mMockObserver = null
        }
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun _1_isFav() {
        mLiveData = vm.isFav(randomShowType, randomFav)
        liveData.observeForever(mockObserver)

        val data = liveData.waitForValue()

        verify(mockObserver).onChanged(data)
        verify(isShowFavUseCase).invoke(randomShowType, randomFav)
        assertEquals(true, data)
    }

    @Test
    fun _2_insertFav() {
        vm.insertFav(randomShowType, randomFav)
        val data = liveData.waitForValue(delay = 500)

        verify(mockObserver, times(2)).onChanged(data)
        verify(insertFavShowUseCase).invoke(randomShowType, randomFav)
        assertEquals(true, data)
    }

    @Test
    fun _3_deleteFav() {
        vm.deleteFav(randomShowType, randomFav)

        val data = liveData.waitForValue(delay = 500)

        verify(mockObserver).onChanged(data)
        verify(deleteFavShowUseCase).invoke(randomShowType, randomFav)
        assertEquals(false, data)
    }
}