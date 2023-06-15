package citics.sharing.data.repository

import citics.sharing.data.model.request.*
import citics.sharing.data.model.request.legal.LegalCreateOrUpdateRequest
import citics.sharing.data.model.request.legal.LegalDeleteDocumentRequest
import citics.sharing.data.model.request.legal.LegalDeleteTagRequest
import citics.sharing.data.model.request.legal.LegalUpdateNameDocumentRequest
import citics.sharing.data.model.request.tham_dinh.*
import citics.sharing.data.model.response.*
import citics.sharing.data.model.response.base.BaseResponse
import citics.sharing.data.model.response.tham_dinh.*
import citics.sharing.service.customadapter.NetworkResponse
import com.citics.valuation.data.model.response.ErrorResponse
import citics.sharing.data.repository.base.BaseRepository
import citics.sharing.di.ApiMain
import citics.sharing.di.ApiSearch
import citics.sharing.di.ApiUploader
import citics.sharing.service.APIService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidationRepository @Inject constructor(
    @ApiMain private val apiServiceAgent: APIService,
    @ApiSearch private val apiServiceSearch: APIService,
    @ApiUploader private val apiServiceForUploader: APIService
) : BaseRepository() {

//    fun getListRecord(
//        filters: HashMap<String, Any?>
//    ): Flow<PagingData<RecordResponse.RecordData>> {
//        return Pager(config = PagingConfig(
//            enablePlaceholders = true, initialLoadSize = PAGE_SIZE, pageSize = PAGE_SIZE
//        ), pagingSourceFactory = {
//            RecordPagingSource(
//                apiServiceAgent, filters, headers
//            )
//        }).flow
//    }

    suspend fun getRecordDetail(
        recordId: String, check_vi_pham_tsss: Boolean = false
    ): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.getRecordDetail(
            recordId, check_vi_pham_tsss
        )
    }


    suspend fun getDashboardPinNumber(): NetworkResponse<BaseResponse<NPin?, Any>, ErrorResponse> {
        return apiServiceAgent.recordPinNumber()
    }

    suspend fun getProgressBar(type: String): NetworkResponse<RecordStatusResponse, ErrorResponse> {
        return apiServiceAgent.getProgressBar(type)
    }

    suspend fun getFilterStatus(type: String): NetworkResponse<BaseResponse<MutableList<StatusBarResponse>, Any>, ErrorResponse> {
        return apiServiceAgent.getFilterBarStatus(type)
    }

    suspend fun getHoSoChoNhan(): NetworkResponse<HoSoChoNhanResponse, ErrorResponse> {
        return apiServiceAgent.getHoSoChoNhan()
    }

    suspend fun xuLyHoSoND(hoSoRequest: HoSoRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.xuLyHoSoND(hoSoRequest)
    }

    suspend fun getListBankOrCN(bankId: String?): NetworkResponse<BaseResponse<MutableList<BarBankResponse>, Any>, ErrorResponse> {
        return apiServiceAgent.getListBankOrCN(bankId)
    }

    suspend fun getMenuBarList(type: String?): NetworkResponse<BaseResponse<MutableList<StatusBarResponse>, Any>, ErrorResponse> {
        return apiServiceAgent.getMenuBarListStatus(type)
    }

    suspend fun getMenuBarBadge(type: String?): NetworkResponse<BaseResponse<MutableList<StatusBarResponse>, Any>, ErrorResponse> {
        return apiServiceAgent.getMenuBarBadge(type)
    }

    suspend fun getRecordHistory(_recordId: String): NetworkResponse<RecordHistoryResponse, ErrorResponse> {
        return apiServiceAgent.getRecordHistory(
            _recordId
        )
    }

    suspend fun getRecordBangTinhHistory(_recordId: String): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.getRecordBangTinhHistory(
            _recordId
        )
    }

    suspend fun getRecordWithSameContract(_id: String): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.getRecordWithSameContract(
            _id
        )
    }

    suspend fun getRecordGuarantee(recordId: String): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.getRecordGuarantee(
            recordId
        )
    }

    suspend fun deleteLegalAssetCompared(deleteLegalAssetCompared: DeleteLegalAssetCompared): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.deleteLegalAssetCompared(
            deleteLegalAssetCompared
        )
    }

    suspend fun getAssetComparedWarehouse(_assetId: String): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.getAssetComparedWarehouse(
            _assetId
        )
    }

    suspend fun getAssetComparedDetail(_assetId: String): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.getAssetComparedDetail(
            _assetId
        )
    }

    suspend fun deleteAssetCompared(deleteAssetCompared: DeleteAssetCompared): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.deleteAssetCompared(
            deleteAssetCompared
        )
    }

    suspend fun haveComparedAssetsForThamDinh(
        recordId: String, filters: HashMap<String, Any?>
    ): NetworkResponse<TDComparedAssetMapResponse, ErrorResponse> {
        return apiServiceAgent.getComparedAssetsFromMap(
            recordId, filters
        )
    }

