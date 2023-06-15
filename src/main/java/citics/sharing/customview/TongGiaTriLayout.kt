package citics.sharing.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import citics.sharing.adapter.VeThuaDatAdapter
import citics.sharing.data.model.response.DetailAdjustmentRates
import citics.sharing.extension.toCurrency
import com.sharing.R
import com.sharing.databinding.LayoutTongGiaTriBinding

/**
 * Created by ChinhQT on 25/10/2022.
 */
class TongGiaTriLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private var veThuaDatAdapter: VeThuaDatAdapter? = null
    private var veCongTrinhAdapter: DienTichCongTrinhAdapter? = null
    private var binding: LayoutTongGiaTriBinding
    private var typeSelected: TongGiaTriType = TongGiaTriType.GIA_TRI
        set(value) {
            field = value
            veThuaDatAdapter?.setTypeData(field)
            veCongTrinhAdapter?.setTypeData(field)
        }

    init {
        binding = LayoutTongGiaTriBinding.inflate(LayoutInflater.from(context), this)


        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.TongGiaTriLayout, 0, 0
            )

            try {
                val type = typedArray.getInt(R.styleable.TongGiaTriLayout_tgt_type_show, 0)
                if (type == 0) {
                    // Gia tri
                    typeSelected = TongGiaTriType.GIA_TRI
                } else if (type == 1) {
                    // Don gia
                    typeSelected = TongGiaTriType.DON_GIA
                } else if (type == 2) {
                    //Dien tich
                    typeSelected = TongGiaTriType.DIEN_TICH
                } else if (type == 3) {
                    typeSelected = TongGiaTriType.ALL
                }

            } finally {
                typedArray.recycle()
            }
        }
//
//        binding.btnGiaTri.setOnClickListener {
//            typeSelected = TongGiaTriType.GIA_TRI
//
//        }
//        binding.btnDonGia.setOnClickListener {
//            typeSelected = TongGiaTriType.DON_GIA
//        }
//        binding.btnDienTich.setOnClickListener {
//            typeSelected = TongGiaTriType.DIEN_TICH
//        }
//        binding.btnGiaTri.performClick()
    }

    @SuppressLint("SetTextI18n")
    fun setValue(
        data: DetailAdjustmentRates?
    ) {
        if (data == null) {
            return
        }
        binding.run {
            tvValueVeThuaDat.text = data.tong_gia_tri_dat_tham_dinh.toCurrency()
            tvValueCongTrinh.text = data.tong_gia_tri_cong_trinh.toCurrency()
            veThuaDatAdapter = VeThuaDatAdapter(
                context,
                data.chi_tiet_don_gia_dat?.toMutableList() ?: mutableListOf(),
                typeSelected
            )
            binding.rvVeThuaDat.adapter = veThuaDatAdapter
            veCongTrinhAdapter = DienTichCongTrinhAdapter(
                context,
                data.chi_tiet_gia_cong_trinh?.toMutableList() ?: mutableListOf(), typeSelected
            )
            binding.rvCongTrinhXayDung.adapter = veCongTrinhAdapter
        }
    }

    enum class TongGiaTriType {
        GIA_TRI, DON_GIA, DIEN_TICH, ALL
    }

}