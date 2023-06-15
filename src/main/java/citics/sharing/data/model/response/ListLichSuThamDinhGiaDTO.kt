package citics.sharing.data.model.response

import com.squareup.moshi.Json

data class ListLichSuThamDinhGiaDTO(
    @Json(name = "number") val number: Long? = null,
    @Json(name = "date") val priceDate: Long? = null,
    @Json(name = "cvalue_record") val recordId: String,
    @Json(name = "diff_rate") val diffRate: Float? = null,
    @Json(name = "id") val id: String? = null,
    @Json(name = "unit_price") val unitPrice: Long? = null,
    @Json(name = "type") val type: String? = null,
    @Json(name = "status") val status: String? = null,
    @Json(name = "customer") val customer: String? = null,
) {
}