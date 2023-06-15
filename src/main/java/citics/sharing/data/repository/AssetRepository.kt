package citics.sharing.data.repository

import citics.sharing.data.model.request.EstimationAssetRequest
import citics.sharing.data.model.request.TSSSMyAssetRequest
import citics.sharing.data.model.request.legal.LegalCreateOrUpdateRequest
import citics.sharing.data.model.request.legal.LegalDeleteDocumentRequest
import citics.sharing.data.model.request.legal.LegalDeleteTagRequest
import citics.sharing.data.model.request.legal.LegalUpdateNameDocumentRequest
import citics.sharing.data.model.response.*
import citics.sharing.data.model.response.base.BaseResponse
import citics.sharing.data.model.response.tham_dinh.AssetLabelRequest
import citics.sharing.data.model.response.tham_dinh.AssetLevelRequest
import citics.sharing.data.model.response.tham_dinh.Properties
import citics.sharing.data.model.response.tudinhgia.DeleteTSSSTSCTRequest
import citics.sharing.service.customadapter.NetworkResponse
import com.citics.valuation.data.model.response.ErrorResponse
import citics.sharing.data.repository.base.BaseRepository
import citics.sharing.di.ApiMain
import citics.sharing.di.ApiSearch
import citics.sharing.service.APIService
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by ChinhQT on 24/10/2022.
 */

@Singleton
class AssetRepository @Inject constructor(
    @ApiSearch private val apiServiceSearch: APIService,
    @ApiMain private val apiServiceAgent: APIService,
) : BaseRepository() {

    suspend fun getLandDetailByLatLng(
        lat: Double, lng: Double
    ): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceSearch.getLandDetailByLatLng(
            "",
            lat, lng
        )
    }
//
//    suspend fun getLandDetailsByVN2000City(
//        request: RequestBody,
//    ): NetworkResponse<LandDetailResponse, ErrorResponse> {
//        var cp = ""
//        if (StaticDataUtils.isTruCPoint) {
//            cp = "cp"
//        }
//        return apiServiceSearch.getLandDetailsByVN2000City(
//            cp,
//            request
//        )
//    }
//
//    suspend fun getLandDetailBySoToSoThua(
//        ma_thanh_pho: String, ma_quan: String, ward: String, soto: String, sothua: String
//    ): NetworkResponse<LandDetailResponse, ErrorResponse> {
//        var cp = ""
//        if (StaticDataUtils.isTruCPoint) {
//            cp = "cp"
//        }
//        return apiServiceSearch.getLandDetailBySoToSoThua(
//            cp,
//            ma_thanh_pho, ma_quan, ward, soto, sothua
//        )
//    }

    suspend fun getLandSuggestion(
        term: String, size: Int = 20
    ): NetworkResponse<LandSuggestionResponse, ErrorResponse> {
        return apiServiceSearch.getLandSuggestion(term, size)
    }

    suspend fun estimationAsset(
        asset_id: String,
        loai_tai_san: String,
        properties: Properties,
        using_purpose: List<DetailUsingPurpose>?
    ): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        val estimationAssetRequest =
            EstimationAssetRequest(asset_id, loai_tai_san, properties, using_purpose)
        return apiServiceSearch.estimationAsset(
            estimationAssetRequest
        )
    }


    suspend fun getCanHoSuggestion(
        term: String, size: Int = 20
    ): NetworkResponse<LandSuggestionResponse, ErrorResponse> {
        return apiServiceSearch.getCanHoSuggestion(term, size)
    }

    suspend fun getProjectDetail(id: String): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceSearch.getProjectDetail(id)
    }

    suspend fun getProjectDetail2(id: String): NetworkResponse<ProjectDetailResponse, ErrorResponse> {
        return apiServiceSearch.getProjectDetail2(id)
    }

    suspend fun getCanHoAdvanced(
        query: String, page: Int = 20, shapes: String
    ): NetworkResponse<CanHoAdvancedResponse, ErrorResponse> {
        return apiServiceSearch.getCanHoAdvanced(
            query, page, shapes
        )
    }

