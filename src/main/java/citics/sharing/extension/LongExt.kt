package citics.sharing.extension

import android.icu.text.NumberFormat
import citics.sharing.utils.Utils
import java.math.BigDecimal
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

fun Long?.toCurrency(): String {
    return try {
        toShow() + " đ"
    } catch (e: Exception) {
        "0 đ"
    }
}

fun Long?.toCurrencyPerM2(): String {
    return try {
        toShow() + " đ/m2"
    } catch (e: Exception) {
        "0 đ/m2"
    }
}

fun Long?.toShow(): String {
    return try {
        this?.let {
            val bd = BigDecimal(it)
            val long = bd.longValueExact()
            Utils.formatNumberWithoutUnit(long)
        } ?: kotlin.run {
            ""
        }

    } catch (e: Exception) {
        ""
    }
}

fun Long?.toShowName(): String {
    return try {
        Utils.formatNumberWithoutUnit(this)
    } catch (e: Exception) {
        ""
    }
}

fun Long?.formatNumber(): String {
    val number = this.toString()
    return if (number.isNotEmpty()) {
        val temporary = number.toDouble()
        NumberFormat.getNumberInstance(Locale.US).format(temporary)
    } else {
        "-"
    }
}

fun Long?.convertMilisToHms(): String {
    this?.let { ms ->
        val milliseconds = abs(ms)
        val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
        val minutes =
            TimeUnit.MILLISECONDS.toMinutes(milliseconds) - TimeUnit.HOURS.toMinutes(hours)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) - TimeUnit.MINUTES.toSeconds(
            TimeUnit.MILLISECONDS.toMinutes(milliseconds)
        )

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    } ?: kotlin.run {
        return "-"
    }
}
