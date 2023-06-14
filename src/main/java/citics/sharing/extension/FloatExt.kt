package citics.sharing.extension

import com.citics.valuation.utils.Utils
import java.text.DecimalFormat


fun Float?.toDienTich(): String {
    return toKichThuoc("m2")
}

fun Float?.toKichThuoc(donvi: String): String {
    return (if (this == null) "" else (Utils.formatNumberWithoutUnit((this).toString()) + " $donvi"))
}

fun Float?.toShow(): String {
    return try {
        val number = this?.toRound()
        if (number == null) {
            ""
        } else {
            this?.let {
                Utils.formatNumberWithoutUnit(number)
            } ?: kotlin.run {
                ""
            }

        }
    } catch (e: Exception) {
        ""
    }
}


fun Double?.toShow(): String {
    return try {
        val number = this?.toRound()
        if (number == null) {
            ""
        } else
            Utils.formatNumberWithoutUnit(number)
    } catch (e: Exception) {
        ""
    }
}


fun Float.toRound(): String {
    val value = DecimalFormat("#.##").format(this)
    return value.replace(",", ".")
}

fun Double.toRound(): String {
    val value = DecimalFormat("#.##").format(this)
    return value.replace(",", ".")
}