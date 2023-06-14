//package com.citics.cagent.views.dfAssetData.nhadat.view
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.widget.LinearLayout
//import androidx.navigation.findNavController
//import androidx.navigation.fragment.findNavController
//import com.citics.cagent.R
//import citics.sharing.data.model.response.DetailUsingPurpose
//import citics.sharing.data.model.response.LandDTO
//import citics.sharing.data.model.response.tham_dinh.Properties
//import com.citics.cagent.databinding.LayoutDfNdHientrangthuadatBinding
//import com.citics.cagent.databinding.LayoutDfNdViewChitietthuadatBinding
//import com.citics.cagent.ui.fragments.adapters.tham_dinh.DonGiaDatOAdapter
//import com.citics.cagent.ui.fragments.adapters.tham_dinh.DonGiaDatOViewAdapter
//import com.citics.cagent.ui.fragments.adapters.tham_dinh.MucDichSuDungDatAdapter
//import com.citics.cagent.ui.fragments.thamdinh.chitiet.ChiTietDienTichThuaDatFragment
//import com.citics.cagent.utilities.LevelType
//import com.citics.cagent.utilities.extensions.setListChip
//import com.citics.cagent.utilities.extensions.toShow
//
//class LayoutDFNDViewChiTietThuaDat @JvmOverloads constructor(
//    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
//) : LinearLayout(context, attrs, defStyle, defStyleRes) {
//    private var properties: Properties? = null
//    private var binding: LayoutDfNdViewChitietthuadatBinding
//    private var adapterMucDichSuDungDat: MucDichSuDungDatAdapter? = null
//    private var adapterDonGiaThuaDat: DonGiaDatOViewAdapter? = null
//
//    private var listDat: List<LandDTO>? = null
//    private var tongDienTich: String? = null
//
//    init {
//        binding = LayoutDfNdViewChitietthuadatBinding.inflate(LayoutInflater.from(context), this)
//        adapterMucDichSuDungDat = MucDichSuDungDatAdapter(
//            context, mutableListOf(), false
//        )
//        adapterDonGiaThuaDat = DonGiaDatOViewAdapter(context, mutableListOf())
//        binding.rvMucDichSuDungDat.adapter = adapterMucDichSuDungDat
//        binding.rvDonGiaDat.adapter = adapterDonGiaThuaDat
//        attrs?.let {
//            onClick()
//        }
//
//    }
//
//    private fun onClick() {
//        binding.tvChiTietThuaDat.setOnClickListener {
//            findNavController().navigate(
//                R.id.chiTietDienTichThuaDatFragment,
//                ChiTietDienTichThuaDatFragment.getArgument(tongDienTich ?: "", listDat)
//            )
//        }
//    }
//
//    fun setData(
//        properties: Properties?,
//        usingPurpose: List<DetailUsingPurpose>?,
//        assetLevel: String?
//    ) {
//        this.properties = properties
//        binding.run {
//            properties?.let {
//                vTongDienTich.setValue(properties.dien_tich_dc_cong_nhan?.toString())
//                vChieuDai.setValue(properties.chieu_dai?.toString())
//                vChieuRong.setValue(properties.chieu_rong?.toString())
//                vHinhDang.setValue(properties.hinh_dang)
//                vHanMucChuyenDoi.setValue(properties.k_factor_upper_limit)
//                vHuongNha.setValue(properties.huong)
//                vHinhThucSuDung.setValue(properties.hinh_thuc_su_dung)
//                vHeSoK.setValue(properties.k_factor_upper_limit)
//                vVttheoubnd.setValue(properties.vi_tri_theo_ubnd)
//                adapterMucDichSuDungDat?.mainList = usingPurpose?.toMutableList()
//                adapterDonGiaThuaDat?.mainList = properties.lands
//                listDat = properties.lands
//                tongDienTich = properties.dien_tich
//            }
//            handleUiByLevel(assetLevel)
//        }
//    }
//
//    private fun handleUiByLevel(assetLevel: String?) {
//        binding.run {
//            when (assetLevel) {
//                LevelType.TRA_CUU.type -> {
//                    vHinhThucSuDung.visibility = GONE
//                    bigLine.visibility = GONE
//                    lnChuyenDoiMucDich.visibility = GONE
//                }
//                LevelType.LAM_TSSS_CHINH_SUA_GIA.type -> {
//                    vHinhThucSuDung.visibility = GONE
//                    bigLine.visibility = GONE
//                    lnChuyenDoiMucDich.visibility = GONE
//                }
//                LevelType.MUA_BAN.type -> {
//                    vHinhThucSuDung.visibility = GONE
//                    bigLine.visibility = GONE
//                    lnChuyenDoiMucDich.visibility = GONE
//                }
//                LevelType.THAM_DINH.type -> {
//
//                }
//                LevelType.TAI_SAN_SO_SANH.type -> {
//                    vHinhThucSuDung.visibility = GONE
//                    vHanMucChuyenDoi.visibility = GONE
//                    vHeSoK.visibility = GONE
//                }
//            }
//        }
//    }
//}