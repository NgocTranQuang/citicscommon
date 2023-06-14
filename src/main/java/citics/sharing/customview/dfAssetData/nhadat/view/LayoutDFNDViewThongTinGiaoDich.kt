//package com.citics.cagent.views.dfAssetData.nhadat.view
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.widget.LinearLayout
//import androidx.navigation.findNavController
//import androidx.navigation.fragment.findNavController
//import com.citics.cagent.R
//import citics.sharing.data.model.response.DetailAdjustmentRates
//import citics.sharing.data.model.response.DetailUsingPurpose
//import citics.sharing.data.model.response.LandDTO
//import citics.sharing.data.model.response.tham_dinh.Properties
//import com.citics.cagent.databinding.LayoutDfNdHientrangthuadatBinding
//import com.citics.cagent.databinding.LayoutDfNdViewChitietthuadatBinding
//import com.citics.cagent.databinding.LayoutDfNdViewThongtingiaodichBinding
//import com.citics.cagent.ui.fragments.adapters.tham_dinh.DonGiaDatOAdapter
//import com.citics.cagent.ui.fragments.adapters.tham_dinh.DonGiaDatOViewAdapter
//import com.citics.cagent.ui.fragments.adapters.tham_dinh.MucDichSuDungDatAdapter
//import com.citics.cagent.ui.fragments.thamdinh.chitiet.ChiTietDienTichThuaDatFragment
//import com.citics.cagent.utilities.DEFAULT
//import com.citics.cagent.utilities.LevelType
//import com.citics.cagent.utilities.extensions.setListChip
//import com.citics.cagent.utilities.extensions.toCurrency
//import com.citics.cagent.utilities.extensions.toDateTime
//import com.citics.cagent.utilities.extensions.toShow
//
//class LayoutDFNDViewThongTinGiaoDich @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
//) : LinearLayout(context, attrs, defStyle, defStyleRes) {
//    private var properties: Properties? = null
//    private var binding: LayoutDfNdViewThongtingiaodichBinding
//
//
//    init {
//        binding = LayoutDfNdViewThongtingiaodichBinding.inflate(LayoutInflater.from(context), this)
//
//        attrs?.let {
//            onClick()
//        }
//
//    }
//
//    private fun onClick() {
//
//    }
//
//    fun setData(
//        properties: Properties?,
//        adjustmentRates: DetailAdjustmentRates?,
//        assetLevel: String?
//    ) {
//        this.properties = properties
//        binding.run {
//            properties?.let {
//                tinhtranggiaodich.setValue(properties?.tinh_trang_giao_dich)
//                thoidiemgiaodich.setValue(properties?.thoi_diem_giao_dich?.toDateTime("dd 'tháng' MM, yyyy"))
//                giaraoban.setValue(properties?.gia_rao_ban?.toCurrency())
//                giathuongluong.setValue(properties?.gia_thuong_luong?.toCurrency())
//
//                try {
//                    properties?.gia_thuong_luong?.let { gtl ->
//                        properties.gia_rao_ban?.let { grb ->
//                            if (grb != 0L) {
//                                val percent = gtl * 1.0F / grb * 100
//                                tylethuongluongTxt.setValue(percent.toString())
//                            } else {
//                                tylethuongluongTxt.setValue(DEFAULT)
//                            }
//                        } ?: kotlin.run { tylethuongluongTxt.setValue(DEFAULT) }
//                    } ?: kotlin.run { tylethuongluongTxt.setValue(DEFAULT) }
//                } catch (error: java.lang.Exception) {
//                    tylethuongluongTxt.setValue(DEFAULT)
//                }
//
//                gtqsddTxt.setValue(adjustmentRates?.gia_tri_tham_dinh.toShow())
//                dotincayTxt.setValue(properties.do_tin_cay ?: DEFAULT)
//                dgdtcTxt.setValue(properties.dien_giai_do_tin_cay ?: DEFAULT)
//                duongdantaisanTF.setValue(properties.link ?: DEFAULT)
//                nguoilienheTF.setValue(properties.ten_nguoi_lien_he ?: DEFAULT)
//                sodienthoaiTF.setValue(properties.sdt_lien_he ?: DEFAULT)
//            }
//            handleUiByLevel(assetLevel)
//        }
//    }
//
//    private fun handleUiByLevel(assetLevel: String?) {
//        binding.run {
//            when (assetLevel) {
//                LevelType.TRA_CUU.type -> {
//
//                }
//                LevelType.LAM_TSSS_CHINH_SUA_GIA.type -> {
//                    lnNguon.visibility = GONE
//                    duongdantaisanTF.visibility = GONE
//                    tylethuongluongLayout.visibility = GONE
//                    gtqsddTxt.visibility = GONE
//                    lnThongTinKhac.visibility = GONE
//                }
//
//                LevelType.MUA_BAN.type -> {
//                    lnNguon.visibility = GONE
//                    duongdantaisanTF.visibility = GONE
//                    tylethuongluongLayout.visibility = GONE
//                    gtqsddTxt.visibility = GONE
//                    lnThongTinKhac.visibility = GONE
//                }
//                LevelType.THAM_DINH.type -> {
//
//                }
//                LevelType.TAI_SAN_SO_SANH.type -> {
//
//                }
//            }
//        }
//    }
//}