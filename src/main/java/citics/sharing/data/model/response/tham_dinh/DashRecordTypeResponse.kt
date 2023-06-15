package citics.sharing.data.model.response.tham_dinh

data class DashRecordTypeResponse(
    val status: Boolean? = null, val data: Data? = null
) {
    data class Data(
        val value: Long? = null,
        val tang_truong: Float? = null,
        val nha_dat: NhaDat? = null,
        val can_ho: CanHo? = null
    )

    data class NhaDat(
        val value: Long? = null,
        val tile: Float? = null,
        val tang_truong: Float? = null,
        val ti_le_tre: Float? = null
    )

    data class CanHo(
        val value: Long? = null,
        val tile: Float? = null,
        val tang_truong: Float? = null,
        val ti_le_tre: Float? = null
    )
}