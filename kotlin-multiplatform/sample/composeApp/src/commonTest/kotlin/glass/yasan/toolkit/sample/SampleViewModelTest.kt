package glass.yasan.toolkit.sample

import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

@OptIn(ExperimentalCoroutinesApi::class)
class SampleViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: SampleViewModel

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = SampleViewModel()
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun givenInitialized_thenCountIsZero() {
        // Given
        val state = viewModel.viewState.value

        // Then
        assertEquals(0, state.count)
    }

    @Test
    fun whenIncrementCalled_thenCountIncreases() = runTest(testDispatcher) {
        // When
        viewModel.onViewEvent(SampleViewModel.Event.Increment)

        // Then
        assertEquals(1, viewModel.viewState.value.count)
    }

    @Test
    fun whenDecrementCalled_thenCountDecreases() = runTest(testDispatcher) {
        // When
        viewModel.onViewEvent(SampleViewModel.Event.Decrement)

        // Then
        assertEquals(-1, viewModel.viewState.value.count)
    }

    @Test
    fun whenMultipleOperationsCalled_thenCountCalculatesCorrectly() = runTest(testDispatcher) {
        // When
        viewModel.onViewEvent(SampleViewModel.Event.Increment)
        viewModel.onViewEvent(SampleViewModel.Event.Increment)
        viewModel.onViewEvent(SampleViewModel.Event.Increment)
        assertEquals(3, viewModel.viewState.value.count)

        viewModel.onViewEvent(SampleViewModel.Event.Decrement)
        assertEquals(2, viewModel.viewState.value.count)

        viewModel.onViewEvent(SampleViewModel.Event.Increment)
        assertEquals(3, viewModel.viewState.value.count)

        viewModel.onViewEvent(SampleViewModel.Event.Decrement)
        viewModel.onViewEvent(SampleViewModel.Event.Decrement)
        viewModel.onViewEvent(SampleViewModel.Event.Decrement)

        // Then
        assertEquals(0, viewModel.viewState.value.count)
    }
}
