package citics.sharing.utils

import android.content.Context
import android.text.TextUtils
import com.citics.cbank.R
import citics.sharing.extension.toDate

object UIHelper {
    fun getNgayCapNhatGia(context : Context, date : Long?) : String{
        if(date == null){
            return context.getString(R.string.khong_ro_thoi_diem_cap_nhat)
        }else return context.getString(R.string.ngay_cap_nhat_gia_x, date.toDate())
    }

    fun getNgayCapNhatGiaDonGian(context : Context, date : Long?) : String{
        if(date == null){
            return context.getString(R.string.unknown)
        }else return date.toDate()
    }

    fun uiShowYear(year : Int?) : String{
        if(year == null || year <= 0) return ""
        else return year.toString()
    }

    /**
     * @param defaultWhenUnavailable Value when myInt is NULL
     * @param defaultWhen0 Value when myInt is 0
     */
    fun uiShowIntAsStringOrDefault(myInt: Int?, defaultWhenUnavailable: String, defaultWhen0: String): String{
        return myInt?.let {
            if(it == 0) defaultWhen0
            else it.toString()
        } ?: kotlin.run {
            defaultWhenUnavailable
        }
    }

    /**
     * @param defaultWhenUnavailable Value when myString is NULL
     * @param defaultWhenEmpty Value when myString is empty
     */
    fun uiShowStringAsStringOrDefault(myString: String?, defaultWhenUnavailable: String, defaultWhenEmpty: String): String{
        return myString?.let {
            if(TextUtils.isEmpty(it)) defaultWhenEmpty
            else it
        } ?: kotlin.run {
            defaultWhenUnavailable
        }
    }

}