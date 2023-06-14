package citics.sharing.customview.companion

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import citics.sharing.data.model.response.tham_dinh.Properties
import com.citics.cbank.R
import com.citics.cbank.databinding.LayoutTraCuuChiTietDuAnBinding
import citics.sharing.extension.toShow
import citics.sharing.extension.toShowTime
import com.citics.valuation.utils.UIHelper

/**
 * Created by ChinhQT on 25/10/2022.
 */
class ChiTietDuAnLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutTraCuuChiTietDuAnBinding

    init {
        binding = LayoutTraCuuChiTietDuAnBinding.inflate(LayoutInflater.from(context), this)
    }

    fun setValue(data: Properties?) {
        if (data == null) {
            return
        }
        binding.run {
            vDienTichDat.setValue(data.dien_tich_khuon_vien.toShow())
            vMatDoXayDung.setValue(data.mat_do_xay_dung)
            vSoThap.setValue(data.so_block)
            vTongSoCanHo.setValue(data.so_can)
            vNamXayDung.setValue(UIHelper.uiShowYear(data.nam_khoi_cong))
            vNamHoanThanh.setValue(UIHelper.uiShowYear(data.nam_hoan_cong))
            vSoTang.setValue(data.so_tang)
            vCoCauCanHo.setValue(context.getString(
                R.string.so_dien_tich_in_range,
                data.dien_tich_nho_nhat.toString(), data.dien_tich_lon_nhat.toString()))
            vLoaiHinh.setValue(data.loai_du_an)
            vThoiDiemMoBan.setValue(data.thoi_diem_mo_ban?.toShowTime())
            vHienTrang.setValue(data.tinh_trang)
            vPhapLy.setValue(data.tinh_trang_phap_ly)
        }
    }


}