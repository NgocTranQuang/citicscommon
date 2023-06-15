package citics.sharing.customview.dfAssetData.nhadat.edit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import citics.sharing.data.model.response.tham_dinh.Properties
import citics.sharing.utils.LevelType
import com.sharing.databinding.LayoutDfNdViTriBinding

/**
 * Created by ChinhQT on 25/10/2022.
 */

class LayoutDFNDViTri @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutDfNdViTriBinding

    init {
        binding = LayoutDfNdViTriBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
//            val typedArray = context.obtainStyledAttributes(
//                it, R.styleable.MDSDDWithListLayout, 0, 0
//            )
//
//            try {
//                landAdapter = LandAdapter(context, mutableListOf())
//                binding.rvLandmdssdd.adapter = landAdapter
//                landAdapter?.onChange = {
//                    tinhTongDienTichDat()
//                }
//            } finally {
//                typedArray.recycle()
//            }
        }
    }

    fun setData(properties: Properties?, assetLevel: String?) {
        binding.run {
            dfAreaChooser.setText("${properties?.thanh_pho ?: ""} \n ${properties?.quan ?: ""} \n ${properties?.phuong ?: ""}")
            soToTF.setText(properties?.so_to)
            soThuaTF.setText(properties?.so_thua)
            dfSoNhaTF.setText(properties?.so_nha)
            dfTenDuongTF.setText(properties?.vi_tri_duong)
            viDoTF.setText(properties?.vi_do?.toString())
            kinhDoTF.setText(properties?.kinh_do?.toString())
            dfTtcmap.setText(properties?.tinh_trang)
            soToTF.disable()
            dfAreaChooser.disable()
            soThuaTF.disable()
            dfSoNhaTF.disable()
            dfTenDuongTF.disable()
            viDoTF.disable()
            kinhDoTF.disable()
            dfTtcmap.disable()
            handleUiByLevel(assetLevel)
        }
    }

    private fun handleUiByLevel(assetLevel: String?) {
        binding.run {
            when (assetLevel) {
                LevelType.TRA_CUU.type -> {
                    dfTtcmap.visibility = GONE
                }
                LevelType.LAM_TSSS_CHINH_SUA_GIA.type -> {
                    dfTtcmap.visibility = GONE
                }
                LevelType.THAM_DINH.type -> {

                }
                LevelType.MUA_BAN.type -> {

                }
                LevelType.TAI_SAN_SO_SANH.type -> {

                }
            }
        }
    }
}