package citics.sharing.customview.companion

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import citics.sharing.adapter.VeCongTrinhAdapter
import citics.sharing.data.model.response.CongTrinh
import citics.sharing.data.model.response.Document
import citics.sharing.data.model.response.tham_dinh.QhxdDTO
import citics.sharing.extension.open
import citics.sharing.extension.showBalloonPopup
import com.sharing.R
import com.sharing.databinding.LayoutVeCongTrinhBinding

/**
 * Created by ChinhQT on 25/10/2022.
 */
class VeCongTrinhLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {
    private var adaterVeCongTrinh: VeCongTrinhAdapter? = null
    private var binding: LayoutVeCongTrinhBinding
    private var onBoSungCongTrinh: (() -> Unit)? = null

    init {
        binding = LayoutVeCongTrinhBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.ThongTinDinhGia, 0, 0
            )

            try {


            } finally {
                typedArray.recycle()
            }

        }
        binding.btnBoSungCongTrinh.setOnClickListener {
            onBoSungCongTrinh?.invoke()
        }
    }


    fun onBoSungCongTrinh(onBoSungCongTrinh: (() -> Unit)) {
        this.onBoSungCongTrinh = onBoSungCongTrinh
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    }

    @SuppressLint("SetTextI18n")
    fun setValue(qhxd: QhxdDTO?, data: MutableList<CongTrinh>?, mapCongTrinh: Map<String, Any>?) {
        if (qhxd == null) {
            binding.tvttqh.visibility = View.GONE
        } else {
            binding.tvttqh.visibility = View.VISIBLE
        }

        binding.tvttqh.setOnClickListener {
            it.showBalloonPopup("text", resLayout = R.layout.layout_popup_qhsdd)?.let {
                it.getContentView().findViewById<TextView>(R.id.tvTitle).text =
                    context.getText(R.string.thong_tin_quy_hoach_xay_dung)
                it.getContentView().findViewById<TextView>(R.id.tvLoaiDat).text =
                    context.getString(R.string.mat_do_xay_dung) + " " + qhxd?.mat_do_xay_dung
                it.getContentView().findViewById<TextView>(R.id.tvThoiKy).text =
                    context.getString(R.string.chieu_cao_xay_dung) + " " + qhxd?.tang_cao + "m"

                it.getContentView().findViewById<TextView>(R.id.tvQuyetDinhSo).text =
                    context.getString(R.string.tqds) + " " + qhxd?.file_quyet_dinh

                if (!TextUtils.isEmpty(qhxd?.file_quyet_dinh_url)) {
                    it.getContentView().findViewById<TextView>(R.id.tvQuyetDinhSo)
                        ?.setOnClickListener {
//                    val urlIntent = Intent(
//                        Intent.ACTION_VIEW, Uri.parse(data.qhsdd?.quyet_dinh_url)
//                    )
//                    context.startActivity(urlIntent)
                            Document(url = qhxd?.file_quyet_dinh_url ).open(context)

//                            context.startActivity(
//                                WebviewActivity.newIntent(
//                                    context,
//                                    qhxd?.file_quyet_dinh_url ?: "",
//                                    title = context.getString(R.string.qds) + " " + qhxd?.file_quyet_dinh,
//                                    isDocumentFile = false,
//                                )
//                            )
                        }
                } else {
                    it.getContentView().findViewById<TextView>(R.id.tvQuyetDinhSo)
                        .setTextColor(context.getColor(R.color.color_divider))
                }

            }
        }
        if (data == null || data.size == 0) {
            showNoData()
            return
        }
        binding.btnBoSungCongTrinh.text = context.getString(R.string.chinh_sua_cong_trinh_xay_dung)
        binding.lnNoCongTrinhXayDung.visibility = View.GONE
        binding.rvVeCongTrinh.visibility = View.VISIBLE
        adaterVeCongTrinh = VeCongTrinhAdapter(context, mapCongTrinh)
        binding.rvVeCongTrinh.adapter = adaterVeCongTrinh
        adaterVeCongTrinh?.mainList = data

    }

    private fun showNoData() {
        binding.lnNoCongTrinhXayDung.visibility = View.VISIBLE
        binding.rvVeCongTrinh.visibility = View.GONE
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

    }

}