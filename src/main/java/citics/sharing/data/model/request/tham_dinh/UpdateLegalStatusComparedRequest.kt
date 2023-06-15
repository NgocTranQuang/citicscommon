package citics.sharing.data.model.request.tham_dinh

/**
 * Created by ChinhQT on 10/01/2023.
 */
data class UpdateLegalStatusComparedRequest(
    val id: String, val status: String, val note: String, val compared_id: String? = null
)