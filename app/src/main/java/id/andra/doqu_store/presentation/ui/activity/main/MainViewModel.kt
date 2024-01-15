package id.andra.doqu_store.presentation.ui.activity.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.andra.doqu_store.R
import id.andra.doqu_store.utils.Var
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(Var.SUBSCRIBED_FLOW),
        MainState()
    )

    private fun handleOnNavigationChanged(id: Int) = viewModelScope.launch {
        // delay(300)
        val isShowNavigationBar = when (id) {
            R.id.notificationFragment -> true
            R.id.homeFragment -> true
            R.id.historyFragment -> true
            R.id.announcementFragment -> true
            R.id.profileFragment -> true
            else -> false
        }
        _state.update { state ->
            state.copy(isShowNavigationBar = isShowNavigationBar)
        }
    }

    private fun handleOnNavIdChanged(id: Int) {
        _state.update { state ->
            state.copy(
                currentNavId = id,
                isHomeActive = id == R.id.homeFragment,
                isHistoryActive = id == R.id.historyFragment,
                isNotificationActive = id == R.id.notificationFragment,
                isAnnouncementActive = id == R.id.announcementFragment,
                isProfileActive = id == R.id.profileFragment
            )
        }
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnNavigationChanged -> {
                handleOnNavigationChanged(event.id)
            }

            is MainEvent.OnNavIdChanged -> {
                handleOnNavIdChanged(event.id)
            }
        }
    }

}

data class MainState(
    val isShowNavigationBar: Boolean = true,
    val currentNavId: Int = 0,
    val isHomeActive: Boolean = true,
    val isAnnouncementActive: Boolean = false,
    val isHistoryActive: Boolean = false,
    val isNotificationActive: Boolean = false,
    val isProfileActive: Boolean = false
)

sealed class MainEvent {
    data class OnNavigationChanged(val id: Int) : MainEvent()
    data class OnNavIdChanged(val id: Int) : MainEvent()
}