//    fun getCanHoAdvanced(
//        term: String, shapes: String = ""
//    ): Flow<PagingData<CanHoAdvancedResponse.Content>> {
//        return Pager(config = PagingConfig(
//            enablePlaceholders = false, initialLoadSize = PAGE_SIZE, pageSize = PAGE_SIZE
//        ), pagingSourceFactory = {
//            AssetPagingSource(
//                apiServiceSearch, term, shapes, headers
//            )
//        }).flow
//    }

    suspend fun getCanHoSuggestion(
        project_id: String, term: String
    ): NetworkResponse<CanHoSuggestionResponse, ErrorResponse> {
        return apiServiceSearch.getCanHoSuggestion(
            project_id, term
        )
    }

    suspend fun getAllCanHoByProjectId(
        project_id: String?
    ): NetworkResponse<BaseResponse<MutableList<String>, Any>, ErrorResponse> {
        return apiServiceSearch.getAllCanHoByProjectId(
            project_id ?: ""
        )
    }

    suspend fun getCanHoFilterAdvance(
        filters: HashMap<String, Any?>?
    ): NetworkResponse<CanHoFilterAdvanceResponse, ErrorResponse> {
        return apiServiceSearch.getCanHoFilterAdvance(filters)
    }

    suspend fun getOptionsSuggestion(
        project_id: String
    ): NetworkResponse<OptionsSuggestionResponse, ErrorResponse> {
        return apiServiceSearch.getOptionsSuggestion(
            project_id
        )
    }

    suspend fun getCiticsPriceChart(
        asset_id: String
    ): NetworkResponse<CiticsPriceChartResponse, ErrorResponse> {
        return apiServiceSearch.getCiticsPriceChart(
            asset_id
        )
    }

    suspend fun getApartmentAsset(
        hasMap: HashMap<String,Any?>
    ): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceSearch.getApartmentAsset(
            hasMap
        )
    }

    suspend fun selfAssetRequest(
        body: RequestBody
    ): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceSearch.selfAssetRequest(
            body
        )
    }

    suspend fun selfAssetComparedAdd(body: RequestBody): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceSearch.selfAssetComparedAdd(
            body
        )
    }

    suspend fun addTSSSTSCT(body: RequestBody): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.addTSSSTSCT(
            body
        )
    }

    suspend fun mineUpsert(body: RequestBody): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.mineUpsert(
            body
        )
    }

    suspend fun getLichSuThamDinhGia(id: String): NetworkResponse<BaseResponse<List<ListLichSuThamDinhGiaDTO>, Any>, ErrorResponse> {
        return apiServiceSearch.getLichSuThamDinhGia(id)
    }

    suspend fun updateComparedAsset(body: RequestBody): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceSearch.updateComparedAsset(
            body
        )
    }

    suspend fun updateComparedAssetTSCT(body: RequestBody): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.updateComparedAssetTSCT(
            body
        )
    }

    suspend fun getSelfAssetCalculate(): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceSearch.getSelfAssetCalculate()
    }

    suspend fun getSelfAssetCalculateTSCT(my_asset_id: String): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.getSelfAssetCalculateTSCT(
            my_asset_id
        )
    }

    suspend fun deleteComparedAsset(asset_id: String): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceSearch.deleteComparedAsset(asset_id)
    }

    suspend fun deleteComparedAssetTSCT(asset_id: DeleteTSSSTSCTRequest): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.deleteComparedAssetTSCT(asset_id)
    }

    suspend fun getDonGiaUBND(ma_quan: String): NetworkResponse<BaseResponse<DonGiaUBNDResponse, Any>, ErrorResponse> {
        return apiServiceSearch.getDonGiaUBND(ma_quan)
    }

    //region My Asset

    suspend fun getMineFilterMap(
        filters: HashMap<String, Any?>?
    ): NetworkResponse<MineFilterMapResponse, ErrorResponse> {
        return apiServiceAgent.getMineFilterMap(
            filters
        )
    }

