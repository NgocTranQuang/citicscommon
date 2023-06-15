package citics.sharing.data.model.response

/**
 * Created by ChinhQT on 26/10/2022.
 */
data class AreasSuggestionResponse(
    val status: Boolean = false, val data: List<Data>
) {
    data class Data(val description: String, val child: List<ChildItem>)
    data class ChildItem(val code: String, val value: String)
}