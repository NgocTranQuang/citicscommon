package citics.sharing.data.model.response.hoahong

data class HoaHongResponse(val content: List<ContentHoaHongDTO>? = null, val totalPages: Int?)

data class ContentHoaHongDTO(
    val record_id: String? = null,
    val date: Long? = null,
    val status: StatusHoaHongDTO? = null,
    val hoa_hong_tham_dinh: Long? = null,
    val phi_di_lai: Long? = null,
    val phi_phat: Long? = null,
    val thu_nhap_ca_nhan: Long? = null,
    val hoa_hong_kha_dung: Long? = null,
    // Detail

    val vi_pham: String? = null,
    val mo_ta_vi_pham: String? = null,
    val thoi_gian_cam_ket: String? = null,
    val ngay_khao_sat: Long? = null
)

data class StatusHoaHongDTO(
    val key: String? = null,
    val title: String? = null,
    val color: String? = null
)