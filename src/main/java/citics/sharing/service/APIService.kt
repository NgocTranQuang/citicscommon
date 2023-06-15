package citics.sharing.service

import citics.sharing.data.model.request.*
import citics.sharing.data.model.request.legal.LegalCreateOrUpdateRequest
import citics.sharing.data.model.request.legal.LegalDeleteDocumentRequest
import citics.sharing.data.model.request.legal.LegalDeleteTagRequest
import citics.sharing.data.model.request.legal.LegalUpdateNameDocumentRequest
import citics.sharing.data.model.request.tham_dinh.*
import citics.sharing.data.model.response.*
import citics.sharing.data.model.response.base.BaseLoadMoreResponse
import citics.sharing.data.model.response.base.BaseResponse
import citics.sharing.data.model.response.cpoint.CPointListResponse
import citics.sharing.data.model.response.cpoint.DepositMethodResponse
import citics.sharing.data.model.response.hoahong.ContentHoaHongDTO
import citics.sharing.data.model.response.hoahong.HoaHongOverviewResponse
import citics.sharing.data.model.response.hoahong.HoaHongResponse
import citics.sharing.data.model.response.tham_dinh.*
import citics.sharing.data.model.response.tudinhgia.DeleteTSSSTSCTRequest
import citics.sharing.service.customadapter.NetworkResponse
import com.citics.valuation.data.model.response.ErrorResponse
import com.sharing.BuildConfig
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * Created by ChinhQT on 25/09/2022.
 */
interface APIService {

    //region AUTHORIZE

    @POST(BuildConfig.endpoint_new_login)
    suspend fun loginAccount(
        @Body userRequest: UserRequest
    ): NetworkResponse<UserResponse, ErrorResponse>

    @HTTP(method = "DELETE", path = "v2.4/authorize/auth.logout", hasBody = true)
    suspend fun logout(): NetworkResponse<LogoutResponse, ErrorResponse>

    @POST("v2.4/authorize/auth.forget.password")
    suspend fun forgetPassword(
        @Body request: ForgetPasswordRequest
    ): NetworkResponse<ForgetPasswordResponse, ErrorResponse>

    @POST("v2.4/authorize/auth.opt.confirm")
    suspend fun confirmOTP(
        @Body request: OTPRequest
    ): NetworkResponse<OTPResponse, ErrorResponse>

    @POST("v2.4/authorize/auth.opt.confirm.login")
    suspend fun confirmOTPLoginInNewDevice(
        @Body request: OTPRequest
    ): NetworkResponse<OTPResponse, ErrorResponse>

    @POST("v2.4/authorize/auth.opt.resend")
    suspend fun resendOTP(
        @Body request: ResendRequest
    ): NetworkResponse<ResendResponse, ErrorResponse>

    @POST("v2.4/authorize/auth.opt.resend")
    suspend fun resendOTPInNewDeView(
        @Body request: ResendRequest
    ): NetworkResponse<ResendResponse, ErrorResponse>

    @POST("v2.4/authorize/auth.set.password")
    suspend fun setNewPassword(
        @Body request: NewPassRequest
    ): NetworkResponse<NewPassResponse, ErrorResponse>

    //endregion

    //region USER

    @GET("v2/account/detail.get")
    suspend fun getUserProfile(): NetworkResponse<UserProfileResponse, ErrorResponse>

    @Multipart
    @POST("v2/cac/file/account/upload-avatar")
    suspend fun avatarUpload(
        @Part file: MultipartBody.Part
    ): NetworkResponse<BaseResponse<UserProfileResponse.Avatar, Any>, ErrorResponse>

    @POST("v2.4/authorize/auth.change.password")
    suspend fun changePassword(
        @Body changePassRequest: ChangePassRequest
    ): NetworkResponse<ResultResponse, ErrorResponse>

    //endregion

    //region REGISTER

    @POST("v2.4/static/subscribe.expert")
    suspend fun subscribe(
        @Body request: RegisterRequest
    ): NetworkResponse<RegisterResponse, ErrorResponse>

    //endregion

    //region STATIC

    @GET("v2.4/static/configuration.full")
    suspend fun getTemplate(): NetworkResponse<TemplateResponse, ErrorResponse>

    @GET("v2.4/asset/data-available")
    suspend fun getDataAvailable(): NetworkResponse<DataAvailableResponse, ErrorResponse>

    @GET("v2.4/static/area.by.code")
    suspend fun getAreas(@Query("area_code") area_code: String): NetworkResponse<AreaResponse, ErrorResponse>

    @GET("v2.4/static/area.by.term")
    suspend fun getAreasSuggestion(
        @Query("term") term: String, @Query("limit") limit: Int = 10
    ): NetworkResponse<AreasSuggestionResponse, ErrorResponse>

    @GET("v2.4/static/static.info")
    suspend fun getStaticNhaDatInfo(
        @Query("property_type") property_type: String
    ): NetworkResponse<StaticNhaDatResponse, ErrorResponse>

    @GET("v2.4/static/static.info")
    suspend fun getStaticCanHoInfo(
        @Query("property_type") property_type: String
    ): NetworkResponse<StaticCanHoResponse, ErrorResponse>

