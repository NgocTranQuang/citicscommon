package citics.sharing.data.model.others

import android.os.Parcelable
import citics.sharing.data.model.response.AssetDetailResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class GoingToMyAsset(
    val loaiTaiSan: String,
    val myAssetId: String? = null,
    val assetDetailResponse: AssetDetailResponse? = null
) : Parcelable