package citics.sharing.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import citics.sharing.activity.base.BaseViewModel
import citics.sharing.data.model.others.ImageModel
import citics.sharing.data.model.response.Document
import citics.sharing.data.model.response.UserProfileResponse
import citics.sharing.data.repository.Resource
import citics.sharing.service.customadapter.NetworkResponse
import citics.sharing.utils.CLIENT_CODE_NETWORK_ERROR
import citics.sharing.utils.CLIENT_CODE_UNKNOWN_ERROR
import com.citics.valuation.data.model.response.ErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by ChinhQT on 22/02/2023.
 */
@HiltViewModel
class UploaderSVM @Inject constructor() : BaseViewModel() {

    // Don't change to MutableStateFlow, because the collect function don't receive when the values are changed
    private var _imageList: MutableLiveData<HashMap<String, ImageModel>> =
        MutableLiveData(hashMapOf())
    val imageList: LiveData<HashMap<String, ImageModel>> = _imageList

    private val _uploaderAgencyResponse: MutableStateFlow<Resource<MutableList<Document>>> =
        MutableStateFlow(Resource.Loading())
    val uploaderAgencyResponse: StateFlow<Resource<MutableList<Document>>> get() = _uploaderAgencyResponse

    private val _uploaderImagesResponse: MutableStateFlow<Resource<MutableList<Document>>> =
        MutableStateFlow(Resource.Loading())
    val uploaderImagesResponse: StateFlow<Resource<MutableList<Document>>> = _uploaderImagesResponse

    private val _uploaderAvatarResponse: MutableStateFlow<Resource<UserProfileResponse.Avatar>> =
        MutableStateFlow(Resource.Loading())
    val uploaderAvatarResponse: StateFlow<Resource<UserProfileResponse.Avatar>> get() = _uploaderAvatarResponse

    fun changeImages(imageModel: HashMap<String, ImageModel>) {
        _imageList.value = imageModel
    }

    fun addImage(key: String, imageModel: ImageModel) {
        _imageList.value?.put(key, imageModel)
    }

    fun removeItem(imageModel: ImageModel) {
        Timber.d("removeItem")
        _imageList.value?.remove(imageModel.clientID)
        _imageList.value = _imageList.value
    }

    fun clear() {
        _imageList.value?.clear()
        _imageList.value = _imageList.value
    }

