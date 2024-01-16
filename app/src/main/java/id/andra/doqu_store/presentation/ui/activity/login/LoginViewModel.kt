package id.andra.doqu_store.presentation.ui.activity.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.andra.doqu_store.data.remote.api.AuthApi
import id.andra.doqu_store.data.remote.request.LoginRequest
import id.andra.doqu_store.domain.model.Token
import id.andra.doqu_store.utils.Var
import id.andra.doqu_store.utils.network.createRetrofit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val api: AuthApi = createRetrofit()
    private val _state = MutableStateFlow(LoginState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(Var.SUBSCRIBED_FLOW),
        LoginState()
    )

    private fun handleOnSubmit() = viewModelScope.launch {
        try {
            val response = api.login(
                LoginRequest(
                    username = _state.value.email,
                    password = _state.value.password,
                )
            )
            _state.update { it.copy(token = response.toToken()) }
        } catch (e: Throwable) {
            _state.update { it.copy(isError = true) }
        }
    }

    fun onEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.OnTogglePassword -> _state.update { it.copy(isShowPassword = !_state.value.isShowPassword) }
            is LoginEvent.OnEmailChanged -> _state.update { it.copy(email = event.value) }
            is LoginEvent.OnPasswordChanged -> _state.update { it.copy(password = event.value) }
            LoginEvent.OnSubmit -> handleOnSubmit()
            LoginEvent.ResetToken -> _state.update { it.copy(token = null) }
            LoginEvent.ResetError -> _state.update { it.copy(isError = false) }
        }
    }
}

data class LoginState(
    val isShowPassword: Boolean = false,
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val token: Token? = null,
    val isError: Boolean = false
)

sealed class LoginEvent {
    data class OnEmailChanged(val value: String) : LoginEvent()
    data class OnPasswordChanged(val value: String) : LoginEvent()
    data object OnTogglePassword : LoginEvent()
    data object OnSubmit : LoginEvent()
    data object ResetToken : LoginEvent()
    data object ResetError : LoginEvent()
}