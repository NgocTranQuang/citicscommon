package citics.sharing.data.model.request

import citics.sharing.data.model.response.AssetDetailData
import citics.sharing.data.model.response.DetailAdjustmentRates
import citics.sharing.data.model.response.DetailUsingPurpose
import citics.sharing.data.model.response.tham_dinh.Properties

/**
 * Created by ChinhQT on 15/11/2022.
 */
data class MineUpsertRequest(
    val id: String? = "",
    val loai_tai_san: String? = "",
    val tinh_trang: String? = "",
    val my_asset_id: String? = "",
    val properties: Properties? = null,
    val adjustment_rates: DetailAdjustmentRates? = null,
    val using_purpose: List<DetailUsingPurpose>? = null,
    val compared_assets: List<AssetDetailData>? = null,
    val level: String? = null
)