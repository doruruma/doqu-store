package id.andra.doqu_store.presentation.ui.activity.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.andra.doqu_store.utils.Var
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(Var.SUBSCRIBED_FLOW),
        LoginState()
    )

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.OnTogglePassword -> _state.update { it.copy(isShowPassword = !_state.value.isShowPassword) }
            is LoginEvent.OnEmailChanged -> _state.update { it.copy(email = event.value) }
            is LoginEvent.OnPasswordChanged -> _state.update { it.copy(password = event.value) }
        }
    }
}

data class LoginState(
    val isShowPassword: Boolean = false,
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
)

sealed class LoginEvent {
    data class OnEmailChanged(val value: String) : LoginEvent()
    data class OnPasswordChanged(val value: String) : LoginEvent()
    data object OnTogglePassword : LoginEvent()
}