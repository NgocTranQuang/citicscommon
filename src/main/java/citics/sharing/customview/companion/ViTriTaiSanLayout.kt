package citics.sharing.customview.companion

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import citics.sharing.data.model.response.tham_dinh.Properties
import com.citics.cbank.databinding.LayoutViTriNhatDatBinding

/**
 * Created by ChinhQT on 25/10/2022.
 */
class ViTriTaiSanLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutViTriNhatDatBinding

    init {
        binding = LayoutViTriNhatDatBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
//            val typedArray = context.obtainStyledAttributes(
//                it, R.styleable.ThongTinGiaLayout, 0, 0
//            )

            try {
            } finally {
//                typedArray.recycle()
            }
        }

    }

    @SuppressLint("SetTextI18n")
    fun setValue(
        propties: Properties?
    ) {
        if (propties == null) {
            return
        }
        binding.vToSo.setValue(propties.so_to)
        binding.vThuaSo.setValue(propties.so_thua)
        binding.vToaDoGoogle.setValue(propties.toa_do)
    }

}