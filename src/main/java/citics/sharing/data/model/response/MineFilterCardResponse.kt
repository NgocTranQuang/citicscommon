package citics.sharing.data.model.response

/**
 * Created by ChinhQT on 22/11/2022.
 */
data class MineFilterCardResponse(
    val status: Boolean, val data: Data? = null
) {
    data class Data(
        val content: List<Content>? = null,
        val pageable: Pageable? = null,
        val totalElements: Int? = null,
        val totalPages: Int? = null,
        val last: Boolean? = null,
        val first: Boolean? = null
    )

    data class Content(
        val id: String,
        val propertyId: String,
        val level: String? = null,
        val label: String? = null,
        val label_title: String? = null,
        val loai_tai_san: String,
        val kinh_do: Double? = null,
        val vi_do: Double? = null,
        val dia_chi: String? = null,
        val dia_chi1: String? = null,
        val dia_chi2: String? = null,
        val dien_tich: String? = null,
        val gia_tri_tham_dinh: Long?,
        val so_phong_ngu: String? = null,
        val so_wc: String? = null,
        val danh_sach_hinh_anh: List<SliderImage>? = null,
        val my_asset_id: String? = null,
        val apartment_sub_text: String? = null
    )

    data class SliderImage(
        val photo_url: String? = null,
        val photo_title: String? = null,
        val photo_extension: String? = null,
        val content_type: String? = null
    )

    data class Pageable(val pageSize: Int? = null, val pageNumber: Int? = null)
}