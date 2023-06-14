//package com.citics.cagent.views.dfAssetData.canho.view
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.view.View
//import citics.sharing.data.model.response.PhapLyDTO
//import citics.sharing.data.model.response.tham_dinh.Properties
//import com.citics.cagent.databinding.LayoutDfChViewPhaplyBinding
//import com.citics.cagent.databinding.LayoutDfChViewThongtinduanBinding
//import com.citics.cagent.utilities.LevelType
//import com.citics.cagent.utilities.LoaiTaiSan
//import com.citics.cagent.utilities.extensions.dataListenerScope
//import com.citics.cagent.utilities.extensions.toShowTime
//import com.citics.cagent.utilities.static_data_utils.StaticDataUtils
//import citics.sharing.customview.dfAssetData.nhadat.edit.BaseLayoutDF
//
//class LayoutDFCHViewPhapLy @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
//) : BaseLayoutDF(context, attrs, defStyle, defStyleRes) {
//
//    private var binding: LayoutDfChViewPhaplyBinding
//    var onClick: (PhapLyDTO) -> Unit = {}
//    var onDelete: (PhapLyDTO) -> Unit = {}
//    var onClickAddNew: (() -> Unit)? = null
//
//    init {
//        binding = LayoutDfChViewPhaplyBinding.inflate(LayoutInflater.from(context), this)
//
//        attrs?.let {
//        }
//    }
//
//    fun setData(properties: Properties?, assetLevel: String?) {
//        binding.run {
//            if (properties?.loai_tai_san == LoaiTaiSan.CAN_HO.typeName) {
//                vListLoaiPhapLy.setListPhapLy(
//                    StaticDataUtils.listLegalStaticCanHo,
//                    properties.nhom_hinh_phap_ly ?: mutableListOf()
//                )
//            } else {
//                vListLoaiPhapLy.setListPhapLy(
//                    StaticDataUtils.listLegalStaticNhaDat,
//                    properties?.nhom_hinh_phap_ly ?: mutableListOf()
//                )
//            }
//            vListLoaiPhapLy.onClick = onClick
//            vListLoaiPhapLy.onDelete = onDelete
//            vListLoaiPhapLy.onClickAddNew = onClickAddNew
//            vTinhTrangPhapLy.setValue(properties?.tinh_trang_phap_ly)
//            vTenChuSoHuu.setValue(properties?.chu_so_huu)
//            vQuanHe.setValue(properties?.quan_he_kh_chu_so_huu)
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
//                        mutableListOf(lnChuSoHuu)
//                }
//                LevelType.TAI_SAN_SO_SANH.type -> {
//                    listViewGone =
//                        mutableListOf(lnChuSoHuu)
//                }
//            }
//        }
//        listViewGone.forEach {
//            it.visibility = GONE
//        }
//    }
//
//}