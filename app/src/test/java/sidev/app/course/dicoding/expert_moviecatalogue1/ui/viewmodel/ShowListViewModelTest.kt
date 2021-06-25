package sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.assertEquals
import org.mockito.Mockito.*
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetPopularShowListUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.DummyData
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.UnitTestingUtil.waitForValue

class ShowListViewModelTest {
    companion object {
        private val useCase: GetPopularShowListUseCase by lazy { mock(GetPopularShowListUseCase::class.java) }

        @JvmStatic
        @BeforeClass
        fun init(): Unit = runBlocking {
            `when`(useCase(Const.ShowType.MOVIE, null)).thenReturn(
                flow { emit(DummyData.movieDomains) }
            )
            `when`(useCase(Const.ShowType.TV, null)).thenReturn(
                flow { emit(DummyData.tvDomains) }
            )
        }
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var vm: ShowListViewModel
    private lateinit var mockObserver: Observer<List<Show>>
    private lateinit var liveData: LiveData<List<Show>>
    private lateinit var actualList: List<Show>

    @Before
    fun beforeEach() {
        @Suppress("UNCHECKED_CAST")
        mockObserver = mock(Observer::class.java) as Observer<List<Show>>
        vm = ShowListViewModel(null, useCase) //So the value of the LiveData is reset on each test case.
    }

    @Test
    fun getPopularMovieList() {
        liveData = vm.getShowPopularList(Const.ShowType.MOVIE, null)
        actualList = DummyData.movieDomains
    }

    @Test
    fun getPopularTvList() {
        liveData = vm.getShowPopularList(Const.ShowType.TV, null)
        actualList = DummyData.tvDomains
    }

    @After
    fun afterEach() {
        liveData.observeForever(mockObserver)
        val data = liveData.waitForValue()

        verify(mockObserver).onChanged(data)
        assertEquals(actualList, data)
    }
}