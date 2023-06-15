package citics.sharing.data.model.others

import android.os.Parcelable
import com.citics.valuation.data.datasource.local.CityEntity
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 26/10/2022.
 */
@Parcelize
data class SearchAreaModel(
    val key: String, var status: Int = 0, val message: String
) : Parcelable {
    companion object {
        fun fromAreaResponse(areaData: CityEntity) =
            SearchAreaModel(areaData.code, 0, areaData.name)
    }
}