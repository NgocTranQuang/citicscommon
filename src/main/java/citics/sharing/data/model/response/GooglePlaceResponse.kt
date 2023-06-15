package citics.sharing.data.model.response

/**
 * Created by ChinhQT on 31/10/2022.
 */
data class GooglePlaceResponse(
    val status: Boolean, val data: GooglePlaceData.Data? = null
)