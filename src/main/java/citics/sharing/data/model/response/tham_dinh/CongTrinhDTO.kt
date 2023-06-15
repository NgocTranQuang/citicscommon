package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class CongTrinhDTO(
    @Json(name = "id") var id: String? = null,
    @Json(name = "loai_cong_trinh") var loaiCongTrinh: String? = null,
    @Json(name = "tang") var so_tang: String? = null,
    @Json(name = "ket_cau_nha") val ket_cau_nha: String? = null,
    @Json(name = "tang_ham") val tangHam: Int? = null,
    @Json(name = "tang_tren") val tangTren: Int? = null,
    @Json(name = "dien_tich_san_xay_dung_tang_ham") val dien_tich_san_xay_dung_tang_ham: Double? = null,
    @Json(name = "dien_tich_san_xay_dung_tang_ham_phqh") val dien_tich_san_xay_dung_tang_ham_phqh: Float? = null,
    @Json(name = "dien_tich_san_xay_dung_tang_ham_vpqh") val dien_tich_san_xay_dung_tang_ham_vpqh: Double? = null,
    @Json(name = "dien_tich_san_xay_dung_tang_ham_incomplete") val dien_tich_san_xay_dung_tang_ham_incomplete: Double? = null,
    @Json(name = "dien_tich_san_xay_dung_cac_tang_tren") val dien_tich_san_xay_dung_cac_tang_tren: Double? = null,
    @Json(name = "dien_tich_san_xay_dung_cac_tang_tren_phqh") val dien_tich_san_xay_dung_cac_tang_tren_phqh: Double? = null,
    @Json(name = "dien_tich_san_xay_dung_cac_tang_ham_phqh") val dien_tich_san_xay_dung_cac_tang_ham_phqh: Double? = null,
    @Json(name = "dien_tich_san_xay_dung_cac_tang_tren_vpqh") val dien_tich_san_xay_dung_cac_tang_tren_vpqh: Double? = null,
    @Json(name = "dien_tich_san_xay_dung_cac_tang_tren_incomplete") val dien_tich_san_xay_dung_cac_tang_tren_incomplete: Double? = null,
    @Json(name = "thanh_tien_gia_tri_cong_trinh_phqh") val thanh_tien_gia_tri_cong_trinh_phqh: Long? = null,
    @Json(name = "thanh_tien_gia_tri_cong_trinh") val thanh_tien_gia_tri_cong_trinh: Long? = null,
    @Json(name = "thanh_tien_gia_tri_cong_trinh_incomplete") val thanh_tien_gia_tri_cong_trinh_incomplete: Long? = null,
    @Json(name = "thanh_tien_gia_tri_cong_trinh_vpqh") val thanh_tien_gia_tri_cong_trinh_vpqh: Long? = null,
    @Json(name = "dien_tich_san") var dien_tich_san: Float? = null,
    @Json(name = "cap_nha") val cap_nha: String? = null,
    @Json(name = "nien_han") val nien_han: String? = null,
    @Json(name = "mo_ta_cong_trinh") val mo_ta_cong_trinh: String? = null,
    @Json(name = "nam_xay_dung") var nam_xay_dung: String? = null,
    @Json(name = "nam_sua_chua") var nam_sua_chua: String? = null,
    @Json(name = "co_so_ha_tang_ky_thuat") val co_so_ha_tang_ky_thuat: List<String>? = null,
    @Json(name = "so_sanh_thuc_te_co_so_ha_tang_ky_thuat") val so_sanh_thuc_te_co_so_ha_tang_ky_thuat: String? = null,
    @Json(name = "so_sanh_thuc_te_co_so_ha_tang_ky_thuat_khac") val so_sanh_thuc_te_co_so_ha_tang_ky_thuat_khac: String? = null,
    @Json(name = "don_gia_cong_trinh") val don_gia_cong_trinh: Float? = null,
    @Json(name = "gia_tri_cong_trinh") val gia_tri_cong_trinh: Float? = null,
    @Json(name = "so_wc") val so_wc: Int? = null,
    @Json(name = "so_phong_bep") val so_phong_bep: Int? = null,
    @Json(name = "so_phong_ngu") val so_phong_ngu: Int? = null,
    @Json(name = "so_phong_khach") val so_phong_khach: Int? = null,
    @Json(name = "thoi_diem_tham_dinh") val thoi_diem_tham_dinh: Long? = null,
    @Json(name = "ty_le_clcl_cong_trinh") val ty_le_clcl_cong_trinh: Float? = null,
) : Parcelable