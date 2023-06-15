package citics.sharing.data.model.response

import com.citics.valuation.data.datasource.local.CityEntity


/**
 * Created by ChinhQT on 26/10/2022.
 */
data class AreaResponse(
    val status: Boolean = false, val data: List<CityEntity>? = null
)