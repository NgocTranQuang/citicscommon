package citics.sharing.data.model.request

/**
 * Created by ChinhQT on 24/10/2022.
 */
data class NewPassRequest(
    val new_password: String, val confirm_new_password: String, val token: String
)