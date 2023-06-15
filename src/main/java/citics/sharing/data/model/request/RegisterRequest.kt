package citics.sharing.data.model.request

/**
 * Created by ChinhQT on 25/09/2022.
 */
data class RegisterRequest(
    val name: String,
    val phone: String,
    val email: String,
    val city_code: String,
    val job: String,
    val worked: Boolean = false,
    val attachments: List<String>
)