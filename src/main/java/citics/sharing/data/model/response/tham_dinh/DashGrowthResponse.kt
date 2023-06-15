package citics.sharing.data.model.response.tham_dinh

data class DashGrowthResponse(
    val status: Boolean? = null, val data: List<Data>? = null
) {
    data class Data(
        val values: List<DashRecordResponse.Data>? = null,
        val index: Int? = null,
        val timing: String? = null
    )
}