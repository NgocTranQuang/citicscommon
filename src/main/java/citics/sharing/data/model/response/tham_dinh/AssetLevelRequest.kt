package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AssetLevelRequest(
    val level: String?, val my_asset_id: String?
) : Parcelable