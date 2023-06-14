//package com.citics.cagent.views.dfAssetData.canho.view
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.view.View
//import citics.sharing.data.model.response.tham_dinh.Properties
//import com.citics.cagent.databinding.LayoutDfChViewThongtinduanBinding
//import com.citics.cagent.utilities.LevelType
//import com.citics.cagent.utilities.UIHelper
//import com.citics.cagent.utilities.extensions.toShowTime
//import citics.sharing.customview.dfAssetData.nhadat.edit.BaseLayoutDF
//
//class LayoutDFCHViewThongTinDuAn @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
//) : BaseLayoutDF(context, attrs, defStyle, defStyleRes) {
//
//    private var binding: LayoutDfChViewThongtinduanBinding
//
//    init {
//        binding = LayoutDfChViewThongtinduanBinding.inflate(LayoutInflater.from(context), this)
//
//        attrs?.let {
//        }
//    }
//
//    fun setData(properties: Properties?, assetLevel: String?) {
//        binding.run {
//            vDienTichDat.setValue(properties?.dien_tich)
//            vMatDoXayDung.setValue(properties?.mat_do_xay_dung)
//            vSoThap.setValue(properties?.so_block)
//            vTongSoCanHo.setValue(properties?.so_can)
//            vNamXayDung.setValue(UIHelper.uiShowYear(properties?.nam_khoi_cong))
//            vNamHoanThanh.setValue(UIHelper.uiShowYear(properties?.nam_hoan_cong))
//            vSoTang.setValue(properties?.so_tang)
//            val coCau =
//                "${properties?.dien_tich_nho_nhat ?: 0.0} m2 - ${properties?.dien_tich_lon_nhat ?: 0.0} m2"
//            vCoCauCanHo.setValue(coCau)
//            vLoaiHinh.setValue(properties?.loai_can_ho)
//            vThoiDiemMoBan.setValue(properties?.thoi_diem_mo_ban?.toShowTime())
//            vHienTrang.setValue(properties?.tinh_trang)
//            vPhapLy.setValue(properties?.phap_ly)
//            handleUiByLevel(assetLevel)
//        }
//    }
//
//    private fun handleUiByLevel(assetLevel: String?) {
//        var listViewGone = mutableListOf<View>()
//        binding.run {
//            when (assetLevel) {
//                LevelType.TRA_CUU.type -> {
//                    listViewGone = mutableListOf(
//
//                    )
//                }
//                LevelType.LAM_TSSS_CHINH_SUA_GIA.type -> {
//                    listViewGone =
//                        mutableListOf()
//
//                }
//                LevelType.THAM_DINH.type -> {
//
//                }
//                LevelType.MUA_BAN.type -> {
//                    listViewGone =
//                        mutableListOf()
//                }
//                LevelType.TAI_SAN_SO_SANH.type -> {
//                    listViewGone =
//                        mutableListOf()
//                }
//            }
//        }
//        listViewGone.forEach {
//            it.visibility = GONE
//        }
//    }
//
//    fun setThongTinDuAn(properties: Properties.ThongTinDuAn?) {
//        binding.run {
//            vDienTichDat.setValue(properties?.dien_tich_khuon_vien?.toString())
//            vMatDoXayDung.setValue(properties?.mat_do_xay_dung)
//            vSoThap.setValue(properties?.so_block)
//            vTongSoCanHo.setValue(properties?.so_can)
//            vNamXayDung.setValue(UIHelper.uiShowYear(properties?.nam_khoi_cong))
//            vNamHoanThanh.setValue(UIHelper.uiShowYear(properties?.nam_hoan_cong))
//            vSoTang.setValue(properties?.so_tang)
//            val coCau =
//                "${properties?.dien_tich_nho_nhat ?: 0.0} m2 - ${properties?.dien_tich_lon_nhat ?: 0.0} m2"
//            vCoCauCanHo.setValue(coCau)
//            vLoaiHinh.setValue(properties?.loai_can_ho)
//            vThoiDiemMoBan.setValue(properties?.thoi_diem_mo_ban?.toShowTime())
//            vHienTrang.setValue(properties?.tinh_trang)
//            vPhapLy.setValue(properties?.phap_ly)
//        }
//    }
//}