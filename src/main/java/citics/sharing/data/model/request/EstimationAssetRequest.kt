package citics.sharing.data.model.request

import citics.sharing.data.model.response.DetailUsingPurpose
import citics.sharing.data.model.response.tham_dinh.Properties

/**
 * Created by ChinhQT on 07/11/2022.
 */
data class EstimationAssetRequest(
    val id: String,
    val loai_tai_san: String,
    val properties: Properties?,
    val using_purpose: List<DetailUsingPurpose>? = null
)