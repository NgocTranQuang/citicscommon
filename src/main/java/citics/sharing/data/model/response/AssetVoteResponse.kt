package citics.sharing.data.model.response

/**
 * Created by ChinhQT on 23/11/2022.
 */
data class AssetVoteResponse(
    val status: Boolean, val data: Data? = null
) {
    data class Data(val asset_id: String? = null, val is_voted: Boolean? = null)
}