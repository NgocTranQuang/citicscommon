package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import citics.sharing.data.model.response.AssetDetailData
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class ThamDinhResponse(
    val content: List<RecordResponse.RecordData>? = listOf(),
    val first: Boolean? = false,
    val last: Boolean? = false,
    val number: Int? = 0,
    val numberOfElements: Int? = 0,
    val pageable: Pageable? = null,
    val size: Int? = 0,
    val sort: SortX? = null,
    val totalElements: Int? = 0,
    val totalPages: Int? = 0
) : Parcelable

@Parcelize
data class Content(
    val active: RecordResponse.RecordData.RecordActive? = null,
    val address_1: String? = "",
    val address_2: String? = "",
    val assessed: AssetDetailData? = null,
    val badge_guarantee: Int? = 0,
    val badge_violation: Int? = 0,
    val contract_id: String? = "",
    val created_date: Long? = 0,
    val customer: RecordResponse.RecordData.Customer? = null,
    val execution_time: RecordResponse.RecordData.ExecutionTime? = null,
    val execution_times: List<RecordResponse.RecordData.ExecutionTimes>? = null,
    val extra: Extra? = Extra(),
    val id: String? = "",
    val is_guarantee: Boolean? = false,
    val last_modified_date: Long? = 0,
    val legals: RecordResponse.RecordData.Legals? = null,
    val owners: List<RecordResponse.RecordData.RecordOwner>? = null,
    val partner_template: RecordResponse.RecordData.RecordOwner.Group? = null,
    val preliminaried: Boolean? = false,
    val price: RecordResponse.RecordData.RecordPrice? = null,
    val property_type: RecordResponse.RecordData.PropertyType? = null,
    val report: @RawValue List<Any>? = listOf(),
    val safe_guarantee: Boolean? = false,
    val shipper_address: RecordResponse.RecordData.ShipperAddress? = null,
    val status: RecordResponse.RecordData.RecordStatus? = null,
    val survey_time: SurveyTime? = SurveyTime(),
    val tasks: List<Task>? = listOf(),
    val type: String? = ""
) : Parcelable

@Parcelize
data class Pageable(
    val offset: Int? = 0,
    val pageNumber: Int? = 0,
    val pageSize: Int? = 0,
    val paged: Boolean? = false,
    val sort: SortX? = SortX(),
    val unpaged: Boolean? = false
) : Parcelable

@Parcelize
data class SortX(
    val sorted: Boolean? = null, val unsorted: Boolean? = null
) : Parcelable

@Parcelize
data class Extra(
    val current_rate: Double? = null,
    val month: String? = null,
    val price_asset: Long? = null,
    val price_current: Long? = null,
    val rate: String? = null
) : Parcelable