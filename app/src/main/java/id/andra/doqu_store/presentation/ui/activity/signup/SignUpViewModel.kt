package id.andra.doqu_store.presentation.ui.activity.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.andra.doqu_store.utils.Var
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SignUpViewModel : ViewModel() {
    private val _state = MutableStateFlow(SignUpstate())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(Var.SUBSCRIBED_FLOW),
        SignUpstate()
    )

    fun onEvent(event: SignUpEvent) {
        when (event) {
            SignUpEvent.OnTogglePassword -> _state.update { it.copy(isShowPassword = !_state.value.isShowPassword) }
            SignUpEvent.OnTogglePasswordConfirm -> _state.update { it.copy(isShowPassword = !_state.value.isShowPasswordConfirm) }
        }
    }
}

data class SignUpstate(
    val isShowPassword: Boolean = false,
    val isShowPasswordConfirm: Boolean = false
)

sealed class SignUpEvent {
    data object OnTogglePassword : SignUpEvent()
    data object OnTogglePasswordConfirm : SignUpEvent()
}