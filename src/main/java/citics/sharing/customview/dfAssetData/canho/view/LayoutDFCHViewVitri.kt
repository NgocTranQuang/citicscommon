//package com.citics.cagent.views.dfAssetData.canho.view
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.view.View
//import citics.sharing.data.model.response.tham_dinh.Properties
//import com.citics.cagent.databinding.LayoutDfChViewThongtinduanBinding
//import com.citics.cagent.databinding.LayoutDfChViewVitricanhoBinding
//import com.citics.cagent.utilities.DEFAULT
//import com.citics.cagent.utilities.LevelType
//import com.citics.cagent.utilities.extensions.toShowTime
//import citics.sharing.customview.dfAssetData.nhadat.edit.BaseLayoutDF
//
//class LayoutDFCHViewVitri @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
//) : BaseLayoutDF(context, attrs, defStyle, defStyleRes) {
//
//    private var binding: LayoutDfChViewVitricanhoBinding
//
//    init {
//        binding = LayoutDfChViewVitricanhoBinding.inflate(LayoutInflater.from(context), this)
//
//        attrs?.let {
//        }
//    }
//
//    fun setData(properties: Properties?, assetLevel: String?) {
//        binding.run {
//            sonhaTxt.setValue(properties?.so_nha ?: DEFAULT)
//            tenduongTxt.setValue(properties?.vi_tri_duong ?: DEFAULT)
//            phuongxaTxt.setValue(properties?.phuong ?: DEFAULT)
//            quanhuyenTxt.setValue(properties?.quan ?: DEFAULT)
//            thanhphoTxt.setValue(properties?.thanh_pho ?: DEFAULT)
//            vidoTxt.setValue(properties?.vi_do.toString())
//            kinhdoTxt.setValue(properties?.kinh_do.toString())
//            duan.setValue(properties?.ten_du_an ?: DEFAULT)
//            chuadautu.setValue(properties?.chu_dau_tu ?: DEFAULT)
//            wedsite.setValue(properties?.link_du_an ?: DEFAULT)
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
//                        mutableListOf(wedsite,chuadautu)
//
//                }
//                LevelType.THAM_DINH.type -> {
//
//                }
//                LevelType.MUA_BAN.type -> {
//                    listViewGone =
//                        mutableListOf(wedsite)
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
//}