package citics.sharing.customview.companion

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import citics.sharing.customview.ThongTinLayout
import citics.sharing.data.model.response.tham_dinh.Properties
import com.sharing.R
import com.sharing.databinding.LayoutTienIchNoiKhuBinding

/**
 * Created by ChinhQT on 25/10/2022.
 */
class TienIchNoiKhuLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutTienIchNoiKhuBinding

    init {
        binding = LayoutTienIchNoiKhuBinding.inflate(LayoutInflater.from(context), this)
    }

    fun setValue(data: Properties?) {
        if (data == null) {
            return
        }
        data.tien_nghi?.forEach {
            val layout = ThongTinLayout(context)
            layout.setTitleAndImage(it, R.drawable.ic_check)
            binding.lnTienIch.addView(layout)
        }
    }

}

