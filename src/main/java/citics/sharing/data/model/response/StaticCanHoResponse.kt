package citics.sharing.data.model.response

/**
 * Created by ChinhQT on 15/11/2022.
 */

data class StaticCanHoResponse(val status: Boolean = false, val data: Data? = null) {
    data class Data(
        val duAn: String? = null,
        val tang: Int? = null,
        val thap: List<String>? = null,
        val dienTich: Int? = null,
        val soPhongWc: Int? = null,
        val soPhongNgu: Int? = null,
        val loGia: List<String>? = null,
        val banCong: List<String>? = null,
        val huongCua: List<String>? = null,
        val loaiCanHo: List<String>? = null,
        val yeuToKhac: List<String>? = null,
        val viTriCanHo: List<String>? = null,
        val viewCanhQuan: List<String>? = null,
        val danhMucNoiThat: List<DanhMucNoiThat>? = null,
        val photo_category: List<String>? = null,
        val tinhTrangPhapLy: List<String>? = null,
        val tinh_trang_giao_dich: List<String>? = null,
        val chatLuongNoiThat: List<String>? = null,
        var photo_categories: Map<String, List<String>>? = null
    )

    data class DanhMucNoiThat(
        val dienTichCanHo: Int? = null,
        val donGiaNoiThat: Float? = null,
        val giaTriNoiThat: Float? = null,
        val chatLuongNoiThat: String? = null
    )
}