    // Get Mục đích sử dụng đất
    @GET("v2.4/static/land/purpose.using")
    suspend fun getLandPurposes(): NetworkResponse<BaseResponse<List<DetailUsingPurpose>, Any>, ErrorResponse>

    @GET("v2.4/static/don_gia_ubnd.by.ma_quan/{maquan}")
    suspend fun getDonGiaUBND(@Path("maquan") maquan: String?): NetworkResponse<BaseResponse<DonGiaUBNDResponse, Any>, ErrorResponse>

    //endregion

    //region MAP

    @GET("v2.4/asset/google.geocode")
    suspend fun getGoogleGeocode(
        @Query("latlng") latLng: String
    ): NetworkResponse<GooglePlacesResponse, ErrorResponse>

    @GET("v2.4/asset/google-place.by.id")
    suspend fun getPlaceByID(
        @Query("place_id") id: String
    ): NetworkResponse<GooglePlaceResponse, ErrorResponse>

    @GET("v2.4/asset/land.by.wgs/{cp}")
    suspend fun getLandDetailByLatLng(
        @Path("cp") cp: String?,
        @Query("latitude") lat: Double,
        @Query("longitude") lng: Double,
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @POST("v2.4/asset/land.vn2000.city/{cp}")
    suspend fun getLandDetailsByVN2000City(
        @Path("cp") cp: String?,
        @Body request: RequestBody
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @GET("v2.4/asset/land.by.parcel/{cp}")
    suspend fun getLandDetailBySoToSoThua(
        @Path("cp") cp: String?,
        @Query("ma_thanh_pho") ma_thanh_pho: String,
        @Query("ma_quan") ma_quan: String,
        @Query("ma_phuong") ward: String,
        @Query("so_to") soto: String,
        @Query("so_thua") sothua: String,

        ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @GET("v2.4/asset/land.suggest")
    suspend fun getLandSuggestion(
        @Query("term") term: String,
        @Query("size") size: Int = 20,

        ): NetworkResponse<LandSuggestionResponse, ErrorResponse>

    //endregion

    //region ASSET

    @POST("v2.4/calculate/estimation.asset")
    suspend fun estimationAsset(
        @Body estimationAssetRequest: EstimationAssetRequest,
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @GET("v2.4/asset/project.suggest")
    suspend fun getCanHoSuggestion(
        @Query("term") term: String,
        @Query("size") size: Int = 20,

        ): NetworkResponse<LandSuggestionResponse, ErrorResponse>

    @GET("v2.4/asset/project.detail/{id}")
    suspend fun getProjectDetail(
        @Path("id") id: String
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @GET("v2.4/asset/project.detail/{id}")
    suspend fun getProjectDetail2(
        @Path("id") id: String
    ): NetworkResponse<ProjectDetailResponse, ErrorResponse>

    @GET("v2.4/asset/projects/filter")
    suspend fun getCanHoAdvanced(
        @Query("query") query: String,
        @Query("page") page: Int = 20,
        @Query("shapes") shapes: String,

        ): NetworkResponse<CanHoAdvancedResponse, ErrorResponse>

    @GET("v2.4/asset/projects/{id}/apartment-suggestion")
    suspend fun getCanHoSuggestion(
        @Path("id") id: String,
        @Query("term") term: String,

        ): NetworkResponse<CanHoSuggestionResponse, ErrorResponse>

    @GET("v2.4/asset/projects/{id}/apartment-codes/list")
    suspend fun getAllCanHoByProjectId(
        @Path("id") id: String
    ): NetworkResponse<BaseResponse<MutableList<String>, Any>, ErrorResponse>

    @GET("v2.4/asset/project/apartment.filter.advance")
    suspend fun getCanHoFilterAdvance(
        @QueryMap filters: HashMap<String, Any?>?
        ): NetworkResponse<CanHoFilterAdvanceResponse, ErrorResponse>


    @GET("v2.4/asset/projects/{id}/filter-options")
    suspend fun getOptionsSuggestion(
        @Path("id") id: String
    ): NetworkResponse<OptionsSuggestionResponse, ErrorResponse>

    @GET("v2.4/asset/citics-price-chart/{id}")
    suspend fun getCiticsPriceChart(
        @Path("id") id: String
    ): NetworkResponse<CiticsPriceChartResponse, ErrorResponse>

    @GET("v2.4/asset/project/apartment.filter/{cp}")
    suspend fun getApartmentAsset(
        @Path("cp") cp: String?,
        @QueryMap filters: HashMap<String, Any?>?,
        ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @GET("v2.4/calculate/self.asset.calculate")
    suspend fun getSelfAssetCalculate(

    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @GET("v2.4/asset/self.mine.asset.calculate")
    suspend fun getSelfAssetCalculateTSCT(
        @Query("my_asset_id") my_asset_id: String
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @POST("v2.4/calculate/self.asset.compared/add")
    suspend fun selfAssetComparedAdd(
        @Body body: RequestBody
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>


    @POST("v2.4/asset/mine.asset.compared/add")
    suspend fun addTSSSTSCT(
        @Body body: RequestBody
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>


    @POST("v2.4/asset/mine.upsert")
    suspend fun mineUpsert(
        @Body body: RequestBody
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @HTTP(method = "DELETE", path = "v2.4/calculate/self.asset.compared/delete", hasBody = true)
    suspend fun deleteComparedAsset(
        @Query("asset_id") asset_id: String
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @HTTP(method = "DELETE", path = "v2.4/asset/mine.asset.compared/delete", hasBody = true)
    suspend fun deleteComparedAssetTSCT(
        @Body body: DeleteTSSSTSCTRequest
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    // Cập nhật thông tin tài sản so sánh tự định giá
    @POST("v2.4/calculate/self.asset.compared/update")
    suspend fun updateComparedAsset(
        @Body body: RequestBody
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    // Cập nhật thông tin tài sản so sánh tự định giá
    @POST("v2.4/asset/mine.asset.compared/update")
    suspend fun updateComparedAssetTSCT(
        @Body body: RequestBody
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    // Tìm tài sản so sánh
    @GET("v2.4/record/compared.assets.get/{recordId}")
    suspend fun getComparedAssetsFromMap(
        @Path("recordId") recordId: String,
        @QueryMap filters: HashMap<String, Any?>?,

        ): NetworkResponse<TDComparedAssetMapResponse, ErrorResponse>

    // Estimate CTXD
    @GET("v2.4/asset/assess-price/{id}")
    suspend fun getLichSuThamDinhGia(
        @Path("id") id: String
    ): NetworkResponse<BaseResponse<List<ListLichSuThamDinhGiaDTO>, Any>, ErrorResponse>

    @GET("v2.4/asset/generate.report.bcn.html")
    suspend fun genHTML(
        @Query("my_asset_id") my_asset_id: String
    ): NetworkResponse<HTMLResponse, ErrorResponse>

    @GET("v2.4/asset/generate.report.bcn.pdf")
    suspend fun genPDF(
        @Query("my_asset_id") my_asset_id: String
    ): NetworkResponse<PDFResponse, ErrorResponse>

    @POST("v2.4/asset/label.update")
    suspend fun labelUpdate(
        @Body reqest: AssetLabelRequest?
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @POST("v2.4/asset/level.upgrade")
    suspend fun levelUpgrade(
        @Body reqest: AssetLevelRequest?
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    //endregion

    //region TuDinhGia

    @POST("v2.4/calculate/self.asset.request")
    suspend fun selfAssetRequest(
        @Body body: RequestBody
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    //endregion

    //region My Asset

    @GET("v2.4/asset/mine.filter.map.by.service")
    suspend fun getMineFilterMap(
        @QueryMap filters: HashMap<String, Any?>? = hashMapOf(),

        ): NetworkResponse<MineFilterMapResponse, ErrorResponse>

    @GET("v2.4/asset/mine.filter.cart.by.service")
    suspend fun getMineFilterCard(
        @QueryMap filters: HashMap<String, Any?>? = hashMapOf(),

        ): NetworkResponse<MineFilterCardResponse, ErrorResponse>

    @GET("v2.4/asset/mine.filter.summary.by.service")
    suspend fun getMineFilterSummary(

    ): NetworkResponse<MineFilterSummaryResponse, ErrorResponse>

    @GET("v2.4/asset/vote")
    suspend fun getAssetVote(
        @Query("asset_id") asset_id: String,
        @Query("is_change") is_change: Boolean?,

        ): NetworkResponse<AssetVoteResponse, ErrorResponse>

    @GET("v2.4/asset/mine.delete.by.id")
    suspend fun getMineDeleteByID(
        @Query("my_asset_id") asset_id: String
    ): NetworkResponse<AssetDeleteResponse, ErrorResponse>

    @GET("v2.4/asset/mine.get.by.id")
    suspend fun getMineById(
        @Query("my_asset_id") my_asset_id: String
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @GET("v2.4/asset/get.cvalue")
    suspend fun getCValueOfAsset(
        @Query("my_asset_id") my_asset_id: String
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @POST("v2.4/asset/mine.asset.compared/add")
    suspend fun addTSSSFromMyAsset(
        @Body request: TSSSMyAssetRequest
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @POST("v2.4/asset/mine.asset.compared/update")
    suspend fun updateTSSSFromMyAsset(
        @Body request: TSSSMyAssetRequest
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    //endregion

    //region Legal, Photo

    //region UPLOADER

    @Multipart
    @POST("v2/cva/file/agency.attachment")
    suspend fun agencyPhotoUpload(
        @Part file: MultipartBody.Part
    ): NetworkResponse<BaseResponse<MutableList<Document>, Any>, ErrorResponse>

    @Multipart
    @POST("v2/cva/file/legal.upload")
    suspend fun legalPhotoUpload(
        @Part file: MultipartBody.Part
    ): NetworkResponse<BaseResponse<MutableList<Document>, Any>, ErrorResponse>

    @Multipart
    @POST("v2/cva/file/asset.photo.upload")
    suspend fun assetPhotoUpload(
        @Part file: MultipartBody.Part
    ): NetworkResponse<BaseResponse<MutableList<Document>, Any>, ErrorResponse>

    @POST("v2.4/record/photo.update")
    suspend fun recordPhotoUpload(
        @Body request: LegalCreateOrUpdateRequest
    ): NetworkResponse<RecordResponse, ErrorResponse>

    @POST("v2.4/asset/mine.asset/photo.update")
    suspend fun recordPhotoUploadTSCTSCT(
        @Body request: LegalCreateOrUpdateRequest
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @POST("v2.4/record/asset.compared/photo.update")
    suspend fun recordPhotoUploadTSSS(
        @Body request: LegalCreateOrUpdateRequest
    ): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse>

    @POST("v2.4/report/upload.document")
    suspend fun recordReportUpload(
        @Body request: HoSoPhotoUpdateRequest
    ): NetworkResponse<BaseResponse<List<RecordResponse.RecordData.Report>, Any>, ErrorResponse>

    // For ThamDinh - GhiChuHoSo
    @Multipart
    @POST("v2/cva/file/file.upload")
    suspend fun notePhotoUpload(
        @Part file: MultipartBody.Part
    ): NetworkResponse<BaseResponse<List<Document>, Any>, ErrorResponse>

    // Delete document
    @HTTP(method = "DELETE", path = "v2.4/report/delete.document", hasBody = true)
    suspend fun deleteReportDocument(
        @Body request: HoSoPhotoUpdateRequest
    ): NetworkResponse<BaseResponse<String, Any>, ErrorResponse>

    //endregion

    @HTTP(method = "DELETE", path = "v2.4/record/photo.delete", hasBody = true)
    suspend fun recordPhotoDelete(
        @Body request: LegalDeleteDocumentRequest
    ): NetworkResponse<RecordResponse, ErrorResponse>


    @HTTP(method = "DELETE", path = "v2.4/asset/mine.asset/photo.delete", hasBody = true)
    suspend fun recordPhotoDeleteTSCTSCT(
        @Body request: LegalDeleteDocumentRequest
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>


    @HTTP(method = "DELETE", path = "v2.4/record/asset.compared/photo.delete", hasBody = true)
    suspend fun recordPhotoDeleteTSSS(
        @Body request: LegalDeleteDocumentRequest
    ): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse>

    @HTTP(method = "DELETE", path = "v2.4/record/asset.compared/photo.delete.tag", hasBody = true)
    suspend fun recordPhotoDeleteTagTSSS(
        @Body request: LegalDeleteTagRequest
    ): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse>

    @HTTP(method = "DELETE", path = "v2.4/record/photo.delete.tag", hasBody = true)
    suspend fun recordPhotoDeleteTagTSC(
        @Body request: LegalDeleteTagRequest
    ): NetworkResponse<RecordResponse, ErrorResponse>

    @POST("v2.4/calculate/self.asset/legal.update")
    suspend fun updatePhapLy(
        @Body reqest: LegalCreateOrUpdateRequest
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @POST("v2.4/asset/mine.asset/legal.update")
    suspend fun updatePhapLyTSCTSCT(
        @Body reqest: LegalCreateOrUpdateRequest
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @HTTP(method = "DELETE", path = "v2.4/calculate/self.asset/legal.delete.tag", hasBody = true)
    suspend fun deletePhapLy(
        @Body reqest: LegalDeleteTagRequest
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @HTTP(method = "DELETE", path = "v2.4/asset/mine.asset/legal.delete.tag", hasBody = true)
    suspend fun deletePhapLyTSCTSCT(
        @Body reqest: LegalDeleteTagRequest
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>


    @HTTP(method = "DELETE", path = "v2.4/calculate/self.asset/legal.delete", hasBody = true)
    suspend fun deleteImagesPhapLy(
        @Body reqest: LegalDeleteDocumentRequest
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @HTTP(method = "DELETE", path = "v2.4/asset/mine.asset/legal.delete", hasBody = true)
    suspend fun deleteImagesPhapLyTSCTSCT(
        @Body reqest: LegalDeleteDocumentRequest
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @GET("v2.4/static/legal/property-type/{type}")
    suspend fun legalProperty(
        @Path("type") type: Int
    ): NetworkResponse<BaseResponse<LegalPropertyResponse, Any>, ErrorResponse>

    @POST("v2.4/calculate/self.asset/legal.update.name")
    suspend fun legalDocumentNameUpdate(
        @Body reqest: LegalUpdateNameDocumentRequest
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    @POST("v2.4/asset/mine.asset/legal.update.name")
    suspend fun legalDocumentNameUpdateTSCTSCT(
        @Body reqest: LegalUpdateNameDocumentRequest
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    //endregion

    //region ThamDinh - Validation

    //region ThamDinh - TSChinh

    @GET("v2.4/record/list.pin.get")
    suspend fun getHoSoChoNhan(

    ): NetworkResponse<HoSoChoNhanResponse, ErrorResponse>

    @POST("v2.4/process/action.execution")
    suspend fun xuLyHoSoND(
        @Body hoSoRequest: HoSoRequest
    ): NetworkResponse<RecordResponse, ErrorResponse>

    @POST("v2.4/record/properties.update")
    suspend fun updateRecordAsset(
        @Body request: PropertiesUpdateRequest
    ): NetworkResponse<RecordResponse, ErrorResponse>

    @POST("v2.4/record/property-type.update")
    suspend fun updateLoaiTaiSan(
        @Body updateLTSRequest: UpdateLTSRequest
    ): NetworkResponse<RecordResponse, ErrorResponse>

    @POST("v2.4/record/purpose.update")
    suspend fun updateMucDichSuDung(
        @Body updateMDSDRequest: UpdateMDSDRequest
    ): NetworkResponse<RecordResponse, ErrorResponse>

    @POST("v2.4/record/legal.update.status")
    suspend fun updateLegalStatusTSCTD(
        @Body updateLegalStatusRequest: UpdateLegalStatusRequest,

        ): NetworkResponse<RecordResponse, ErrorResponse>

    @POST("v2.4/record/customer.update")
    suspend fun updateCustomer(
        @Body customerRequest: CustomerRequest
    ): NetworkResponse<RecordResponse, ErrorResponse>

    //    Tính giá trị hồ sơ
    @GET("v2.4/record/record.calculated/{recordId}")
    suspend fun getRecordCalculated(
        @Path("recordId") recordId: String?
    ): NetworkResponse<RecordResponse, ErrorResponse>

    // Get list record survey
    @GET("v2.4/record/list.survey.get")
    suspend fun getListRecord(
        @Query("size") perPage: Int? = 20,
        @Query("page") page: Int? = 0,
        @QueryMap filters: HashMap<String, Any?>? = hashMapOf(),

        ): NetworkResponse<BaseResponse<ThamDinhResponse, Any>, ErrorResponse>

    // Get record details
    @GET("v2.4/record/detail.get/{recordId}")
    suspend fun getRecordDetail(
        @Path("recordId") recordId: String,
        @Query("check_vi_pham_tsss") check_vi_pham_tsss: Boolean = false,

        ): NetworkResponse<RecordResponse, ErrorResponse>

    // Lấy lịch sử bảng tính và bao cáo offline
    @GET("v2.4/record/record.other/history/{recordId}")
    suspend fun getRecordBangTinhHistory(
        @Path("recordId") _recordId: String?
    ): NetworkResponse<RecordResponse, ErrorResponse>

    //    Lấy danh sách hồ sơ trong cùng hợp đồng
    @GET("v2.4/record/record.in.contract/{id}")
    suspend fun getRecordWithSameContract(
        @Path("id") _id: String?
    ): NetworkResponse<RecordResponse, ErrorResponse>

    @GET("v2.4/record/history.get/{recordId}")
    suspend fun getRecordHistory(
        @Path("recordId") recordId: String?
    ): NetworkResponse<RecordHistoryResponse, ErrorResponse>

    //    Lấy những điều kiện mà hồ sơ đã bị vượt điều kiện ngân hàng
    @GET("v2.4/record/guarantee.get/{recordId}")
    suspend fun getRecordGuarantee(
        @Path("recordId") recordId: String?
    ): NetworkResponse<RecordResponse, ErrorResponse>

    // Cập nhật vị trí cho tài sản tham dinh
    @POST("v2.4/record/location.update")
    suspend fun updateLocationRecord(
        @Body rq: UpdateLocationTSTDRequest
    ): NetworkResponse<RecordResponse, ErrorResponse>

    @GET("v2.4/guarantee/factor.others")
    suspend fun getGuaranteeFactor(
        @Query("record_id") recordId: String
    ): NetworkResponse<BaseResponse<List<FactorResponseDTO>, Any>, ErrorResponse>

    @POST("v2.4/guarantee/guarantee.submit")
    suspend fun updateGuaranteeFactor(
        @Body request: UpdateGuaranteeRequest
    ): NetworkResponse<RecordResponse, ErrorResponse>

    // Lấy danh sách hồ sơ tương đồng
    @GET("v2.4/record/record.interact.list/{recordID}")
    suspend fun getInteractList(
        @Path("recordID") recordID: String
    ): NetworkResponse<TDHoSoTuongDongResponse, ErrorResponse>

    @GET("v2.4/record/record.interact.list/{recordID}")
    suspend fun getInteractList(
        @Path("recordID") recordID: String,
        @QueryMap filters: HashMap<String, Any?>?,

        ): NetworkResponse<TDHoSoTuongDongResponse, ErrorResponse>

    @GET("v2.4/record/record.price.history/{recordID}")
    suspend fun getHistoryList(
        @Path("recordID") recordID: String
    ): NetworkResponse<TDLichSuResponse, ErrorResponse>

    @GET("v2.4/record/record.public/{recordID}")
    suspend fun getRecordPublicList(
        @Path("recordID") recordID: String
    ): NetworkResponse<RecordResponse, ErrorResponse>

    @GET("v2.4/record/mine.compared.assets.cart/{recordID}")
    suspend fun isRecordMyAssetCard(
        @Path("recordID") recordID: String
    ): NetworkResponse<TDHoSoTuongDongResponse, ErrorResponse>

    @GET("v2.4/record/mine.compared.assets.cart/{recordId}")
    suspend fun getRecordMyAssetCard(
        @Path("recordId") recordId: String,
        @QueryMap filters: HashMap<String, Any?>?,
    ): NetworkResponse<TDHoSoTuongDongResponse, ErrorResponse>

    @GET("v2.4/record/mine.compared.assets.map/{recordID}")
    suspend fun getRecordMyAssetMap(
        @Path("recordID") recordID: String
    ): NetworkResponse<TDHoSoTuongDongResponse, ErrorResponse>

    @POST("v2.4/record/mine.asset.compared/add")
    suspend fun addTSSSMyAsset(
        @Body request: AddTSSSTSCTRequest
    ): NetworkResponse<RecordResponse, ErrorResponse>

    @GET("v2.4/note/all.get")
    suspend fun getNotesRecord(
        @Query("id") recordID: String
    ): NetworkResponse<NoteRecordResponse, ErrorResponse>

    @POST("v2.4/note/submit")
    suspend fun addNote(
        @Body request: AddNoteRequest
    ): NetworkResponse<ResultResponse, ErrorResponse>

    @HTTP(method = "DELETE", path = "v2.4/note/delete", hasBody = true)
    suspend fun deleteNotesRecord(
        @Query("_note_id") _note_id: String
    ): NetworkResponse<ResultResponse, ErrorResponse>

    @POST("v2.4/record/legal.update")
    suspend fun updatePhapLyThamDinh(
        @Body reqest: LegalCreateOrUpdateRequest
    ): NetworkResponse<RecordResponse, ErrorResponse>

    // Delete legal image thẩm định
    @HTTP(method = "DELETE", path = "v2.4/record/legal.delete", hasBody = true)
    suspend fun deletePhotoLegalThamDinh(
        @Body rq: LegalDeleteDocumentRequest
    ): NetworkResponse<RecordResponse, ErrorResponse>

    // Delete legal thẩm định
    @HTTP(method = "DELETE", path = "v2.4/record/legal.delete.tag", hasBody = true)
    suspend fun deleteLegalThamDinh(
        @Body rq: LegalDeleteTagRequest
    ): NetworkResponse<RecordResponse, ErrorResponse>

    @POST("v2.4/record/legal.update.name")
    suspend fun legalThamDinhDocumentNameUpdate(
        @Body reqest: LegalUpdateNameDocumentRequest
    ): NetworkResponse<RecordResponse, ErrorResponse>

    //endregion

    //region ThamDinh - TSSS

    //    Lấy chi tiết tài sản so sánh từ warehouse
    @GET("v2.4/record/asset.compared/warehouse.get/{assetId}")
    suspend fun getAssetComparedWarehouse(
        @Path("assetId") _assetId: String?
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    //    Lấy chi tiết tài sản so sanh
    @GET("v2.4/record/asset.compared/detail.get/{assetId}")
    suspend fun getAssetComparedDetail(
        @Path("assetId") _assetId: String?
    ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    // Thêm tài sản so sanh vào hồ sơ
    @POST("v2.4/record/asset.compared/add")
    suspend fun addAssetCompared(
        @Body addAssetComparedRequest: AddAssetComparedRequest,

        ): NetworkResponse<RecordResponse, ErrorResponse>

    // Update legal compared asset
    @POST("v2.4/record/asset.compared/legal.update.status")
    suspend fun updateLegalStatusTSSSTD(
        @Body updateLegalStatusComparedRequest: UpdateLegalStatusComparedRequest,

        ): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse>

    @POST("v2.4/asset/mine.asset/legal.update.status")
    suspend fun updateLegalStatusTSCTSCT(
        @Body updateLegalStatusComparedRequest: UpdateLegalStatusComparedRequest,

        ): NetworkResponse<AssetDetailResponse, ErrorResponse>

    // Dung khi them tai san so sanh bang nhap lieu
    @POST("v2.4/record/asset.compared/location.update")
    suspend fun updateLocationCompared(
        @Body updateLocationComparedRequest: UpdateLocationComparedRequest,

        ): NetworkResponse<BaseResponse<AssetDetailData, RecordResponse.RecordExtra>, ErrorResponse>

    // Cap nhat vi tri cho tai san so sanh da ton tai
    @POST("v2.4/record/asset.compared/location.update")
    suspend fun updateLocationComparedForTSSS(
        @Body updateLocationComparedRequest: UpdateLocationComparedRequestForTSSS,

        ): NetworkResponse<BaseResponse<AssetDetailData, RecordResponse.RecordExtra>, ErrorResponse>

    @POST("v2.4/record/asset.compared/location.update")
    suspend fun updateLocationComparedCH(
        @Body updateLocationComparedCanHoRequest: UpdateLocationComparedCanHoRequest,

        ): NetworkResponse<BaseResponse<AssetDetailData, RecordResponse.RecordExtra>, ErrorResponse>

    @POST("v2.4/record/asset.compared/location.update")
    suspend fun updateLocationComparedCHTSSS(
        @Body updateLocationComparedCHRequest: UpdateLocationComparedCHRequest,

        ): NetworkResponse<BaseResponse<AssetDetailData, RecordResponse.RecordExtra>, ErrorResponse>

    // Cập nhật tài sản so sanh trong hồ sơ
    @POST("v2.4/record/asset.compared/update")
    suspend fun updateAssetCompared(
        @Body updateAssetComparedRequest: UpdateAssetComparedRequest,

        ): NetworkResponse<ResultResponse, ErrorResponse>

    // Xóa tài sản so sanh trong hồ sơ
    @HTTP(method = "DELETE", path = "v2.4/record/asset.compared/delete", hasBody = true)
    suspend fun deleteAssetCompared(
        @Body deleteAssetCompared: DeleteAssetCompared,

        ): NetworkResponse<RecordResponse, ErrorResponse>

    // Delete legal compared asset
    @HTTP(method = "DELETE", path = "v2.4/record/asset.compared/legal.delete", hasBody = true)
    suspend fun deleteLegalAssetCompared(
        @Body deleteLegalAssetCompared: DeleteLegalAssetCompared,

        ): NetworkResponse<RecordResponse, ErrorResponse>

    // Thêm tài sản so sánh từ tài sản gợi ý
    @POST("v2.4/record/asset.compared/interact.add")
    suspend fun addTSSSFromSuggesstion(
        @Body request: AddTSSSFromSuggesstionRequest
    ): NetworkResponse<RecordResponse, ErrorResponse>

    @POST("v2.4/record/asset.compared/legal.update")
    suspend fun updatePhapLyTSSSThamDinh(
        @Body reqest: LegalCreateOrUpdateRequest
    ): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse>

    // Delete legal image thẩm định
    @HTTP(method = "DELETE", path = "v2.4/record/asset.compared/legal.delete", hasBody = true)
    suspend fun deletePhotoLegalTSSSThamDinh(
        @Body rq: LegalDeleteDocumentRequest
    ): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse>

    // Delete legal thẩm định
    @HTTP(method = "DELETE", path = "v2.4/record/asset.compared/legal.delete.tag", hasBody = true)
    suspend fun deleteLegalTSSSThamDinh(
        @Body rq: LegalDeleteTagRequest
    ): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse>

    @POST("v2.4/record/asset.compared/legal.update.name")
    suspend fun legalTSSSThamDinhDocumentNameUpdate(
        @Body reqest: LegalUpdateNameDocumentRequest
    ): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse>

    //endregion

    @GET("v2.4/gallery/project_id/{duanId}")
    suspend fun getImageWarehouseFromDuAn(
        @Path("duanId") duanId: String?
    ): NetworkResponse<BaseResponse<List<ImageFromWareHouseResponse>, Any>, ErrorResponse>

    @POST("v2.4/record/photo.add.from.library")
    suspend fun photoAddFromLibraryTSC(
        @Body reqest: AddFromLibraryRequestDTO?
    ): NetworkResponse<RecordResponse, ErrorResponse>


    @POST("v2.4/record/asset.compared/photo.add.from.library")
    suspend fun photoAddFromLibraryTSSS(
        @Body reqest: AddFromLibraryRequestDTO?
    ): NetworkResponse<CompareAsssetThamDinhResponse, ErrorResponse>

    //endregions

    //region Notification

    @POST("v2/notification/device.register")
    suspend fun registerNotification(
        @Body notificationRequest: NotificationRequest,

        ): NetworkResponse<NotificationResponse, ErrorResponse>

    //endregion

    //region Dashboard

    @GET("v2.4/dashboard/process.bar")
    suspend fun getProgressBar(
        @Query("type") type: String
    ): NetworkResponse<RecordStatusResponse, ErrorResponse>

    // Get Dashboard Filters
    @GET("v2.4/dashboard/filter.bar.status")
    suspend fun getFilterBarStatus(
        @Query("type") type: String
    ): NetworkResponse<BaseResponse<MutableList<StatusBarResponse>, Any>, ErrorResponse>

    // Get Dashboard Filters
    @GET("v2.4/dashboard/filter.bar.bank")
    suspend fun getListBankOrCN(
        @Query("bank_id") bankId: String?
    ): NetworkResponse<BaseResponse<MutableList<BarBankResponse>, Any>, ErrorResponse>

    // Get list record status
    @GET("v2.4/dashboard/menu.bar")
    suspend fun getMenuBarListStatus(
        @Query("type") type: String?
    ): NetworkResponse<BaseResponse<MutableList<StatusBarResponse>, Any>, ErrorResponse>

    // Get list record status
    @GET("v2.4/dashboard/record/analysis.value")
    suspend fun getMenuBarBadge(
        @Query("type") type: String?
    ): NetworkResponse<BaseResponse<MutableList<StatusBarResponse>, Any>, ErrorResponse>

    @GET("v2.4/dash/dash.commission.detail/{recordId}")
    suspend fun getDashCommissionDetail(
        @Path("recordId") recordId: String?
    ): NetworkResponse<ResultResponse, ErrorResponse>

    @GET("v2.4/dash/dash.commission.history/{recordId}")
    suspend fun getDashCommissionHistory(
        @Path("recordId") recordId: String?
    ): NetworkResponse<ResultResponse, ErrorResponse>

    @GET("v2.4/dash/dash.commission.overview")
    suspend fun getDashCommissionOverview(

    ): NetworkResponse<ResultResponse, ErrorResponse>

    @GET("v2.4/dash/dash.commissions")
    suspend fun getDashCommission(

    ): NetworkResponse<ResultResponse, ErrorResponse>

    @GET("v2.4/dash/dash.record")
    suspend fun getDashRecord(
        @Query("from") type: String?,
        @Query("to") to: String?,

        ): NetworkResponse<DashRecordResponse, ErrorResponse>

    @GET("v2.4/dash/dash.record.growth")
    suspend fun getDashRecordGrowth(

    ): NetworkResponse<DashGrowthResponse, ErrorResponse>

    @GET("v2.4/dash/dash.record.type")
    suspend fun getDashRecordType(
        @Query("from") type: String?,
        @Query("to") to: String?,

        ): NetworkResponse<DashRecordTypeResponse, ErrorResponse>

    @GET("v2.4/record/pin.number")
    suspend fun recordPinNumber(

    ): NetworkResponse<BaseResponse<NPin?, Any>, ErrorResponse>


    //endregion

    //region CPoint

    @GET("v2.4/c-point/available.get")
    suspend fun getCPointAvailable(

    ): NetworkResponse<ResultResponse, ErrorResponse>

    @GET("v2.4/c-point/balance.get")
    suspend fun getCPointBalance(

    ): NetworkResponse<ResultResponse, ErrorResponse>

    @GET("v2.4/c-point/deposit.methods")
    suspend fun getCPointDeposit(

    ): NetworkResponse<ResultResponse, ErrorResponse>

    @GET("v2.4/c-point/donated.get")
    suspend fun getCPointDonated(

    ): NetworkResponse<ResultResponse, ErrorResponse>

    @GET("v2.4/c-point/history.get")
    suspend fun getCPointHistory(

    ): NetworkResponse<ResultResponse, ErrorResponse>

    @GET("v2.4/c-point/searchable.get")
    suspend fun getCPointSearchable(

    ): NetworkResponse<ResultResponse, ErrorResponse>

    @GET("v2.4/c-point/withdraw.get")
    suspend fun getCPointWithdraw(

    ): NetworkResponse<ResultResponse, ErrorResponse>

    //endregion

    //region Biometric

    @POST(BuildConfig.endpoint_new_bio)
    suspend fun loginBiometric(
        @Body biometricRequest: BiometricRequest
    ): NetworkResponse<UserResponse, ErrorResponse>


    @POST("v2.4/bio/register")
    suspend fun registerBiometric(
        @Body rq: RegisterBiometricRequest?
    ): NetworkResponse<BaseResponse<RegisterResponseDTO, Any>, ErrorResponse>

    //endregion

    // region Hoa Hồng
    // Get list record survey
    @GET("v2.4/dash/dash.commissions")
    suspend fun getListHoaHong(
        @Query("size") perPage: Int? = 20,
        @Query("page") page: Int? = 0,
        @QueryMap filters: HashMap<String, Any?>? = hashMapOf(),

        ): NetworkResponse<BaseResponse<HoaHongResponse, Any>, ErrorResponse>

    @GET("v2.4/dash/dash.commission.overview")
    suspend fun getHoaHongOverview(

    ): NetworkResponse<BaseResponse<HoaHongOverviewResponse, Any>, ErrorResponse>

    @GET("v2.4/dash/dash.commission.detail/{id}")
    suspend fun getHoaHongDetail(
        @Path("id") id: String?
    ): NetworkResponse<BaseResponse<ContentHoaHongDTO, Any>, ErrorResponse>

    @GET("v2.4/c-point/available.get")
    suspend fun getCPointCuaBan(

    ): NetworkResponse<BaseResponse<Long?, Any>, ErrorResponse>

    @GET("v2.4/c-point/withdraw.get")
    suspend fun getCPointDaDung(

    ): NetworkResponse<BaseResponse<Long?, Any>, ErrorResponse>

    // Get list cpoint

    // Get list record survey
    @GET("v2.4/c-point/history.get")
    suspend fun getListCPoint(
        @Query("size") perPage: Int? = 20,
        @Query("page") page: Int? = 0,
        @Query("type") type: Int? = 0,

        ): NetworkResponse<BaseResponse<BaseLoadMoreResponse<CPointListResponse>, Any>, ErrorResponse>


    @GET("v2.4/c-point/deposit.methods")
    suspend fun getDepositMethods(

    ): NetworkResponse<BaseResponse<List<DepositMethodResponse>, Any>, ErrorResponse>

    // Get Dashboard Filters
    @GET("v2.4/static/confidence.get")
    suspend fun confidenceGet(

    ): NetworkResponse<BaseResponse<MutableList<StatusBarConfidenceGetResponse>, Any>, ErrorResponse>

    @GET("v2.4/asset/boundaries/cadastres/filter")
    suspend fun getRanhThuaBoundaries(
        @Query("boundaries.max_longitude") maxLng: Double,
        @Query("boundaries.min_longitude") minLng: Double,
        @Query("boundaries.max_latitude") maxLat: Double,
        @Query("boundaries.min_latitude") minLat: Double,
        @Query("tiny") tiny: Boolean,

        ): NetworkResponse<RanhThuaResponse, ErrorResponse>

    @GET("v2.4/record/report.generate.html")
    suspend fun generateHTMLLinkVanBan(
        @Query("recordId") recordId: String?,

        ): NetworkResponse<BaseResponse<GenerateVanbanHMTLResponse, Any>, ErrorResponse>


    @GET("v2.4/notification/notification.get")
    suspend fun getNotification(
        @Query("page") page: Int? = null,
    ): NetworkResponse<BaseResponse<NotificationResponseDTO, Any>, ErrorResponse>


    @GET("v2.4/record/receive.off")
    suspend fun ngungTiepNhanHoSo(
        @Query("off") off: Boolean? = null,
    ): NetworkResponse<UserProfileResponse, ErrorResponse>


    @POST("v2.4/calculate/estimation.construction.live")
    suspend fun estimationConstruction(
        @Body reqest: EstimationConstructionRequest?,
    ): NetworkResponse<BaseResponse<EstimationConstructionResponse, Any>, ErrorResponse>

}