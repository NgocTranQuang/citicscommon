package citics.sharing.data.model.response

import android.os.Parcelable
import citics.sharing.data.model.response.base.BaseResponse
import com.citics.valuation.data.model.response.ExtraData
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 26/10/2022.
 */
@Parcelize
class AssetDetailResponse : BaseResponse<AssetDetailData, ExtraData>(), Parcelable