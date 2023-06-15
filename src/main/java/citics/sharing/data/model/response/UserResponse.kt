package citics.sharing.data.model.response

/**
 * Created by ChinhQT on 25/09/2022.
 */
data class UserResponse(
    val status: Boolean = false, val message: String? = "", val data: Data? = null
) {
    data class Data(
        val business: Boolean = false,
        val token: String = "",
        val code: Int = 0,
        val firebase_token: String? = "",
        val authorities: List<String> = emptyList(),
        val user_id: String = "",
        val is_hoi_so: Boolean = false,
        val on_behalf_of_bank: String? = "",
        val is_business: Boolean = false,
        var need_otp_confirm: Boolean = false
    )
}