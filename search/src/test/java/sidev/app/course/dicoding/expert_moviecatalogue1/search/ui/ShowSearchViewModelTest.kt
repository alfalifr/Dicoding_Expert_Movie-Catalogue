@file:Suppress("TestFunctionName")

package sidev.app.course.dicoding.expert_moviecatalogue1.search.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.assertEquals
import org.mockito.Mockito.*
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.DummyData
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.UnitTestingUtil.randomSubList
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.UnitTestingUtil.waitForValue
import sidev.app.course.dicoding.expert_moviecatalogue1.search.core.domain.usecase.SearchShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.search.ui.viewmodel.ShowSearchViewModel

class ShowSearchViewModelTest {
    companion object {
        private val useCase: SearchShowUseCase by lazy { mock(SearchShowUseCase::class.java) }


        private val randomShowList = DummyData.showDomains.randomSubList()
        private val randomShowType = Const.ShowType.values().random()
        private const val randomKeyword = "ay"

        @JvmStatic
        @BeforeClass
        fun init() {
            `when`(useCase(randomShowType, randomKeyword)).thenReturn(
                flow { emit(randomShowList) }
            )
        }
    }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var mockObserver: Observer<List<Show>>
    private lateinit var vm: ShowSearchViewModel

    @Before
    fun beforeEach() {
        vm = ShowSearchViewModel(null, useCase)
        @Suppress("UNCHECKED_CAST")
        mockObserver = mock(Observer::class.java) as Observer<List<Show>>
        vm.searchList.observeForever(mockObserver)
    }

    @Test
    fun invalidKeyword(): Unit = runBlocking {
        val keyword = "*._"
        vm.searchShow(randomShowType, keyword)
        val data = vm.searchValidState.first()

        assertEquals(false, data)
        verifyNoInteractions(mockObserver)
    }

    @Test
    fun validKeyword(): Unit = runBlocking {
        vm.searchShow(randomShowType, randomKeyword, 0)
        val isValid = vm.searchValidState.first() //So the validator flow can run
        assertEquals(true, isValid)

        val data = vm.searchList.waitForValue()

        verify(mockObserver).onChanged(data)
        verify(useCase).invoke(randomShowType, randomKeyword)
        assertEquals(randomShowList, data)
    }
}