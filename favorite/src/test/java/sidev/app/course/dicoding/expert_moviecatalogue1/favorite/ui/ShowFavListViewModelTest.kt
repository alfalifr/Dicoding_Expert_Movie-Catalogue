package sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.UnitTestingUtil.randomSubList
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.UnitTestingUtil.waitForValue
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.FavDummyData
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.domain.usecase.GetFavShowListUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.core.util.DataMapper.toModel
import sidev.app.course.dicoding.expert_moviecatalogue1.favorite.ui.viewmodel.ShowFavListViewModel
import sidev.lib.`val`.SuppressLiteral

class ShowFavListViewModelTest {
    companion object {
        private val useCase: GetFavShowListUseCase by lazy { mock(GetFavShowListUseCase::class.java) }
        private val vm: ShowFavListViewModel by lazy { ShowFavListViewModel(null, useCase) }

        private val randomFavList = FavDummyData.favList.randomSubList().map { it.toModel() }
        private val randomShowType = Const.ShowType.values().random()

        @JvmStatic
        @BeforeClass
        fun init() {
            `when`(useCase(randomShowType)).thenReturn(
                flow { emit(randomFavList) }
            )
        }
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mockObserver: Observer<List<Show>>

    @Before
    fun before() {
        @Suppress(SuppressLiteral.UNCHECKED_CAST)
        mockObserver = mock(Observer::class.java) as Observer<List<Show>>
    }

    @Test
    fun getFavList() {
        val liveData = vm.getFavList(randomShowType)
        liveData.observeForever(mockObserver)
        val data = liveData.waitForValue()

        verify(mockObserver).onChanged(data)
        assertEquals(randomFavList, data)
    }
}