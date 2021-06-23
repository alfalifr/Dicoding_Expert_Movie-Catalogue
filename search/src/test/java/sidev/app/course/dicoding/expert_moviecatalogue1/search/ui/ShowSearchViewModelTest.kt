package sidev.app.course.dicoding.expert_moviecatalogue1.search.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.runners.MethodSorters
import org.mockito.Mockito.*
import sidev.app.course.dicoding.expert_moviecatalogue1.core.domain.model.Show
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.Const
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.DummyData
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.UnitTestingUtil.randomSubList
import sidev.app.course.dicoding.expert_moviecatalogue1.core.util.test.UnitTestingUtil.waitForValue
import sidev.app.course.dicoding.expert_moviecatalogue1.search.core.domain.usecase.SearchShowUseCase
import sidev.app.course.dicoding.expert_moviecatalogue1.search.ui.viewmodel.ShowSearchViewModel
import sidev.lib.`val`.SuppressLiteral

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ShowSearchViewModelTest {
    companion object {
        private val useCase: SearchShowUseCase by lazy { mock(SearchShowUseCase::class.java) }
        private val vm: ShowSearchViewModel by lazy { ShowSearchViewModel(null, useCase) }

        private val randomShowList = DummyData.showDomains.randomSubList()
        private val randomShowType = Const.ShowType.values().random()
        private val randomKeyword = "ay"

        @Suppress(SuppressLiteral.UNCHECKED_CAST)
        private val mockObserver: Observer<List<Show>> = mock(Observer::class.java) as Observer<List<Show>>

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


    @Test
    fun _1_invalidKeyword(): Unit = runBlocking {
        vm.searchList.observeForever(mockObserver)

        val keyword = "*._"
        vm.searchShow(randomShowType, keyword)
        val data = vm.searchValidState.first()

        assertEquals(false, data)
        verifyNoInteractions(mockObserver)
    }

    @Test
    fun _2_validKeyword(): Unit = runBlocking {
        vm.searchShow(randomShowType, randomKeyword, 0)
        val isValid = vm.searchValidState.first() //So the validator flow can run
        assertEquals(true, isValid)

        val data = vm.searchList.waitForValue()

        verify(mockObserver).onChanged(data)
        verify(useCase).invoke(randomShowType, randomKeyword)
        assertEquals(randomShowList, data)
    }
}