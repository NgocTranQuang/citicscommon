package citics.sharing.data.model.response

data class RecordStatusResponse(
    val status: Boolean? = null, val data: List<RecordStatus>? = null
) {
    data class RecordStatus(
        val status: Int,
        val title: String,
        val key: String,
        val color: String,
    )
}