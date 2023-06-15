package citics.sharing.data.model.response

/**
 * Created by ChinhQT on 19/10/2022.
 */
data class UploaderResponse(
    val status: Boolean = false,
    val data: List<Document>,
    var clientID: String = ""
) {

}
