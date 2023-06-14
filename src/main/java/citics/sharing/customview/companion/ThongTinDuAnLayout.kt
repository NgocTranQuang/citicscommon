package citics.sharing.customview.companion

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import citics.sharing.data.model.response.tham_dinh.Properties
import com.citics.cbank.databinding.LayoutThongTinDuAnBinding
import citics.sharing.extension.setHtml

/**
 * Created by ChinhQT on 25/10/2022.
 */
class ThongTinDuAnLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutThongTinDuAnBinding

    init {
        binding = LayoutThongTinDuAnBinding.inflate(LayoutInflater.from(context), this)
    }

    @SuppressLint("SetTextI18n")
    fun setValue(data: Properties?) {
        if (data == null) {
            return
        }
        binding.run {
            val desc = data.mo_ta
            if (TextUtils.isEmpty(desc)) {
                tvMoTaDuAn.visibility = View.GONE
            } else {
                tvMoTaDuAn.setHtml(desc)
            }
            vDuAn.setValue(data.ten_du_an)
            vDiaChi.setValue(data.dia_chi)
            vToaDoGoogle.setValue(data.toa_do)
            vChuDauTu.setValue(data.chu_dau_tu)
            vWebsiteDuAn.setValue(data.link)
        }
    }
}

