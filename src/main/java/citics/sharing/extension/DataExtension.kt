package citics.sharing.extension

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import citics.sharing.activity.wv.WebViewActivity
import citics.sharing.data.model.others.ChooserItem
import citics.sharing.data.model.others.SelectorItem
import citics.sharing.data.model.response.Document
import citics.sharing.data.model.response.LandDTO
import citics.sharing.utils.FileUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sharing.R
import es.dmoral.toasty.Toasty
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun List<String?>?.toListChooser(): MutableList<ChooserItem> {
    return this?.mapIndexed { _, s -> ChooserItem(s ?: "", s ?: "") }?.toMutableList()
        ?: mutableListOf()
}

fun List<String?>?.toListSelector(): MutableList<SelectorItem> {
    return this?.mapIndexed { index, s -> SelectorItem(index.toString(), s ?: "") }?.toMutableList()
        ?: mutableListOf()
}

fun Any.toJson(): String? {
    return Gson().toJson(this)
}

inline fun <reified T> String.toObject(): T? {
    return Gson().fromJson(this, T::class.java)
}

inline fun <reified T> String?.toMutableList(): MutableList<T> {
    val myType = object : TypeToken<List<T>>() {}.type
    val result = Gson().fromJson<List<T>>(this, myType)
    return result?.toMutableList() ?: mutableListOf()
}

fun Any.toFormat(): String {
    if (this is Long) {
        return this.toShow()
    }
    if (this is Float) {
        return this.toShow()
    }
    if (this is Double) {
        return this.toShow()
    }
    return toString()
}


inline fun <reified T : Any> T?.copyByJson(): T? {
    if (this == null)
        return null
    return this.toJson()?.toObject<T>()
}

@SuppressLint("SimpleDateFormat")
fun Long.toShowTime(format: String = "dd MMM yyyy"): String {
    val cal: Calendar = Calendar.getInstance()
    val tz: TimeZone = cal.timeZone
    val dateFormat = SimpleDateFormat(format, Locale("vi", "VN"))
    dateFormat.timeZone = tz
    return dateFormat.format(this)
}

@SuppressLint("SimpleDateFormat")
fun String.toShowTime(format: String = "dd MMM yyyy"): String {
    val longValue = toLongOrNull()
    if (longValue == null) {
        // => String này có kiểu yyyy-MM-dd'T'HH:mm:ss'Z'
        val simFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        try {
            val date = simFormat.parse(this)
            return date?.time?.toShowTime(format) ?: ""
        } catch (e: ParseException) {
            e.printStackTrace()
            return ""
        }
    } else {
        return longValue.toShowTime(format) ?: ""
    }
}

@SuppressLint("SimpleDateFormat")
fun String.toShowDate(format: String = "'ngày' dd 'tháng' MM, yyyy"): String {
    return toShowTime(format)
}

fun Long.toDateTime(format: String = "HH:mm. 'Thứ' u, dd 'tháng' MM, yyyy"): String {
    val temporary = Date(this)
    val pattern = SimpleDateFormat(format, Locale.US)
    return pattern.format(temporary)
}

fun Long.toDate(format: String = "'ngày' dd 'tháng' MM, yyyy"): String {
    val temporary = Date(this)
    val pattern = SimpleDateFormat(format, Locale.US)
    return pattern.format(temporary)
}

fun Uri.isDocument(context: Context): String? {
    val fileType = FileUtils.getFileType(context, this)
    if (fileType?.lowercase()?.contains("png") == true || fileType?.lowercase()
            ?.contains("jpeg") == true || fileType?.lowercase()?.contains("jpg") == true
    ) {
        return null
    }
    return fileType
}

fun String?.isDocument(): String? {
    if (this?.lowercase()?.contains("png") == true || this?.lowercase()
            ?.contains("jpeg") == true || this?.lowercase()?.contains("jpg") == true
    ) {
        return null
    }
    return this
}

fun MutableList<File>.toListMultiPart(
    context: Context
): MutableList<MultipartBody.Part> {

    val parts: MutableList<MultipartBody.Part> = mutableListOf()
    for (i in 0 until this.size) {
        parts.add(this[i].toPart(context))
    }
    return parts
}

fun File.toPart(context: Context, partName: String = "file"): MultipartBody.Part {
    val requestBody: RequestBody = asRequestBody(
        FileUtils.getIntentToOpenFile(
            context, Uri.fromFile(this)
        ).type?.toMediaTypeOrNull()
    )
    return MultipartBody.Part.createFormData(
        partName, name, requestBody
    )
}

@Suppress("CAST_NEVER_SUCCEEDS")
fun Document.open(context: Context) {
    url?.let {
        try {
            val uri = Uri.parse(it)
            context.startActivity(FileUtils.getIntentToOpenFile(context, uri))
        } catch (e: ActivityNotFoundException) {
            context.startActivity(WebViewActivity.newIntent(context, url = it))
        }
    } ?: run {
        Toasty.error(
            context,
            context.getString(R.string.url_must_not_be_null),
            Toast.LENGTH_SHORT,
            true
        ).show()
    }
}
//
//@Suppress("CAST_NEVER_SUCCEEDS")
//fun Document.open(fm: Context) {
//    url?.let {
//        try {
//            val uri = Uri.parse(it)
//            fm.startActivity(FileUtils.getIntentToOpenFile(fm, uri))
//        } catch (e: ActivityNotFoundException) {
//            fm.startActivity(WebviewActivity.newIntent(fm, url = it))
////            fm.findNavController().navigate(R.id.fragment_webView, WebViewFragment.getArgument(it))
//        }
//    } ?: run {
//        Toasty.error(
//            fm,
//            fm.getString(R.string.url_must_not_be_null),
//            Toast.LENGTH_SHORT,
//            true
//        ).show()
//    }
//}

