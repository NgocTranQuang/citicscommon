package citics.sharing.data.model.response.tham_dinh

data class DashRecordResponse(
    val status: Boolean? = null, val data: List<Data>? = null
) {
    data class Data(
        val value: Long? = null,
        val total: Long? = null,
        val tile: Float? = null,
        val dash: Dash? = null
    )

    data class Dash(
        val key: String? = null,
        val title: String? = null
    )
}

data class NPin(val n_pin: Int? = null)