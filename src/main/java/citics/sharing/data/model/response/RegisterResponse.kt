package citics.sharing.data.model.response

/**
 * Created by ChinhQT on 25/09/2022.
 */
data class RegisterResponse(
    val status: Boolean = false, val data: RegisterDataResponse? = null
) {
    data class RegisterDataResponse(
        val id: String,
        val name: String,
        val phone: String,
        val email: String,
        val bank: String,
        val bankBranch: String,
        val bankAddress: String,
        val worked: Boolean,
        var city_code_violation: Boolean? = null
    )
}