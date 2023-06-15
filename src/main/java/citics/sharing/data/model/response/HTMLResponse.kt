package citics.sharing.data.model.response

data class HTMLResponse(
    val status: Boolean? = null, val data: Data? = null
) {
    data class Data(
        val url: String? = null
    )
}