package citics.sharing.data.model.response

import com.google.android.gms.maps.model.LatLng

/**
 * Created by ChinhQT on 31/10/2022.
 */
data class MapDirection(
    val routes: List<Routes>
) {

    data class Routes(
        val legs: List<Leg>, val polyline: Polyline
    ) {

        data class Leg(
            val distance: KeyVal, val duration: KeyVal, val steps: List<Step>
        ) {

            data class Step(
                val distance: KeyVal,
                val duration: KeyVal,
                val startLocation: LatLng,
                val endLocation: LatLng,
                val description: String,
                val polyline: Polyline,
                val travelMode: String
            )

            data class KeyVal(
                val text: String, val value: Any
            )

        }

        data class Polyline(
            val points: String
        )

    }

}