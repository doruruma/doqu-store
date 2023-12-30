package id.andra.doqu_store.presentation.ui.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.andra.doqu_store.R
import id.andra.doqu_store.utils.Var
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(Var.SUBSCRIBED_FLOW),
        MainState()
    )

    private fun handleOnNavigationChanged(id: Int) {
        val isShowBottomBar = when (id) {
            R.id.notificationFragment -> true
            R.id.homeFragment -> true
            R.id.historyFragment -> true
            R.id.announcementFragment -> true
            else -> false
        }
        _state.update { state ->
            state.copy(isShowBottomBar = isShowBottomBar)
        }
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnNavigationChanged -> {
                handleOnNavigationChanged(event.id)
            }
        }
    }

}

data class MainState(
    val isShowBottomBar: Boolean = true
)

sealed class MainEvent {
    data class OnNavigationChanged(val id: Int) : MainEvent()
}