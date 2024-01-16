package id.andra.doqu_store.presentation.ui.activity.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.andra.doqu_store.data.remote.api.AuthApi
import id.andra.doqu_store.data.remote.request.RefreshTokenRequest
import id.andra.doqu_store.domain.model.Token
import id.andra.doqu_store.utils.Var
import id.andra.doqu_store.utils.network.createRetrofit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val api: AuthApi = createRetrofit()
    private val _state = MutableStateFlow(SplashState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(Var.SUBSCRIBED_FLOW),
        SplashState()
    )

    private fun handleOnLoad(accessToken: String, refreshToken: String) = viewModelScope.launch {
        if (accessToken.isEmpty() && refreshToken.isEmpty()) {
            _state.update { it.copy(isNavigateToLogin = true) }
            return@launch
        }
        try {
            val response = api.refreshToken(
                RefreshTokenRequest(
                    refreshToken = refreshToken
                )
            )
            _state.update { it.copy(token = response.toToken()) }
        } catch (e: Throwable) {
            _state.update { it.copy(isNavigateToLogin = true) }
        }
    }

    fun onEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.OnLoad -> handleOnLoad(event.accessToken, event.refreshToken)
            SplashEvent.ResetIsNavigateToLogin -> _state.update { it.copy(isNavigateToLogin = false) }
            SplashEvent.ResetToken -> _state.update { it.copy(token = null) }
        }
    }
}

data class SplashState(
    val isNavigateToLogin: Boolean = false,
    val token: Token? = null
)

sealed class SplashEvent {
    data class OnLoad(val accessToken: String, val refreshToken: String) : SplashEvent()
    data object ResetIsNavigateToLogin : SplashEvent()
    data object ResetToken : SplashEvent()
}