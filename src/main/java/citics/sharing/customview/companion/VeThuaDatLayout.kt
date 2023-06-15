package citics.sharing.customview.companion

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import citics.sharing.data.model.response.Document
import citics.sharing.data.model.response.tham_dinh.Properties
import citics.sharing.extension.open
import citics.sharing.extension.setListChip
import citics.sharing.extension.showBalloonPopup
import citics.sharing.extension.toShow
import com.sharing.R
import com.sharing.databinding.LayoutVeThuaDatBinding


/**
 * Created by ChinhQT on 25/10/2022.
 */
class VeThuaDatLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutVeThuaDatBinding

    init {
        binding = LayoutVeThuaDatBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.VeThuaDatLayout, 0, 0
            )

            try {

            } finally {
                typedArray.recycle()
            }
        }

    }

    fun onClickEditThongTin(onClick: View.OnClickListener) {
        binding.tvEditThongTinThuaDat.setOnClickListener(onClick)
    }

    @SuppressLint("SetTextI18n")
    fun setValue(data: Properties?) {
        if (data == null) {
            return
        }
        val items = mutableListOf<String>()
        data.lands?.forEach {
            it.name?.let { name -> items.add(name) }
        }
        binding.lnMucDichSuDung.setListChip(items)
        binding.vTongDienTich.setValue(data.dien_tich_dc_cong_nhan.toShow())
        binding.vChieuDai.setValue(data.chieu_dai.toShow())
        binding.vChieuRong.setValue(data.chieu_rong.toShow())
        binding.vHinhDang.setValue(data.hinh_dang)
        binding.vHuongNha.setValue(data.huong)
        binding.vMatTienTiepGiap.setValue(data.mat_tien_tiep_giap)
        binding.vKhoangCachRaDuongChinh.setValue(data.khoang_cach_ra_duong_chinh.toShow())
        binding.vDoRong.setValue(data.do_rong_hem_truoc_nha.toShow())
        binding.vKetCauDuong.setValue(data.ket_cau_duong)
        binding.vDoanNhoNhat.setValue(data.do_rong_hem_nho_nhat.toShow())
        binding.vQuyHoachDuKien.setValue(data.quy_hoach_du_kien.toShow())
        binding.lnYeuToKhac.setListChip(data.yeu_to_khac?.toMutableList())
        binding.vds2.text = context.getString(
            R.string.quy_hoach_su_dung_dat,
            data.qhsdd?.loai_dat ?: "",
            data.qhsdd?.thoi_ki_quy_hoach ?: ""
        )
        if (data.qhsdd == null) {
            binding.vds2.visibility = View.GONE
        } else {
            binding.vds2.visibility = View.VISIBLE
        }
        binding.vds2.setOnClickListener {
            it.showBalloonPopup("text", resLayout = R.layout.layout_popup_qhsdd)?.let {
                it.getContentView().findViewById<TextView>(R.id.tvLoaiDat).text =
                    data.qhsdd?.loai_dat
                it.getContentView().findViewById<TextView>(R.id.tvThoiKy).text =
                    context.getString(R.string.tkqh) + " " + (data.qhsdd?.thoi_ki_quy_hoach ?: "")
                it.getContentView().findViewById<TextView>(R.id.tvQuyetDinhSo).text =
                    data.qhsdd?.so_quyet_dinh

                if (!TextUtils.isEmpty(data.qhsdd?.quyet_dinh_url)) {
                    it.getContentView().findViewById<TextView>(R.id.tvQuyetDinhSo)
                        ?.setOnClickListener {
                            Document(url = data.qhsdd?.quyet_dinh_url).open(context)
                        }
                } else {
                    it.getContentView().findViewById<TextView>(R.id.tvQuyetDinhSo)
                        .setTextColor(context.getColor(R.color.color_divider))
                }

            }
        }
        binding.lnChiTietDienTichDat.setOnClickListener {
//            context.startActivity(
//                ChiTietDienTichThuaDatActivity.newIntent(
//                    context,
//                    data.dien_tich_dc_cong_nhan.toShow(),
//                    data.lands
//                )
//            )

        }
    }
}