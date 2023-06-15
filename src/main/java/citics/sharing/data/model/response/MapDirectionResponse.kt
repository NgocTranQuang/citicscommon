package citics.sharing.data.model.response

import com.squareup.moshi.Json

/**
 * Created by ChinhQT on 31/10/2022.
 */
data class MapDirectionResponse(
    val status: String, val routes: List<Routes>
) {

    data class Routes(
        val legs: List<Leg>, @Json(name = "overview_polyline") val polyline: Polyline
    ) {

        data class Leg(
            val distance: KeyVal, val duration: KeyVal, val steps: List<Step>
        ) {

            data class Step(
                val distance: MapDirection.Routes.Leg.KeyVal,
                val duration: MapDirection.Routes.Leg.KeyVal,
                @Json(name = "start_location") val startLocation: LatLng,
                @Json(name = "end_location") val endLocation: LatLng,
                @Json(name = "html_instructions") val description: String,
                @Json(name = "polyline") val polyline: MapDirection.Routes.Polyline,
                @Json(name = "travel_mode") val travelMode: String
            ) {

                data class LatLng(
                    val lat: Double, val lng: Double
                )
            }

            data class KeyVal(
                val text: String, val value: Any
            )

        }

        data class Polyline(
            val points: String
        )

    }

}