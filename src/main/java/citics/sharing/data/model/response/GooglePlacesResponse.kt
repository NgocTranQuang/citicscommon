package citics.sharing.data.model.response

/**
 * Created by ChinhQT on 31/10/2022.
 */
data class GooglePlacesResponse(
    val status: Boolean,
    val data: List<GooglePlaceData.Data>? = null
)