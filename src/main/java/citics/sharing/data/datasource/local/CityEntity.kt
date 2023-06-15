package com.citics.valuation.data.datasource.local

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 12/10/2022.
 */
@Parcelize
data class CityEntity(
    val code: String = "",
    val name: String = ""
) : Parcelable