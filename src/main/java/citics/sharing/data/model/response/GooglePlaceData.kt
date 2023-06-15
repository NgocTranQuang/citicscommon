package citics.sharing.data.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 31/10/2022.
 */
@Parcelize
data class GooglePlaceData(
    val status: Boolean, val data: Data? = null
) : Parcelable {

    @Parcelize
    data class Data(
        val addressComponents: List<AddressComponents>,
        val adrAddress: String? = null,
        val formattedAddress: String? = null,
        val formattedPhoneNumber: String? = null,
        val geometry: Geometry,
        val icon: String? = null,
        val internationalPhoneNumber: String? = null,
        val name: String? = null,
        val openingHours: String? = null,
        val placeId: String? = null,
        val scope: String? = null,
        val plusCode: PlusCode? = null,
        val permanentlyClosed: Boolean? = false,
        val vicinity: String? = null,
        val website: String? = null,
        val maPhuong: String? = null,
        val phuong: String? = null,
        val maQuan: String? = null,
        val quan: String? = null,
        val maThanhPho: String? = null,
        val thanhPho: String? = null
    ) : Parcelable

    @Parcelize
    data class PlusCode(val globalCode: String? = null, val compoundCode: String? = null) :
        Parcelable

    @Parcelize
    data class AddressComponents(
        val longName: String, val shortName: String, val types: List<String>
    ) : Parcelable

    @Parcelize
    data class Geometry(
        val bounds: BoundsData? = null,
        val location: LatLng? = null,
        val locationType: String? = null,
        val viewport: BoundsData? = null
    ) : Parcelable

    @Parcelize
    data class BoundsData(val northeast: LatLng, val southwest: LatLng) : Parcelable

    @Parcelize
    data class LatLng(val lat: Double, val lng: Double) : Parcelable
}