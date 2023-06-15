package citics.sharing.data.model.request

/**
 * Created by ChinhQT on 09/01/2023.
 */
data class ChangePassRequest(
    val confirm_new_password: String?, val current_password: String?, val new_password: String?
)