package citics.sharing.utils

import android.content.Context
import com.sharing.R

enum class LoaiTaiSan(var type: Int, var typeName: String) {
    NHA_DAT(1, "nha_pho"), CAN_HO(2, "can_ho"), DAT_NEN(3, "dat_nen"), ALL(0, "all")
}

enum class RecordStatus(val type: String) {
    CHUA_XAC_THUC("chua_xac_thuc"), DANG_TIN_CAY("dang_tin_cay"), DA_KIEM_DUYET("da_kiem_duyet"), DA_XAC_THUC(
        "da_xac_thuc"
    ),
    DA_THAM_DINH("da_tham_dinh"), DA_SO_BO("da_so_bo")
}

enum class GiaTriType {
    GIA_TRI, DON_GIA, DIEN_TICH
}

enum class HoSoType {
    HOP_DONG_THAM_DINH
}

enum class Language(val language: String) {
    VIET_NAM("vn"), ENGLISH("en")
}

enum class UsingTimeType(val type: String) {
    LAU_DAI("Lâu dài"), CO_THOI_HAN("Có thời hạn")
}

enum class HinhThucSuDung(val title: Int) {
    CHUNG(title = R.string.text_hinh_thuc_su_dung_chung),
    RIENG(title = R.string.text_hinh_thuc_su_dung_rieng),
}

enum class PositionAssetType(val type: Int) {
    TSC_THAM_DINH(0), TSSS_THAM_DINH(1), TSSS_TRACUU(2), TSC_TSCT(3), TSSS_TSCT(4)
}

enum class DialogType {
    NORMAL, ERROR, CONFIRM, IDLE
}

enum class LevelType(val type: String, val position: Int) {
    TRA_CUU("tra_cuu", 1),// Tài sản tra cứu
    LAM_TSSS_CHINH_SUA_GIA("lam_tai_san_so_sanh_tdg", 5), // Tài sản so sánh tra cứu
    TAI_SAN_SO_SANH("lam_tai_san_so_sanh", 4), // Tài sản so sánh của tài sản thẩm định
    MUA_BAN("mua_ban", 3), // Tài sản mua bán
    CHINH_SUA_GIA("chinh_sua_gia", 2),  // Tài sản
    THAM_DINH("tham_dinh", 4);// Tài sản thẩm định

    companion object {
        fun fromKeyToCode(key: String): Int {
            when (key) {
                TAI_SAN_SO_SANH.type -> return TAI_SAN_SO_SANH.position
                MUA_BAN.type -> return MUA_BAN.position
                CHINH_SUA_GIA.type -> return CHINH_SUA_GIA.position
                TRA_CUU.type -> return TRA_CUU.position
                THAM_DINH.type -> return THAM_DINH.position
                LAM_TSSS_CHINH_SUA_GIA.type -> return LAM_TSSS_CHINH_SUA_GIA.position
                else -> return 0
            }
        }
    }
}

enum class LabelType(val type: String) {
    CHUA_BAN("chua_ban"), DANG_BAN("dang_ban"), DA_BAN("da_ban"), CHUA_THIET_LAP("khong_hien_thi");

    companion object {
        fun fromKeyToTitle(key: String, context: Context): String? {
            when (key) {
                CHUA_BAN.type -> return context.getString(R.string.chua_thiet_lap_trang_thai)
                DANG_BAN.type -> return context.getString(R.string.dang_ban)
                DA_BAN.type -> return context.getString(R.string.da_ban)
                CHUA_THIET_LAP.type -> return null
                else -> return null
            }
        }
    }

}

enum class CPType(val type: Int) {
    CP_NHAN(1), CP_CHI(2)
}

enum class Gender(val type: String, val nameGender: String) {
    NAM("0", "Nam"), NU("1", "Nữ"), KHAC("2", "Khác")
}