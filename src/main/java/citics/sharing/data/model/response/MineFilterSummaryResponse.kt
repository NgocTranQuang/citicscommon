package citics.sharing.data.model.response

/**
 * Created by ChinhQT on 22/11/2022.
 */
data class MineFilterSummaryResponse(
    val status: Boolean, val data: Data? = null
) {
    data class Data(
        val all: Int = 0, val tham_dinh: Int = 0, val dang_ban: Int = 0, val da_ban: Int = 0, val chua_phan_loai: Int = 0
    )

    companion object {
        const val KEY_ALL = "all"
        const val KEY_THAM_DINH = "tham_dinh"
        const val KEY_DANG_BAN = "dang_ban"
        const val KEY_DA_BAN = "da_ban"
        const val KEY_CHUA_PHAN_LOAI = "chua_phan_loai"
    }
}