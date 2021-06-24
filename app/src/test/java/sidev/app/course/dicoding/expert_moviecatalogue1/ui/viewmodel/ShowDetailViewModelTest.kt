package sidev.app.course.dicoding.expert_moviecatalogue1.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.assertEquals
import org.mockito.Mockito.*
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.ShowDetail
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.usecase.GetShowDetailUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.DummyData
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.UnitTestingUtil.waitForValue
import sidev.lib.`val`.SuppressLiteral

class ShowDetailViewModelTest {
    companion object {
        private val useCase: GetShowDetailUseCase by lazy { mock(GetShowDetailUseCase::class.java) }

        private val randomMovieDetail = DummyData.movieDetailDomains.random()
        private val randomTvDetail = DummyData.tvDetailDomains.random()

        @JvmStatic
        @BeforeClass
        fun init(): Unit = runBlocking {
            `when`(useCase(Const.ShowType.MOVIE, randomMovieDetail.show.id)).thenReturn(
                flow { emit(randomMovieDetail) }
            )
            `when`(useCase(Const.ShowType.TV, randomTvDetail.show.id)).thenReturn(
                flow { emit(randomTvDetail) }
            )
        }
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var vm: ShowDetailViewModel
    private lateinit var mockObserver: Observer<ShowDetail>
    private lateinit var liveData: LiveData<ShowDetail>
    private lateinit var actualList: ShowDetail

    @Before
    fun beforeEach() {
        @Suppress(SuppressLiteral.UNCHECKED_CAST)
        mockObserver = mock(Observer::class.java) as Observer<ShowDetail>
        vm = ShowDetailViewModel(null, useCase) //So the value of the LiveData is reset on each test case.
    }

    @Test
    fun getPopularMovieList() {
        liveData = vm.getShowDetail(Const.ShowType.MOVIE, randomMovieDetail.show.id)
        actualList = randomMovieDetail
    }

    @Test
    fun getPopularTvList() {
        liveData = vm.getShowDetail(Const.ShowType.TV, randomTvDetail.show.id)
        actualList = randomTvDetail
    }

    @After
    fun afterEach() {
        liveData.observeForever(mockObserver)
        val data = liveData.waitForValue()

        verify(mockObserver).onChanged(data)
        assertEquals(actualList, data)
    }
}