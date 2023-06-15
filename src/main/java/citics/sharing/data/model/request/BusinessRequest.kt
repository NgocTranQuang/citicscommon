package citics.sharing.data.model.request

/**
 * Created by ChinhQT on 03/01/2023.
 */
data class BusinessRequest(
    val authorizationLetter: String? = null,
    val deputyName: String? = null,
    val deputyPosition: String? = null,
    val guideManName: String? = null,
    val guideManPhone: String? = null,
    val taxCode: String? = null
)