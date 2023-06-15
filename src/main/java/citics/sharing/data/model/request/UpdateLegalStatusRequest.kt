package citics.sharing.data.model.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 28/12/2022.
 */
@Parcelize
data class UpdateLegalStatusRequest(
    val asset_owner: String? = null,
    val customer_owner: Boolean? = null,
    val status: String? = null,
    val note: String? = null,
    val id: String? = null,
    val relation_owner: String? = null
) : Parcelable {
}