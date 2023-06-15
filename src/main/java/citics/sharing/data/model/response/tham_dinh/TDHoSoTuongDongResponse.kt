package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 04/01/2023.
 */
@Parcelize
data class TDHoSoTuongDongResponse(val status: Boolean? = null, val data: Data? = null) :
    Parcelable {

    @Parcelize
    data class Data(
        val content: List<Properties>? = null,
        val totalElements: Int? = null,
        val totalPages: Int? = null,
        val last: Boolean? = null,
        val first: Boolean? = null
    ) : Parcelable

    @Parcelize
    data class Properties(
        val id: String? = null,
        val dia_chi: String? = null,
        val dia_chi1: String? = null,
        val dia_chi2: String? = null,
        val vi_do: Double? = null,
        val kinh_do: Double? = null,
        val toa_do: String? = null,
        val loai_tai_san: String? = null,
        val dien_tich_dc_cong_nhan: Float? = null,
        val mat_tien_tiep_giap: String? = null,
        val do_rong_hem_truoc_nha: String? = null,
        val muc_dich_su_dung: String? = null,
        val ngay_cap_nhat_gia: Long? = null,
        val tong_gia_tri_tai_san: Long? = null,
        val tong_gia_tri_tham_dinh: Long? = null,
        val my_asset_id: String? = null,
        val apartment_sub_text: String? = null,
        val so_phong_ngu: String? = null,
        val so_wc: String? = null,
        val gia_tri_tham_dinh: Long? = null,
        val type: String? = null,
        val don_gia: Long? = null,
        val active: Active? = null,
        val gia_rao_ban: Long? = null,
        val gia_thuong_luong: Long? = null,
        val allow_addition: Boolean? = null,
        val partner_template: PartnerTeamplate? = null
    ) : Parcelable

    @Parcelize
    data class Active(
        val id: Int? = null, val title: String? = null
    ) : Parcelable

    @Parcelize
    data class PartnerTeamplate(
        val group_id: Int? = null, val group_name: String? = null
    ) : Parcelable
}