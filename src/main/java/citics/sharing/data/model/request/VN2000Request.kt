package citics.sharing.data.model.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 01/11/2022.
 */
@Parcelize
data class VN2000Request(val city_code: String, val coordinates: List<XY>) : Parcelable {
    @Parcelize
    data class XY(var x: Double, var y: Double) : Parcelable
}