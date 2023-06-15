package citics.sharing.data.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 12/02/2023.
 */
@Parcelize
data class CongTrinh(
    @Json(name = "id") var id: String? = null,
    @Json(name = "loai_cong_trinh") var loaiCongTrinh: String? = null,
    @Json(name = "label") var label: String? = null,
    @Json(name = "tang") var so_tang: Int? = null,
    @Json(name = "ket_cau_nha") var ket_cau_nha: String? = null,
    @Json(name = "tang_ham") var tangHam: Int? = null,
    @Json(name = "tang_tren") var tangTren: Int? = null,
    @Json(name = "dien_tich_san_xay_dung_tang_ham") var dien_tich_san_xay_dung_tang_ham: Double? = null,
    @Json(name = "dien_tich_san_xay_dung_tang_ham_phqh") var dien_tich_san_xay_dung_tang_ham_phqh: Double? = null,
    @Json(name = "dien_tich_san_xay_dung_tang_ham_vpqh") var dien_tich_san_xay_dung_tang_ham_vpqh: Double? = null,
    @Json(name = "dien_tich_san_xay_dung_tang_ham_incomplete") var dien_tich_san_xay_dung_tang_ham_incomplete: Double? = null,
    @Json(name = "dien_tich_san_xay_dung_cac_tang_tren") var dien_tich_san_xay_dung_cac_tang_tren: Double? = null,
    @Json(name = "dien_tich_san_xay_dung_cac_tang_tren_phqh") var dien_tich_san_xay_dung_cac_tang_tren_phqh: Double? = null,
    @Json(name = "dien_tich_san_xay_dung_cac_tang_tren_vpqh") var dien_tich_san_xay_dung_cac_tang_tren_vpqh: Double? = null,
    @Json(name = "dien_tich_san_xay_dung_cac_tang_tren_incomplete") var dien_tich_san_xay_dung_cac_tang_tren_incomplete: Double? = null,
    @Json(name = "thanh_tien_gia_tri_cong_trinh_phqh") var thanh_tien_gia_tri_cong_trinh_phqh: Long? = null,
    @Json(name = "thanh_tien_gia_tri_cong_trinh") var thanh_tien_gia_tri_cong_trinh: Long? = null,
    @Json(name = "thanh_tien_gia_tri_cong_trinh_incomplete") var thanh_tien_gia_tri_cong_trinh_incomplete: Long? = null,
    @Json(name = "thanh_tien_gia_tri_cong_trinh_vpqh") var thanh_tien_gia_tri_cong_trinh_vpqh: Long? = null,
    @Json(name = "dien_tich_san") var dien_tich_san: Float? = null,
    @Json(name = "cap_nha") var cap_nha: String? = null,
    @Json(name = "nien_han") var nien_han: String? = null,
    @Json(name = "mo_ta_cong_trinh") var mo_ta_cong_trinh: String? = null,
    @Json(name = "nam_xay_dung") var nam_xay_dung: Int? = null,
    @Json(name = "nam_sua_chua") var nam_sua_chua: Int? = null,
    @Json(name = "co_so_ha_tang_ky_thuat") var co_so_ha_tang_ky_thuat: List<String>? = null,
    @Json(name = "so_sanh_thuc_te_co_so_ha_tang_ky_thuat") var so_sanh_thuc_te_co_so_ha_tang_ky_thuat: String? = null,
    @Json(name = "so_sanh_thuc_te_co_so_ha_tang_ky_thuat_khac") var so_sanh_thuc_te_co_so_ha_tang_ky_thuat_khac: String? = null,
    @Json(name = "don_gia_cong_trinh") var don_gia_cong_trinh: Float? = null,
    @Json(name = "gia_tri_cong_trinh") var gia_tri_cong_trinh: Float? = null,
    @Json(name = "so_wc") var so_wc: Int? = null,
    @Json(name = "so_phong_bep") var so_phong_bep: Int? = null,
    @Json(name = "so_phong_ngu") var so_phong_ngu: Int? = null,
    @Json(name = "so_phong_tho") var so_phong_tho: Int? = null,
    @Json(name = "so_phong_khach") var so_phong_khach: Int? = null,
    @Json(name = "thoi_diem_tham_dinh") var thoi_diem_tham_dinh: Long? = null,
    @Json(name = "ty_le_clcl_cong_trinh") var ty_le_clcl_cong_trinh: Float? = null,
    @Json(name = "extra_rooms") var extra_rooms: MutableList<ExtraRooms>? = null
) : Parcelable {

    @Parcelize
    data class ExtraRooms(
        @Json(name = "title") var title: String? = null,
        @Json(name = "so_phong") var so_phong: Int? = null,
    ) : Parcelable

}