//    fun getComparedAssetsForThamDinh(
//        recordId: String, filters: HashMap<String, Any?>?
//    ): Flow<PagingData<TDComparedAssetMapResponse.ContentData>> {
//        return Pager(config = PagingConfig(
//            enablePlaceholders = false, initialLoadSize = PAGE_SIZE, pageSize = PAGE_SIZE
//        ), pagingSourceFactory = {
//            TDTSSSPagingSource(
//                apiServiceAgent, recordId, filters, headers
//            )
//        }).flow
//    }

    suspend fun getComparedAssetsForThamDinh(
        recordId: String, filters: HashMap<String, Any?>?
    ): NetworkResponse<TDComparedAssetMapResponse, ErrorResponse> {
        return apiServiceAgent.getComparedAssetsFromMap(
            recordId, filters
        )
    }

    suspend fun getInteractList(
        recordId: String
    ): NetworkResponse<TDHoSoTuongDongResponse, ErrorResponse> {
        return apiServiceAgent.getInteractList(
            recordId
        )
    }

//    fun getInteractListForThamDinh(
//        recordId: String, filters: HashMap<String, Any?>?
//    ): Flow<PagingData<TDHoSoTuongDongResponse.Properties>> {
//        return Pager(config = PagingConfig(
//            enablePlaceholders = false, initialLoadSize = PAGE_SIZE, pageSize = PAGE_SIZE
//        ), pagingSourceFactory = {
//            TDTSSSHSTDPagingSource(
//                apiServiceAgent, recordId, filters, headers
//            )
//        }).flow
//    }

    suspend fun getHistoryList(
        recordId: String
    ): NetworkResponse<TDLichSuResponse, ErrorResponse> {
        return apiServiceAgent.getHistoryList(
            recordId
        )
    }

    suspend fun getRecordPublicList(
        recordId: String
    ): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.getRecordPublicList(
            recordId
        )
    }

    suspend fun addAssetCompared(addAssetComparedRequest: AddAssetComparedRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.addAssetCompared(
            addAssetComparedRequest
        )
    }

    suspend fun updateLegalStatusTSSSTD(
        updateLegalStatusComparedRequest: UpdateLegalStatusComparedRequest
    ): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse> {
        return apiServiceAgent.updateLegalStatusTSSSTD(
            updateLegalStatusComparedRequest
        )
    }

    suspend fun updateLegalStatusTSCTSCT(
        updateLegalStatusComparedRequest: UpdateLegalStatusComparedRequest
    ): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.updateLegalStatusTSCTSCT(
            updateLegalStatusComparedRequest
        )
    }

    suspend fun updateLocationCompared(updateLocationComparedRequest: UpdateLocationComparedRequest): NetworkResponse<BaseResponse<AssetDetailData, RecordResponse.RecordExtra>, ErrorResponse> {
        return apiServiceAgent.updateLocationCompared(
            updateLocationComparedRequest
        )
    }

    suspend fun updateLocationComparedForTSSS(updateLocationComparedRequest: UpdateLocationComparedRequestForTSSS): NetworkResponse<BaseResponse<AssetDetailData, RecordResponse.RecordExtra>, ErrorResponse> {
        return apiServiceAgent.updateLocationComparedForTSSS(
            updateLocationComparedRequest
        )
    }

    suspend fun updateLocationComparedCH(updateLocationComparedCanHoRequest: UpdateLocationComparedCanHoRequest): NetworkResponse<BaseResponse<AssetDetailData, RecordResponse.RecordExtra>, ErrorResponse> {
        return apiServiceAgent.updateLocationComparedCH(
            updateLocationComparedCanHoRequest
        )
    }

    suspend fun updateLocationComparedCHTSSS(updateLocationComparedCHRequest: UpdateLocationComparedCHRequest): NetworkResponse<BaseResponse<AssetDetailData, RecordResponse.RecordExtra>, ErrorResponse> {
        return apiServiceAgent.updateLocationComparedCHTSSS(
            updateLocationComparedCHRequest
        )
    }

    suspend fun updateLocationRecord(rq: UpdateLocationTSTDRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.updateLocationRecord(
            rq
        )
    }

    suspend fun updateLoaiTaiSan(updateLTSRequest: UpdateLTSRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.updateLoaiTaiSan(updateLTSRequest)
    }

    suspend fun updateLegalStatusTSCTD(updateLegalStatusRequest: UpdateLegalStatusRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.updateLegalStatusTSCTD(
            updateLegalStatusRequest
        )
    }

    suspend fun updateMucDichSuDung(updateMDSDRequest: UpdateMDSDRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.updateMucDichSuDung(
            updateMDSDRequest
        )
    }

    suspend fun updateAssetCompared(updateAssetComparedRequest: UpdateAssetComparedRequest): NetworkResponse<ResultResponse, ErrorResponse> {
        return apiServiceAgent.updateAssetCompared(
            updateAssetComparedRequest
        )
    }

    suspend fun getRecordCalculated(recordId: String): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.getRecordCalculated(recordId)
    }

    suspend fun updateCustomer(customerRequest: CustomerRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.updateCustomer(customerRequest)
    }

    suspend fun getGuaranteeFactor(recordId: String): NetworkResponse<BaseResponse<List<FactorResponseDTO>, Any>, ErrorResponse> {
        return apiServiceAgent.getGuaranteeFactor(recordId)
    }

    suspend fun updateGuaranteeFactor(rq: UpdateGuaranteeRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.updateGuaranteeFactor(rq)
    }

    suspend fun addTSSSFromSuggesstion(rq: AddTSSSFromSuggesstionRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.addTSSSFromSuggesstion(rq)
    }

    suspend fun isRecordMyAssetCard(recordID: String): NetworkResponse<TDHoSoTuongDongResponse, ErrorResponse> {
        return apiServiceAgent.isRecordMyAssetCard(recordID)
    }

