package citics.sharing.data.model.response

/**
 * Created by ChinhQT on 11/11/2022.
 */
data class OptionsSuggestionResponse(
    val status: Boolean = false, val message: String? = null, val data: List<Data>? = null
) {
    data class Data(
        val name: String?, val items: List<Data>?, val sub_text: String? = null
    )
}