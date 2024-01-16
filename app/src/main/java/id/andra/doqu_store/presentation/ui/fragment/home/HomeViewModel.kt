package id.andra.doqu_store.presentation.ui.fragment.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.andra.doqu_store.data.remote.api.ProductApi
import id.andra.doqu_store.domain.model.Product
import id.andra.doqu_store.utils.Var
import id.andra.doqu_store.utils.network.createRetrofit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val api: ProductApi = createRetrofit()
    private val _state = MutableStateFlow(HomeState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(Var.SUBSCRIBED_FLOW),
        HomeState()
    )

    private fun handleOnLoad(token: String) = viewModelScope.launch {
        getProducts(token).join()
    }

    private fun getProducts(token: String) = viewModelScope.launch {
        try {
            val response = api.getProducts(token)
            _state.update { state -> state.copy(products = response.data.map { it.toProduct() }) }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.OnLoad -> handleOnLoad(event.token)
        }
    }

}

data class HomeState(
    val products: List<Product> = listOf()
)

sealed class HomeEvent {
    data class OnLoad(val token: String) : HomeEvent()
}