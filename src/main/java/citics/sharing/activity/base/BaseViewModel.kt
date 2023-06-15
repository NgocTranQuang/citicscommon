package citics.sharing.activity.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import citics.sharing.data.repository.GeneralRepository
import citics.sharing.service.customadapter.NetworkResponse
import citics.sharing.data.datasource.local.PreferenceManager
import com.citics.valuation.data.model.response.ErrorResponse
import citics.sharing.data.repository.Resource
import citics.sharing.utils.CLIENT_CODE_NETWORK_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by ChinhQT on 09/10/2022.
 */
@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var generalRepository: GeneralRepository

    @Inject
    lateinit var preferenceManager: PreferenceManager

    // Show normal error (error api...)
    private val _showErrorStateFull: MutableStateFlow<ErrorResponse?> =
        MutableStateFlow(null)
    val showErrorStateFull: StateFlow<ErrorResponse?> get() = _showErrorStateFull

    // Show het cpoint error (error api...)
    private val _showErrorLoginInNewDevice: MutableStateFlow<ErrorResponse?> =
        MutableStateFlow(null)
    val showErrorLoginInNewDevice: StateFlow<ErrorResponse?> get() = _showErrorLoginInNewDevice


    // Show het cpoint error (error api...)
    private val _showErrorHetCPoint: MutableStateFlow<ErrorResponse?> =
        MutableStateFlow(null)
    val showErrorHetCPoint: StateFlow<ErrorResponse?> get() = _showErrorHetCPoint

    // Show normal error (error api...)
    private val _showErrorDialog: MutableStateFlow<ErrorResponse?> =
        MutableStateFlow(null)
    val showErrorDialog: StateFlow<ErrorResponse?> get() = _showErrorDialog

    private val _showLoading: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val showLoading: StateFlow<Boolean> get() = _showLoading


    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared")
    }

    private fun showErrorDialog(error: ErrorResponse?) {
        viewModelScope.launch(Dispatchers.Main) {
            _showErrorDialog.value = error
        }
    }

    private fun showLoading() {
        viewModelScope.launch(Dispatchers.Main) {
            _showLoading.value = true
        }
    }

    private fun hideLoading() {
        viewModelScope.launch(Dispatchers.Main) {
            _showLoading.value = false
        }
    }




    fun <T : Any> NetworkResponse<T, ErrorResponse>.handleResponse(onSuccess:((T)->Unit)?=null): Resource<T> {
        when (this) {
            is NetworkResponse.Success -> {
                Timber.d("NetworkResponse.Success")
                onSuccess?.invoke(body)
                return Resource.Success(body)
            }

            is NetworkResponse.ApiError -> {
                Timber.d("NetworkResponse.NetworkError ${body.message}")
                return Resource.Failure(dataFail = body)
            }

            is NetworkResponse.NetworkError -> {
                Timber.d("NetworkResponse.NetworkError ${error.message}")
                return Resource.Failure(
                    dataFail = ErrorResponse(
                        message = error.message ?: "",
                        code = CLIENT_CODE_NETWORK_ERROR
                    )
                )
            }

            is NetworkResponse.UnknownError -> {
                Timber.d("NetworkResponse.NetworkError ${error?.message}")

                return Resource.Failure(
                    dataFail = ErrorResponse(
                        message = error?.message ?: "",
                        code = CLIENT_CODE_NETWORK_ERROR
                    )
                )
            }
        }
    }
}