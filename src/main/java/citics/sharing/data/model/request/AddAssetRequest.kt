package citics.sharing.data.model.request

/**
 * Created by ChinhQT on 07/11/2022.
 */
data class AddAssetRequest<T>(
    val asset: T? = null,
    val id: String?=null,
    val loai_tai_san: String?,
    val my_asset_id: String? = null // phần này dành cho tài sản so sánh của tài sản của tui
)