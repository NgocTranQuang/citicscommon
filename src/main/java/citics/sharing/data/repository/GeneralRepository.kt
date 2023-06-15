package citics.sharing.data.repository

import citics.sharing.data.model.response.AreaResponse
import citics.sharing.data.model.response.*
import citics.sharing.data.model.response.base.BaseResponse
import citics.sharing.service.customadapter.NetworkResponse
import com.citics.valuation.data.model.response.ErrorResponse
import citics.sharing.data.repository.base.BaseRepository
import citics.sharing.di.ApiMain
import citics.sharing.di.ApiSearch
import citics.sharing.di.ApiUploader
import citics.sharing.service.APIService
import citics.sharing.utils.LoaiTaiSan
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ChinhQT on 12/10/2022.
 */
@Singleton
class GeneralRepository @Inject constructor(
    @ApiSearch private val apiServiceSearch: APIService,
    @ApiUploader private val apiServiceForUploader: APIService,
    @ApiMain private val apiServiceAgent: APIService,
) : BaseRepository() {

    suspend fun getTemplate(): NetworkResponse<TemplateResponse, ErrorResponse> {
        return apiServiceSearch.getTemplate()
    }

    suspend fun avatarUpload(file: MultipartBody.Part): NetworkResponse<BaseResponse<UserProfileResponse.Avatar, Any>, ErrorResponse> {
        return apiServiceForUploader.avatarUpload(file)
    }

    suspend fun agencyPhotoUpload(file: MultipartBody.Part): NetworkResponse<BaseResponse<MutableList<Document>, Any>, ErrorResponse> {
        return apiServiceForUploader.agencyPhotoUpload(file)
    }

    suspend fun getDataAvailable(): NetworkResponse<DataAvailableResponse, ErrorResponse> {
        return apiServiceSearch.getDataAvailable()
    }

    suspend fun getAreas(area: String = ""): NetworkResponse<AreaResponse, ErrorResponse> {
        return apiServiceSearch.getAreas(area)
    }

    suspend fun getAreasSuggestion(
        term: String, limit: Int = 10
    ): NetworkResponse<AreasSuggestionResponse, ErrorResponse> {
        return apiServiceSearch.getAreasSuggestion(term, limit)
    }

    suspend fun getStaticNhaDatInfo(
    ): NetworkResponse<StaticNhaDatResponse, ErrorResponse> {
        return apiServiceSearch.getStaticNhaDatInfo(LoaiTaiSan.NHA_DAT.type.toString())
    }

    suspend fun getStaticCanHoInfo(
    ): NetworkResponse<StaticCanHoResponse, ErrorResponse> {
        return apiServiceSearch.getStaticCanHoInfo(LoaiTaiSan.CAN_HO.type.toString())
    }

    suspend fun getLandPurposes(): NetworkResponse<BaseResponse<List<DetailUsingPurpose>, Any>, ErrorResponse> {
        return apiServiceSearch.getLandPurposes()
    }

    suspend fun legalPhotoUpload(file: MultipartBody.Part): NetworkResponse<BaseResponse<MutableList<Document>, Any>, ErrorResponse> {
        return apiServiceForUploader.legalPhotoUpload(file)
    }

    suspend fun assetPhotoUpload(file: MultipartBody.Part): NetworkResponse<BaseResponse<MutableList<Document>, Any>, ErrorResponse> {
        return apiServiceForUploader.assetPhotoUpload(file)
    }

    suspend fun hoSoFileUpload(file: MultipartBody.Part): NetworkResponse<BaseResponse<List<Document>, Any>, ErrorResponse> {
        return apiServiceForUploader.notePhotoUpload(file)
    }

    suspend fun getStaticLegalProperty(type: Int): NetworkResponse<BaseResponse<LegalPropertyResponse, Any>, ErrorResponse> {
        return apiServiceSearch.legalProperty(type)
    }
}