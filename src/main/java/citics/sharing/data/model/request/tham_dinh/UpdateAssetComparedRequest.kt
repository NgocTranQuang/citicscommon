package citics.sharing.data.model.request.tham_dinh

import citics.sharing.data.model.response.DetailAdjustmentRates
import citics.sharing.data.model.response.DetailUsingPurpose
import citics.sharing.data.model.response.tham_dinh.Properties

/**
 * Created by ChinhQT on 10/01/2023.
 */
data class UpdateAssetComparedRequest(
    val adjustment_rates: DetailAdjustmentRates? = null,
    val using_purpose: List<DetailUsingPurpose>? = null,
    val properties: Properties? = null,
    val asset_id: String? = null,
    val record_id: String? = null,
    val loai_tai_san: String? = null,
    val level: String? = null
)