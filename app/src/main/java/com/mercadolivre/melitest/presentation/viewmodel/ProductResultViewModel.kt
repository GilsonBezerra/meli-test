package com.mercadolivre.melitest.presentation.viewmodel

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercadolivre.melitest.domain.GetProductUseCase
import com.mercadolivre.melitest.presentation.view.states.ProductUIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class ProductResultViewModel(
    private val getProductUseCase: GetProductUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(ProductUIState())
    val state: State<ProductUIState> = _state

    fun searchProductByParameters(product: String) {
        val param = GetProductUseCase.Params(
            product = product,
        )
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = ProductUIState(isLoading = true)
            try {
                val result = getProductUseCase.execute(param)
                _state.value = result?.let { ProductUIState(product = it) } ?: ProductUIState()
            } catch (e: Exception) {
                _state.value = e.localizedMessage?.let { ProductUIState(error = it) } ?: ProductUIState()
                e.printStackTrace()
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}