//    fun getMineFilterCard(
//        filters: HashMap<String, Any?>?
//    ): Flow<PagingData<MineFilterCardResponse.Content>> {
//        return Pager(config = PagingConfig(
//            enablePlaceholders = false, initialLoadSize = PAGE_SIZE, pageSize = PAGE_SIZE
//        ), pagingSourceFactory = {
//            MyAssetPagingSource(
//                apiServiceAgent, filters, headers
//            )
//        }).flow
//    }

    suspend fun getMineFilterSummary(): NetworkResponse<MineFilterSummaryResponse, ErrorResponse> {
        return apiServiceAgent.getMineFilterSummary(

        )
    }

    suspend fun getAssetVote(
        asset_id: String, isChange: Boolean?
    ): NetworkResponse<AssetVoteResponse, ErrorResponse> {
        return apiServiceAgent.getAssetVote(
            asset_id, isChange
        )
    }

    suspend fun getMineDeleteByID(myAssetID: String): NetworkResponse<AssetDeleteResponse, ErrorResponse> {
        return apiServiceAgent.getMineDeleteByID(
            myAssetID
        )
    }

    suspend fun getMineById(my_asset_id: String): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.getMineById(
            my_asset_id
        )
    }

    suspend fun getCValueOfAssetById(my_asset_id: String): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.getCValueOfAsset(
            my_asset_id
        )
    }

    suspend fun addTSSSFromMyAsset(request: TSSSMyAssetRequest): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.addTSSSFromMyAsset(
            request
        )
    }

    suspend fun updateTSSSFromMyAsset(request: TSSSMyAssetRequest): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.updateTSSSFromMyAsset(
            request
        )
    }

    suspend fun updatePhapLy(pl: LegalCreateOrUpdateRequest): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceSearch.updatePhapLy(pl)
    }

    suspend fun updatePhapLyTSCTSCT(pl: LegalCreateOrUpdateRequest): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.updatePhapLyTSCTSCT(pl)
    }

    suspend fun deletePhapLy(pl: LegalDeleteTagRequest): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceSearch.deletePhapLy(pl)
    }

    suspend fun deletePhapLyTagTSCTSCT(pl: LegalDeleteTagRequest): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.deletePhapLyTSCTSCT(pl)
    }

    suspend fun deleteImagePhapLy(pl: LegalDeleteDocumentRequest): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceSearch.deleteImagesPhapLy(pl)
    }

    suspend fun deleteImagesPhapLyTSCTSCT(pl: LegalDeleteDocumentRequest): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.deleteImagesPhapLyTSCTSCT(pl)
    }

    suspend fun getStaticLegalProperty(type: Int): NetworkResponse<BaseResponse<LegalPropertyResponse, Any>, ErrorResponse> {
        return apiServiceSearch.legalProperty(type)
    }

    suspend fun legalUpdateNameDocument(rq: LegalUpdateNameDocumentRequest): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceSearch.legalDocumentNameUpdate(rq)
    }

    suspend fun legalDocumentNameUpdateTSCTSCT(rq: LegalUpdateNameDocumentRequest): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.legalDocumentNameUpdateTSCTSCT(rq)
    }

    suspend fun genHTML(
        my_asset_id: String
    ): NetworkResponse<HTMLResponse, ErrorResponse> {
        return apiServiceAgent.genHTML(
            my_asset_id
        )
    }

    suspend fun genPDF(
        my_asset_id: String
    ): NetworkResponse<PDFResponse, ErrorResponse> {
        return apiServiceAgent.genPDF(
            my_asset_id
        )
    }

    suspend fun labelUpdate(
        reqest: AssetLabelRequest?
    ): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.labelUpdate(
            reqest
        )
    }

    suspend fun levelUpgrade(
        reqest: AssetLevelRequest?
    ): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.levelUpgrade(
            reqest
        )
    }

    //endregion

    suspend fun getRanhThuaBoundaries(
        maxLng: Double, minLng: Double, maxLat: Double, minLat: Double, tiny: Boolean
    ): NetworkResponse<RanhThuaResponse, ErrorResponse> {
        return apiServiceSearch.getRanhThuaBoundaries(
            maxLng, minLng, maxLat, minLat, tiny
        )
    }
}