fun Int.dp(context: Context): Float =
    context.resources.getDimension(this) / context.resources.displayMetrics.density

fun Long?.combineTimeAndDate(date: Long?): Long? {
    if (this == null || date == null) {
        return null
    }
    val calTime = Calendar.getInstance()
    calTime.timeInMillis = this
    val hour = calTime[Calendar.HOUR_OF_DAY]
    val minute = calTime[Calendar.MINUTE]

    val calDate = Calendar.getInstance()
    calDate.timeInMillis = date
    val year = calDate[Calendar.YEAR]
    val month = calDate[Calendar.MONTH]
    val dayOfMonth = calDate[Calendar.DAY_OF_MONTH]

    val calRequest = Calendar.getInstance()
    calRequest.set(Calendar.HOUR_OF_DAY, hour)
    calRequest.set(Calendar.MINUTE, minute)
    calRequest.set(Calendar.DAY_OF_MONTH, dayOfMonth)
    calRequest.set(Calendar.MONTH, month)
    calRequest.set(Calendar.YEAR, year)
    return calRequest.time.time
}

inline fun <reified T> Bundle.getData(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelable(key, T::class.java)
    } else {
        getParcelable(key)
    }
}

inline fun <reified T : Parcelable> Bundle.getListParcelable(key: String): MutableList<T>? {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        return getParcelableArray(key, T::class.java)?.toMutableList()
    } else {
        val result = get(key)
        if (result is Array<*>) {
            return (result as Array<T>).toMutableList()
        } else if (result is ArrayList<*>) {
            return (result as ArrayList<T>).toMutableList()
        }
        return null
    }
}

fun Bundle.getListString(key: String): MutableList<String>? {
    return getStringArrayList(key)?.toMutableList()
}
//
//// Hàm này dành cho hình ảnh căn hộ (merge 2 list group photo lại thành 1)
//fun List<GroupPhotos>?.combine(photo_categories: Map<String, List<String>>?): MutableList<GroupPhotos> {
//
//    val lstPhotoCate = photo_categories?.map {
//        mutableListOf(it.value.mapIndexed { index, s ->
//            GroupPhotos().apply {
//                title = s
//                if (index == 0) {
//                    section = it.key
//                }
//                isAvailable = false
//            }
//        })
//    }?.flatten()?.flatten()
//    return this?.toMutableList().combine(lstPhotoCate)
//}
//
//// Hàm này dành cho hình ảnh căn hộ (merge 2 list group photo lại thành 1)
//fun List<GroupPhotos>?.combine(lstPhotoCate: List<GroupPhotos>?): MutableList<GroupPhotos> {
//    val mapLagel: LinkedHashMap<String, GroupPhotos> = linkedMapOf()
//    lstPhotoCate?.forEach lit@{ groupStatic ->
//        val groupDTO = this?.firstOrNull { groupInAsset -> groupInAsset.title == groupStatic.title }
//            ?: GroupPhotos(title = groupStatic.title).apply {
//                section = groupStatic.section
//            }
//        groupDTO.isAvailable = true
//        groupDTO.section = groupStatic.section
//        mapLagel[groupStatic.title ?: ""] = groupDTO
//    }
//    this?.forEach { phapLy ->
//        phapLy.title?.let {
//            mapLagel[it] = phapLy
//        }
//    }
//    return mapLagel.values.toMutableList()
//}
//
//@JvmName("combineGroupPhotos")
//fun List<GroupPhotos>?.combine(lstPhotoCate: List<String>?): MutableList<GroupPhotos> {
//    val mapLagel: LinkedHashMap<String, GroupPhotos> = linkedMapOf()
//    val listLegalStatic = lstPhotoCate?.toMutableList()
//    listLegalStatic?.forEach lit@{ title ->
//        val phapLyDTO =
//            this?.firstOrNull { it.title == title } ?: GroupPhotos(title = title)
//        phapLyDTO.isAvailable = true
//        mapLagel[title] = phapLyDTO
//    }
//    this?.forEach { phapLy ->
//        phapLy.title?.let {
//            mapLagel[it] = phapLy
//        }
//    }
//    return mapLagel.values.toMutableList()
//}
//
fun MutableList<LandDTO>?.addListChooser(result: MutableList<SelectorItem>): MutableList<LandDTO> {
    val newList = result.map { selectorItem ->
        this?.firstOrNull { landItem ->
            selectorItem.name == landItem.name
        } ?: LandDTO(name = selectorItem.name, kind = selectorItem.id)
    }.toMutableList()
    this?.clear()
    this?.addAll(newList)
    return newList
}
