package citics.sharing.data.model.request

import citics.sharing.data.model.response.AssetDetailData

/**
 * Created by ChinhQT on 09/01/2023.
 */
data class TSSSMyAssetRequest(
    val loai_tai_san: String?, val my_asset_id: String?, val asset: AssetDetailData? = null
)