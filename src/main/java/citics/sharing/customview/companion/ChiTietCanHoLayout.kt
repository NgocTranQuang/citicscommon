package citics.sharing.customview.companion

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import citics.sharing.data.model.response.tham_dinh.Properties
import citics.sharing.extension.toShow
import com.sharing.databinding.LayoutChiTietCanHoBinding

/**
 * Created by ChinhQT on 25/10/2022.
 */
class ChiTietCanHoLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutChiTietCanHoBinding
    var onClickEditDetail: (() -> Unit)? = null

    init {
        binding = LayoutChiTietCanHoBinding.inflate(LayoutInflater.from(context), this)
        binding.vEditCanHo.setOnClickListener {
            onClickEditDetail?.invoke()
        }
    }

    @SuppressLint("SetTextI18n")
    fun setValue(
        propties: Properties?
    ) {
        if (propties == null) {
            return
        }

        binding.run {
            vLoaiHinh.setValue(propties.loai_can_ho)
            vDienTichTimTuong.setValue(propties.dien_tich_tim_tuong.toShow())
            vDienTichThongThuy.setValue(propties.dien_tich_thong_thuy.toShow())
            vThap.setValue(propties.thap)
            vTang.setValue(propties.tang)
            vHuongNha.setValue(propties.huong)
            vHuongCanhQuan.setValue(propties.canh_quan)
            vPhongNgu.setValue(propties.so_phong_ngu?.toString())
            vPhongVeSinh.setValue(propties.so_wc?.toString())
            vPhongKhach.setValue(propties.so_phong_khach)
            vPhongBep.setValue(propties.so_phong_bep)
        }
    }

}

