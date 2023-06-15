package citics.sharing.data.model.others

/**
 * Created by ChinhQT on 20/10/2022.
 */
data class ImageModel(
    val clientID: String = "",
    val clientPath: String = "",
    val downloadStatus: Int =0,
    val serverID: String =""
)

enum class ImageStatus {
    NONE, DOWNLOADED, FAIL
}