package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 04/01/2023.
 */
@Parcelize
data class TDComparedAssetMapResponse(val status: Boolean? = null, val data: Data? = null) :
    Parcelable {

    @Parcelize
    data class Data(
        val content: List<ContentData>? = null,
        val totalElements: Int? = null,
        val totalPages: Int? = null,
        val last: Boolean? = null,
        val first: Boolean? = null
    ) : Parcelable

    @Parcelize
    data class ContentData(
        val properties: Properties, val property_type: RecordResponse.RecordData.PropertyType
    ) : Parcelable

    @Parcelize
    data class Properties(
        val id: String? = null,
        val confidency: Confidency? = null,
        val dia_chi1: String? = null,
        val dia_chi2: String? = null,
        val vi_do: Double? = null,
        val kinh_do: Double? = null,
        val toa_do: String? = null,
        val dien_tich_dc_cong_nhan: Float? = null,
        val mat_tien_tiep_giap: String? = null,
        val do_rong_hem_truoc_nha: String? = null,
        val muc_dich_su_dung: String? = null,
        val ngay_cap_nhat_gia: Long? = null,
        val tong_gia_tri_tai_san: Long? = null,
        val don_gia: Long? = null,
        val gia_rao_ban: Long? = null,
        val gia_thuong_luong: Long? = null,
        val allow_addition: Boolean? = null,
        val dien_tich_tim_tuong: String? = null,
        val thap: String? = null,
        val tang: String? = null,
        val dien_tich_thong_thuy: String? = null,
    ) : Parcelable

    @Parcelize
    data class Confidency(
        val key: String? = null, val title: String? = null, val description: String? = null
    ) : Parcelable
}