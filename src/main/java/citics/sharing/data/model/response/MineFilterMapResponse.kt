package citics.sharing.data.model.response

/**
 * Created by ChinhQT on 22/11/2022.
 */
data class MineFilterMapResponse(
    val status: Boolean? = null, val data: List<Data>? = null
) {
    data class Data(
        val id: String? = null,
        val loai_tai_san: String? = null,
        val kinh_do: Double? = null,
        val vi_do: Double? = null,
        val ten_du_an: String? = null,
        val ma_du_an: String? = null,
        val my_asset_id: String? = null,
        val dia_chi1: String? = null,
        val dia_chi2: String? = null,
        val gia_tri_tham_dinh: Long? = 0,
        val hinh_anh: Document? = null
    )
}