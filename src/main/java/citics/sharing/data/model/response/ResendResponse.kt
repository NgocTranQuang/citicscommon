package citics.sharing.data.model.response

/**
 * Created by ChinhQT on 24/10/2022.
 */
data class ResendResponse(
    val status: Boolean = false, val message: String?, val data: Data
) {
    data class Data(
        val token: String?,
        val expired: Long?,
        val mobile: Boolean,
    )
}