package citics.sharing.customview.dfAssetData.nhadat.edit

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import citics.sharing.customview.CustomViewDotLayout
import citics.sharing.data.model.response.DetailUsingPurpose
import citics.sharing.data.model.response.tham_dinh.Properties
import com.citics.cbank.R
import com.citics.cbank.databinding.LayoutDfNdHientrangthuadatBinding
import com.citics.valuation.data.model.others.ChooserItem
import com.citics.valuation.data.model.others.SelectorItem
import com.citics.valuation.data.model.others.SingleChoiceData
import citics.sharing.extension.toListChooser
import citics.sharing.extension.toListSelector
import com.citics.valuation.utils.LevelType
import com.citics.valuation.utils.StaticDataUtils

/**
 * Created by ChinhQT on 25/10/2022.
 */

class LayoutDFNDHienTrangThuaDat @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : BaseLayoutDF(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutDfNdHientrangthuadatBinding
    private var usingPurpose: List<DetailUsingPurpose>? = null


    init {
        binding = LayoutDfNdHientrangthuadatBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            onclick()
        }
    }

    private fun onclick() {
        binding.vMatTienTiepGiap.onClickListener = {
            openSingleChoiceActivity(
                SingleChoiceData(
                    title = R.string.mat_tien_tiep_giap,
                    StaticDataUtils.staticNhaDat?.danhMucLand?.matTienTiepGiap.toListChooser(),
                    binding.vMatTienTiepGiap.getText().toString()
                )
            )
        }
        binding.vKetCauDuong.onClickListener = {
            openSingleChoiceActivity(
                SingleChoiceData(
                    title = R.string.ket_cau_duong,
                    StaticDataUtils.staticNhaDat?.danhMucLand?.ketCauDuong.toListChooser(),
                    binding.vKetCauDuong.getText().toString()
                )
            )
        }
        binding.vKetNoiGiaoThong.onClickListener = {
            openSingleChoiceActivity(
                SingleChoiceData(
                    title = R.string.ket_noi_giao_thong,
                    StaticDataUtils.staticNhaDat?.danhMucLand?.ketNoiGiaoThong.toListChooser(),
                    binding.vKetNoiGiaoThong.getText().toString()
                )
            )
        }

        binding.vSoSanhVoiThucTe.onClickListener = {
            openSingleChoiceActivity(
                SingleChoiceData(
                    title = R.string.so_sanh_voi_thuc_te,
                    StaticDataUtils.staticFull?.so_sanh_thuc_te_yeu_to_khac?.map {
                        ChooserItem(
                            id = it.key, name = it.title
                        )
                    }?.toMutableList() ?: mutableListOf(),
                    binding.vSoSanhVoiThucTe.getText().toString()
                )
            )
        }
    }

    fun setData(
        properties: Properties?,
        usingPurpose: List<DetailUsingPurpose>?,
        assetLevel: String?
    ) {
        this.properties = properties
        this.usingPurpose = usingPurpose
        handleUiByLevel(assetLevel)
    }

    override fun onResultSingleChoice(title: Int, id: String?, name: String?) {
        when (title) {
            R.string.ket_cau_duong -> {
                properties?.ket_cau_duong = name
                binding?.vKetCauDuong?.setText(name ?: "")
            }
            R.string.mat_tien_tiep_giap -> {
                properties?.mat_tien_tiep_giap = name
                binding?.vMatTienTiepGiap?.setText(name ?: "")

            }
            R.string.ket_noi_giao_thong -> {
                properties?.ket_noi_giao_thong = name
                binding?.vKetNoiGiaoThong?.setText(name ?: "")

            }
            R.string.so_sanh_voi_thuc_te -> {
                properties?.so_sanh_thuc_te_yeu_to_khac =
                    name
                binding?.vSoSanhVoiThucTe?.setText(name ?: "")

            }
        }
    }


    override fun onResultMultiChoice(title: Int, list: MutableList<SelectorItem>) {
        when (title) {
            R.string.hien_trang_su_dung -> {

                val itemKhacKhac = list.firstOrNull { it.isCustomData }
                itemKhacKhac?.let {
                    properties?.hien_trang_su_dung_khac =
                        it.name
                }
                val itemSelectedWithouKhacKhac =
                    list.filter { !it.isCustomData }
                properties?.hien_trang_su_dung =
                    itemSelectedWithouKhacKhac.map { it.name }.toMutableList()

                val lhtsdk = mutableListOf<String?>(properties?.hien_trang_su_dung_khac)
                lhtsdk.addAll(properties?.hien_trang_su_dung ?: mutableListOf())

                binding.vHienTrangSuDung.setListSelected(lhtsdk.map {
                    SelectorItem(it ?: "", it ?: "")
                } ?: listOf())
            }
            R.string.yeu_to_khac -> {
                val itemKhacKhac = list.firstOrNull { it.isCustomData }
                properties?.yeu_to_khac_khac =
                    itemKhacKhac?.name
                val itemSelectedWithouKhacKhac =
                    list.filter { !it.isCustomData }
                properties?.yeu_to_khac =
                    itemSelectedWithouKhacKhac.map { it.name }.toMutableList()


                val lsYtk = mutableListOf<String?>(properties?.yeu_to_khac_khac)
                lsYtk.addAll(properties?.yeu_to_khac ?: mutableListOf())
                binding.vYeuToKhac.setListSelected(lsYtk.map {
                    SelectorItem(it ?: "", it ?: "")
                } ?: listOf())
            }
        }
    }

    fun handleHuongTiepGiap(huong: String?) {
        huong?.let {
            goneAllViewDot()
            val listChoose =
                StaticDataUtils.staticFull?.huong_tiep_giap?.firstOrNull { it.contains(huong) }
            listChoose?.forEachIndexed { index, s ->
                val theLast = index == listChoose.size - 1
                getViewByName(s).let {
                    it.visibility = View.VISIBLE
                    if (theLast) {
                        it.hideBottomLine()
                    }
                }
            }
        }
    }

    private fun goneAllViewDot() {
        binding.dot5.visibility = GONE
        binding.dot6.visibility = GONE
        binding.dot7.visibility = GONE
        binding.dot8.visibility = GONE
        binding.dot9.visibility = GONE
        binding.dot10.visibility = GONE
        binding.dot11.visibility = GONE
        binding.dot12.visibility = GONE
    }

    fun setListYeuToKhac(usingPurpose: List<DetailUsingPurpose>?) {
        this.usingPurpose = usingPurpose
        val listYTK: List<String> =
            StaticDataUtils.staticNhaDat?.danhMucLand?.yeuToKhac?.filter {
                usingPurpose?.map { using -> using.name }?.contains(
                    it.key
                ) == true
            }?.values?.flatten() ?: listOf()

        val listWithoutDuplicates: List<String> = ArrayList(
            LinkedHashSet(listYTK)
        )
        binding.vYeuToKhac.setListChoice(listWithoutDuplicates.toListSelector())
    }

    private fun getViewByName(name: String): CustomViewDotLayout {
        when (name) {
            context.getString(R.string.dong) -> {
                return binding.dot5
            }
            context.getString(R.string.tay) -> {
                return binding.dot6
            }
            context.getString(R.string.nam) -> {
                return binding.dot7
            }
            context.getString(R.string.bac) -> {
                return binding.dot8
            }
            context.getString(R.string.dong_bac) -> {
                return binding.dot9
            }
            context.getString(R.string.dong_nam) -> {
                return binding.dot10
            }
            context.getString(R.string.tay_nam) -> {
                return binding.dot11
            }
            context.getString(R.string.tay_bac) -> {
                return binding.dot12
            }
            else -> {
                return binding.dot12
            }
        }
    }

    override fun setLauncher(
        singleChoiceLauncher: ActivityResultLauncher<Intent>?,
        multiChoiceLauncher: ActivityResultLauncher<Intent>?
    ) {
        super.setLauncher(singleChoiceLauncher, multiChoiceLauncher)
        binding.vHienTrangSuDung.setLauncher(null, multiChoiceLauncher)
        binding.vYeuToKhac.setLauncher(null, multiChoiceLauncher)
    }

    private fun handleUiByLevel(assetLevel: String?) {
        binding.run {
            when (assetLevel) {
                LevelType.TRA_CUU.type -> {
                    vKetNoiGiaoThong.visibility = GONE
                    lnHienTrangTiepGiap.visibility = GONE
                    vHienTrangSuDung.visibility = GONE
                    vSoSanhVoiThucTe.visibility = GONE
                    vGhiChu.visibility = GONE
                    vGiaThuyet.visibility = GONE
                }
                LevelType.LAM_TSSS_CHINH_SUA_GIA.type -> {
                    vKetNoiGiaoThong.visibility = GONE
                    lnHienTrangTiepGiap.visibility = GONE
                    vHienTrangSuDung.visibility = GONE
                    vSoSanhVoiThucTe.visibility = GONE
                    vGhiChu.visibility = GONE
                    vGiaThuyet.visibility = GONE
                }
                LevelType.MUA_BAN.type -> {
                    lnHienTrangTiepGiap.visibility = GONE
                    vSoSanhVoiThucTe.visibility = GONE
                    vGhiChu.visibility = GONE
                    vGiaThuyet.visibility = GONE
                }
                LevelType.THAM_DINH.type -> {

                }
                LevelType.TAI_SAN_SO_SANH.type -> {
                    vKetNoiGiaoThong.visibility = GONE
                    lnHienTrangTiepGiap.visibility = GONE
                    vHienTrangSuDung.visibility = GONE
                    vSoSanhVoiThucTe.visibility = GONE
                    vGhiChu.visibility = GONE
                    vGiaThuyet.visibility = GONE
                }
            }
        }
        setDataForView()
    }

    private fun setDataForView() {
        binding.run {
            properties?.let { properties ->
                vMatTienTiepGiap.setText(properties.mat_tien_tiep_giap ?: "")
                vKhoangCachRaDuongChinh.setText(properties.khoang_cach_ra_duong_chinh) {
                    properties.khoang_cach_ra_duong_chinh = it
                }
                vDoRong.setText(properties.do_rong_hem_truoc_nha) {
                    properties.do_rong_hem_truoc_nha = it
                }
                vKetCauDuong.setText(properties.ket_cau_duong ?: "")
                vDoanNhoNhat.setText(properties.do_rong_hem_nho_nhat) {
                    properties.do_rong_hem_nho_nhat = it
                }
                vQuyHoachDuKien.setText(properties.quy_hoach_du_kien) {
                    properties.quy_hoach_du_kien = it
                }
                vKetNoiGiaoThong.setText(properties.ket_noi_giao_thong ?: "")
                vDong.setText(properties.huong_tiep_giap?.dong) {
                    properties.huong_tiep_giap?.dong = it
                }
                vTay.setText(properties.huong_tiep_giap?.tay) {
                    properties.huong_tiep_giap?.tay = it
                }
                vNam.setText(properties.huong_tiep_giap?.nam) {
                    properties.huong_tiep_giap?.nam = it
                }
                vBac.setText(properties.huong_tiep_giap?.bac) {
                    properties.huong_tiep_giap?.bac = it
                }
                vDongBac.setText(properties.huong_tiep_giap?.dong_bac) {
                    properties.huong_tiep_giap?.dong_bac = it
                }
                vDongnam.setText(properties.huong_tiep_giap?.dong_nam) {
                    properties.huong_tiep_giap?.dong_nam = it
                }
                vTayNam.setText(properties.huong_tiep_giap?.tay_nam) {
                    properties.huong_tiep_giap?.tay_nam = it
                }
                vTayBac.setText(properties.huong_tiep_giap?.tay_bac) {
                    properties.huong_tiep_giap?.tay_bac = it
                }
                val lhtsdk = mutableListOf<String?>(properties.hien_trang_su_dung_khac)
                lhtsdk.addAll(properties.hien_trang_su_dung ?: mutableListOf())

                vHienTrangSuDung.setListSelected(lhtsdk.map {
                    SelectorItem(it ?: "", it ?: "")
                } ?: listOf())

                val lsYtk = mutableListOf<String?>(properties.yeu_to_khac_khac)
                lsYtk.addAll(properties.yeu_to_khac ?: mutableListOf())
                vYeuToKhac.setListSelected(lsYtk.map {
                    SelectorItem(it ?: "", it ?: "")
                } ?: listOf())
                vSoSanhVoiThucTe.setText(properties.so_sanh_thuc_te_yeu_to_khac ?: "")
                vGhiChu.setText(properties.ghi_chu_khac_ve_thua_dat) {
                    properties.ghi_chu_khac_ve_thua_dat = it
                }
                setListYeuToKhac(usingPurpose)
                binding.vHienTrangSuDung.setListChoice(StaticDataUtils.staticFull?.hien_trang_su_dung?.map {
                    SelectorItem(
                        id = it.key,
                        name = it.title
                    )
                }?.toMutableList() ?: mutableListOf())
            }
            binding.lnContent.visibility = View.VISIBLE
        }
    }

}