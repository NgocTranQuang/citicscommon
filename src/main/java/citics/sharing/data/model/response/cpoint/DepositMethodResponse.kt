package citics.sharing.data.model.response.cpoint

data class DepositMethodResponse(
    val type: String? = null,
    val condition: String? = null,
    val deposit_cp: List<String>? = null,
    val sub_text: String? = null
)
