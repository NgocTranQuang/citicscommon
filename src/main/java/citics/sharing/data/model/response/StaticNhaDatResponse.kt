package citics.sharing.data.model.response

/**
 * Created by ChinhQT on 15/11/2022.
 */

data class StaticNhaDatResponse(val status: Boolean = false, val data: Data? = null) {
    data class Data(
        val danhMucLand: DanhMucLand? = null,
        val danhMucCongTrinh: List<DanhMucCongTrinh>? = null,
        val loaiCongTrinh: List<String>? = null,
        val houseLevel: List<String>? = null,
        val photo_category: List<String>? = null,
        val tinh_trang_giao_dich: List<String>? = null
    )

    data class DanhMucLand(
        val mucDichSuDungDat: List<String>? = null,
        val khuVuc: List<String>? = null,
        val viTriDuong: List<String>? = null,
        val matTienTiepGiap: List<String>? = null,
        val doRongDuong: Double? = null,
        val ketCauDuong: List<String>? = null,
        val ketNoiGiaoThong: List<String>? = null,
        val hinhDang: List<String>? = null,
        val huongChinh: List<String>? = null,
        val chieuDai: String? = null,
        val chieuRong: String? = null,
        val thoiHanSuDungDat: String? = null,
        val tinhTrangPhapLy: List<String>? = null,
        val yeuToKhac: Map<String, List<String>>? = null
    )

    data class DanhMucCongTrinh(
        val basementScale: String? = null,
        val describe: String? = null,
        val houseKind: String? = null,
        val houseLevel: String? = null,
        val lifeTime: String? = null,
        val numOfFloor: String? = null,
        val prototype: String? = null,
        val donGiaXayDungVT1: Long? = null,
        val donGiaXayDungVT2: Long? = null,
        val donGiaXayDungVT3: Long? = null
    )
}