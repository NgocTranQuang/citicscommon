package citics.sharing.data.model.response

import citics.sharing.data.datasource.local.entity.DocumentContractEntity
import citics.sharing.data.datasource.local.entity.JobEntity
import citics.sharing.data.datasource.local.entity.StatusCashEntity
import citics.sharing.data.model.response.tham_dinh.StatusBarResponse
import com.squareup.moshi.Json

/**
 * Created by ChinhQT on 17/10/2022.
 */
data class TemplateResponse(val status: Boolean = false, val data: DataResponse? = null) {
    data class DataResponse(
        @Json(name = "document_contract_category") val documentContractCategory: List<DocumentContractEntity>,
        @Json(name = "status_cash_1st") val statusCash1ST: List<StatusCashEntity>,
        @Json(name = "reasons") val reasons: Map<String, List<KeyValueDTO>>,
        @Json(name = "ghi_chu_khac") val ghiChuKhac: List<String>? = null,
        @Json(name = "hien_trang_su_dung") val hien_trang_su_dung: List<KeyTitleDTO>,
        @Json(name = "trang_thai_hoa_hong") val trang_thai_hoa_hong: List<StatusBarResponse>,
        @Json(name = "so_sanh_thuc_te_yeu_to_khac") val so_sanh_thuc_te_yeu_to_khac: List<KeyTitleDTO>,
        @Json(name = "so_sanh_thuc_te_co_so_ha_tang_ky_thuat") val so_sanh_thuc_te_co_so_ha_tang_ky_thuat: List<KeyTitleDTO>,
        @Json(name = "receive-record-off-days-limit") val receive_record_off_days_limit: Int? = null,
        @Json(name = "co_so_ha_tang_ky_thuat") val co_so_ha_tang_ky_thuat: List<KeyTitleDTO>,
        @Json(name = "jobs") val jobs: List<JobEntity>,
        @Json(name = "status_landing") val status_landing: List<StatusLanding>? = null,
        val thong_tin_giao_dich: List<String>? = null,
        @Json(name = "group_type") val group_type: List<GroupType>? = null,
        @Json(name = "huong_tiep_giap") val huong_tiep_giap: List<List<String>>? = null,
        @Json(name = "dien_tich_rank") val dien_tich_rank: List<KeyTitleDTO>? = null,
        @Json(name = "money_rank") val money_rank: List<KeyTitleDTO>? = null,
        @Json(name = "loai_tai_san_agency") val loai_tai_san_agency: List<KeyTitleDTO>? = null,
        @Json(name = "asset_filter_label") val asset_filter_label: List<KeyTitleDTO>? = null,
        @Json(name = "asset_label") val asset_label: List<KeyTitleDTO>? = null,
        @Json(name = "cap_do_thong_tin_agency") val cap_do_thong_tin_agency: List<CapDoThongTin>? = null,
    )

    data class StatusLanding(val title: String, val value: Int, val key: String)

    data class GroupType(val key: String, val title: String)

    data class CapDoThongTin(
        @Json(name = "key") val key: String,
        @Json(name = "title") val title: String,
        @Json(name = "position") val position: Int? = 0,
        @Json(name = "upgradable") val upgradable: Boolean? = false,
        @Json(name = "nha_pho") val nha_pho_flags: ThongTinFlag? = null,
        @Json(name = "can_ho") val can_ho_flags: ThongTinFlag? = null
    )

    data class ThongTinFlag(
        @Json(name = "thong_tin_giao_dich") val thong_tin_giao_dich: Boolean? = false,
        @Json(name = "hinh_anh") val hinh_anh: Boolean? = false,
        @Json(name = "phap_ly") val phap_ly: Boolean? = false
    )

}