    fun uploadAgencyPhoto(multipartList: MutableList<MultipartBody.Part>) {
        Timber.e("uploadAgencyPhoto")

        val uploaderResponseList: MutableList<Document> = mutableListOf()
        viewModelScope.launch(Dispatchers.IO) {
            multipartList.map {
                async {
                    generalRepository.agencyPhotoUpload(it)
                }
            }.map {
                val response = it.await()
                response.handleResponse().let { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                        }
                        is Resource.Failure -> {
                            _uploaderAgencyResponse.value = Resource.Failure(dataFail = ErrorResponse(
                                message = resource.error?.message ?: "", code = resource.error?.code
                            ))
                        }
                        is Resource.Success -> {
                            resource.data?.let { response ->
                                uploaderResponseList.addAll(
                                    response.data?.toMutableList() ?: mutableListOf()
                                )

                                if (multipartList.size == uploaderResponseList.size) {
                                    _uploaderAgencyResponse.value =
                                        Resource.Success(uploaderResponseList)
                                }
                            }
                        }
                        is Resource.None->{

                        }
                    }
                }
            }
        }
    }

    fun avatarUpload(multipartList: MultipartBody.Part) {
        Timber.e("avatarUpload")

        viewModelScope.launch(Dispatchers.IO) {

            when (val response = generalRepository.avatarUpload(multipartList)) {
                is NetworkResponse.Success -> {
                    Timber.d("NetworkResponse.Success")
                    response.body.data?.let {
                        _uploaderAvatarResponse.value = Resource.Success(it)
                    } ?: kotlin.run {
                        _uploaderAvatarResponse.value = Resource.Failure(dataFail = ErrorResponse(
                            message = "NetworkError", code = CLIENT_CODE_NETWORK_ERROR
                        ))
                    }
                }
                is NetworkResponse.ApiError -> {
                    Timber.d("NetworkResponse.ApiError")
                    _uploaderAvatarResponse.value = Resource.Failure(dataFail = ErrorResponse(
                        message = response.body.message, code = response.body.code
                    ))
                }
                is NetworkResponse.NetworkError -> {
                    Timber.d("NetworkResponse.NetworkError")
                    _uploaderAvatarResponse.value = Resource.Failure(dataFail = ErrorResponse(
                        message = "NetworkError", code = CLIENT_CODE_NETWORK_ERROR
                    ))
                }
                else -> {
                    Timber.d("UnknownError")
                    _uploaderAvatarResponse.value = Resource.Failure(dataFail = ErrorResponse(
                        message = "UnknownError", code = CLIENT_CODE_UNKNOWN_ERROR
                    ))
                }
            }
        }
    }

    fun disableUploaderAgencyResponse(isClearList: Boolean = true) {
        _uploaderAgencyResponse.value = Resource.Loading()
        if (isClearList) {
            _imageList.value?.clear()
        }
    }

    fun disableUploaderImages(isClearList: Boolean = true) {
        _uploaderImagesResponse.value = Resource.Loading()
        if (isClearList) {
            _imageList.value?.clear()
        }
    }

    fun uploaderImages(multipartList: MutableList<MultipartBody.Part>) {
        Timber.e("uploaderImages")

        val uploaderResponseList: MutableList<Document> = mutableListOf()
        viewModelScope.launch(Dispatchers.IO) {
            multipartList.map {
                async {
                    generalRepository.hoSoFileUpload(it)
                }
            }.map {
                val response = it.await()
                response.handleResponse().let { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                        }
                        is Resource.Failure -> {
                            _uploaderImagesResponse.value = Resource.Failure(dataFail = ErrorResponse(
                                message = resource.error?.message ?: "", code = resource.error?.code
                            ))
                        }
                        is Resource.Success -> {
                            resource.data?.let { response ->
                                uploaderResponseList.addAll(
                                    response.data?.toMutableList() ?: mutableListOf()
                                )
                                if (multipartList.size == uploaderResponseList.size) {
                                    _uploaderImagesResponse.value =
                                        Resource.Success(uploaderResponseList)
                                }
                            }
                        }
                        is Resource.None->{

                        }
                    }
                }
            }
        }
    }

    fun uploaderNoImages() {
        viewModelScope.launch(Dispatchers.IO) {
            _uploaderImagesResponse.value = Resource.Success(mutableListOf())
        }
    }

    fun uploaderLegalImages(multipartList: MutableList<MultipartBody.Part>) {
        Timber.e("uploaderLegalImages")

        val uploaderResponseList: MutableList<Document> = mutableListOf()
        viewModelScope.launch(Dispatchers.IO) {
            multipartList.map {
                async {
                    generalRepository.legalPhotoUpload(it)
                }
            }.map {
                val response = it.await()
                response.handleResponse().let { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                        }
                        is Resource.Failure -> {
                            _uploaderImagesResponse.value = Resource.Failure(dataFail = ErrorResponse(
                                message = resource.error?.message ?: "", code = resource.error?.code
                            ))
                        }
                        is Resource.Success -> {
                            resource.data?.let { response ->
                                uploaderResponseList.addAll(
                                    response.data?.toMutableList() ?: mutableListOf()
                                )
                                if (multipartList.size == uploaderResponseList.size) {
                                    _uploaderImagesResponse.value =
                                        Resource.Success(uploaderResponseList)
                                }
                            } ?: kotlin.run {
                                _uploaderImagesResponse.value = Resource.Failure(dataFail = ErrorResponse(
                                    message = resource.error?.message ?: "", code = resource.error?.code
                                ))
                            }
                        }
                        is Resource.None->{

                        }
                    }
                }
            }
        }
    }
}