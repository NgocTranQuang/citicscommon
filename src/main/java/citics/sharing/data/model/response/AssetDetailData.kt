package citics.sharing.data.model.response

import android.os.Parcelable
import citics.sharing.data.model.response.tham_dinh.Properties
import citics.sharing.data.model.response.tham_dinh.RecordResponse
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class AssetDetailData(
    @Json(name = "id") var id: String? = null,
    @Json(name = "level") var level: String? = null,
    @Json(name = "level_title") val level_title: String? = null,
    @Json(name = "label") var label: String? = null,
    @Json(name = "label_title") val label_title: String? = null,
    @Json(name = "loai_tai_san") var loai_tai_san: String? = null,
    @Json(name = "need_boundary") val need_boundary: Boolean = true,
    @Json(name = "sp_rounded") val sp_rounded: Boolean = true,
    @Json(name = "tinh_trang") var tinh_trang: String? = "",
    @Json(name = "my_asset_id") var my_asset_id: String? = null,
    @Json(name = "properties") var properties: Properties? = null,
    @Json(name = "adjustment_rates") var adjustmentRates: DetailAdjustmentRates? = null,
    @Json(name = "properties_preliminary") val propertiesPreliminary: RecordResponse.RecordData.PropertiesPreliminary? = null,
    @Json(name = "properties_warehouse") val propertiesWarehouse: RecordResponse.RecordData.PropertiesPreliminary? = null,
    @Json(name = "adjustment_rates_citics") val adjustment_rates_citics: DetailAdjustmentRates? = null,
    @Json(name = "using_purpose") var usingPurpose: MutableList<DetailUsingPurpose>? = null,
    @Json(name = "compared_assets") var comparedAssets: MutableList<AssetDetailData>? = null,
    @Json(name = "list_base_pricing") var listBasePricing: List<Map<String, String>>? = null,
    @Json(name = "vi_pham_tsss") val vi_pham_tsss: Boolean? = false,
    @Json(name = "yeu_to_khac") var yeuToKhac: List<String>? = null
) : Parcelable