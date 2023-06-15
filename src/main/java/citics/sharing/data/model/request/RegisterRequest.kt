package citics.sharing.data.model.request

/**
 * Created by ChinhQT on 25/09/2022.
 */
data class RegisterRequest(
    val name: String,
    val phone: String,
    val email: String,
    val bank: String,
    val bank_branch: String,
    val bank_address: String
)