//    fun getRecordMyAssetCardForThamDinh(
//        recordId: String, filters: HashMap<String, Any?>?
//    ): Flow<PagingData<TDHoSoTuongDongResponse.Properties>> {
//        return Pager(config = PagingConfig(
//            enablePlaceholders = false, initialLoadSize = PAGE_SIZE, pageSize = PAGE_SIZE
//        ), pagingSourceFactory = {
//            TDTSSSCTPagingSource(
//                apiServiceAgent, recordId, filters, headers
//            )
//        }).flow
//    }

    suspend fun getRecordMyAssetMap(recordID: String): NetworkResponse<TDHoSoTuongDongResponse, ErrorResponse> {
        return apiServiceAgent.getRecordMyAssetMap(recordID)
    }

    suspend fun addTSSSMyAsset(rq: AddTSSSTSCTRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.addTSSSMyAsset(rq)
    }

    suspend fun propertiesUpdate(rq: PropertiesUpdateRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.updateRecordAsset(rq)
    }

    suspend fun recordPhotoUpload(rq: LegalCreateOrUpdateRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.recordPhotoUpload(rq)
    }

    suspend fun recordPhotoUploadTSCTSCT(rq: LegalCreateOrUpdateRequest): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.recordPhotoUploadTSCTSCT(rq)
    }

    suspend fun recordPhotoUploadTSSS(rq: LegalCreateOrUpdateRequest): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse> {
        return apiServiceAgent.recordPhotoUploadTSSS(rq)
    }

    suspend fun reportUploadDocument(rq: HoSoPhotoUpdateRequest): NetworkResponse<BaseResponse<List<RecordResponse.RecordData.Report>, Any>, ErrorResponse> {
        return apiServiceAgent.recordReportUpload(rq)
    }

    suspend fun recordPhotoDelete(rq: LegalDeleteDocumentRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.recordPhotoDelete(rq)
    }

    suspend fun recordPhotoDeleteTSCTSCT(rq: LegalDeleteDocumentRequest): NetworkResponse<AssetDetailResponse, ErrorResponse> {
        return apiServiceAgent.recordPhotoDeleteTSCTSCT(rq)
    }

    suspend fun recordPhotoDeleteTSSS(rq: LegalDeleteDocumentRequest): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse> {
        return apiServiceAgent.recordPhotoDeleteTSSS(rq)
    }

    suspend fun recordPhotoDeleteTagTSSS(rq: LegalDeleteTagRequest): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse> {
        return apiServiceAgent.recordPhotoDeleteTagTSSS(rq)
    }

    suspend fun hoSoPhotoDelete(rq: HoSoPhotoUpdateRequest): NetworkResponse<BaseResponse<String, Any>, ErrorResponse> {
        return apiServiceAgent.deleteReportDocument(rq)
    }

    suspend fun getNotesRecord(recordID: String): NetworkResponse<NoteRecordResponse, ErrorResponse> {
        return apiServiceAgent.getNotesRecord(recordID)
    }

    suspend fun deleteNotesRecord(_note_id: String): NetworkResponse<ResultResponse, ErrorResponse> {
        return apiServiceAgent.deleteNotesRecord(_note_id)
    }

    suspend fun addNote(request: AddNoteRequest): NetworkResponse<ResultResponse, ErrorResponse> {
        return apiServiceAgent.addNote(request)
    }

    suspend fun notePhotoUpload(file: File): NetworkResponse<BaseResponse<List<Document>, Any>, ErrorResponse> {
        val part = MultipartBody.Part.createFormData(
            name = "file", filename = file.name, body = file.asRequestBody("image/*".toMediaType())
        )
        return apiServiceForUploader.notePhotoUpload(part)
    }

    suspend fun updatePhapLyThamDinh(rq: LegalCreateOrUpdateRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.updatePhapLyThamDinh(rq)
    }

    suspend fun updatePhapLyTSSSThamDinh(rq: LegalCreateOrUpdateRequest): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse> {
        return apiServiceAgent.updatePhapLyTSSSThamDinh(rq)
    }

    suspend fun deleteImagePhapLyThamDinh(rq: LegalDeleteDocumentRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.deletePhotoLegalThamDinh(rq)
    }

    suspend fun deleteImagePhapLyTSSSThamDinh(rq: LegalDeleteDocumentRequest): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse> {
        return apiServiceAgent.deletePhotoLegalTSSSThamDinh(rq)
    }

    suspend fun deletePhapLyThamDinh(rq: LegalDeleteTagRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.deleteLegalThamDinh(rq)
    }

    suspend fun deletePhapLyTSSSThamDinh(rq: LegalDeleteTagRequest): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse> {
        return apiServiceAgent.deleteLegalTSSSThamDinh(rq)
    }

    suspend fun legalThamDinhUpdateNameDocument(rq: LegalUpdateNameDocumentRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.legalThamDinhDocumentNameUpdate(
            rq
        )
    }

    suspend fun legalTSSSThamDinhUpdateNameDocument(rq: LegalUpdateNameDocumentRequest): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse> {
        return apiServiceAgent.legalTSSSThamDinhDocumentNameUpdate(
            rq
        )
    }

    suspend fun getImageWarehouseFromDuAn(duanId: String?): NetworkResponse<BaseResponse<List<ImageFromWareHouseResponse>, Any>, ErrorResponse> {
        return apiServiceSearch.getImageWarehouseFromDuAn(
            duanId
        )
    }

    suspend fun recordPhotoDeleteTagTSC(rq: LegalDeleteTagRequest): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.recordPhotoDeleteTagTSC(rq)
    }

    suspend fun photoAddFromLibrary(rq: AddFromLibraryRequestDTO?): NetworkResponse<RecordResponse, ErrorResponse> {
        return apiServiceAgent.photoAddFromLibraryTSC(
            rq
        )
    }

    suspend fun photoAddFromLibraryTSSS(rq: AddFromLibraryRequestDTO?): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse> {
        return apiServiceAgent.photoAddFromLibraryTSSS(
            rq
        )
    }

    suspend fun generateHTMLLinkVanBan(recordId: String?): NetworkResponse<BaseResponse<GenerateVanbanHMTLResponse, Any>, ErrorResponse> {
        return apiServiceAgent.generateHTMLLinkVanBan(
            recordId
        )
    }

    fun getBadgeHS(): Int {
        return preferenceManager.numberHoSo
    }

    fun setBadgeHs(badge: Int) {
        preferenceManager.numberHoSo = badge
    }

    fun resetBadgeHS() {
        preferenceManager.numberHoSo = 0
    }

    suspend fun getConfidenceGet(): NetworkResponse<BaseResponse<MutableList<StatusBarConfidenceGetResponse>, Any>, ErrorResponse> {
        return apiServiceSearch.confidenceGet()
    }

    suspend fun estimationConstruction(rq: EstimationConstructionRequest): NetworkResponse<BaseResponse<EstimationConstructionResponse, Any>, ErrorResponse> {
        return apiServiceSearch.estimationConstruction(rq)
    }


}