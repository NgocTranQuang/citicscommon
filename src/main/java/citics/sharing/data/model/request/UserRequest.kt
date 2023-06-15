package citics.sharing.data.model.request

/**
 * Created by ChinhQT on 25/09/2022.
 */
data class UserRequest(val encrypted_auth_phase: String, val remember_me: Boolean = false)