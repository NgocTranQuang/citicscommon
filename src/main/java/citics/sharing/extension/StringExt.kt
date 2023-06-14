package citics.sharing.extension

import android.os.Build
import android.text.Editable
import android.util.Patterns
import com.google.android.gms.maps.model.LatLng
import java.util.*

fun String?.toM(): String {
    return "$this m"
}

fun String.emailValidator(): Boolean {
    if (this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()) {
        return true
    }
    return false
}

fun String.phoneValidator(): Boolean {
    if (this.isNotEmpty() && Patterns.PHONE.matcher(this).matches()) {
        return true
    }
    return false
}

fun getDeviceName(): String {
    val manufacturer = Build.MANUFACTURER
    val model = Build.MODEL
    val deviceName = if (model.lowercase(Locale.getDefault())
            .startsWith(manufacturer.lowercase(Locale.getDefault()))
    ) {
        capitalize(model)
    } else {
        capitalize(manufacturer) + " " + model
    }
    return deviceName
}


private fun capitalize(s: String?): String {
    if (s == null || s.length == 0) {
        return ""
    }
    val first = s[0]
    return if (Character.isUpperCase(first)) {
        s
    } else {
        first.uppercaseChar().toString() + s.substring(1)
    }
}

fun Editable?.toStringOrNull(): String? {
    if (this == null) {
        return null
    } else {
        return this.toString()
    }
}

fun List<List<Double>>?.toListLatLng(): List<LatLng>? {
    return this?.map { LatLng(it[1], it[0]) }
}