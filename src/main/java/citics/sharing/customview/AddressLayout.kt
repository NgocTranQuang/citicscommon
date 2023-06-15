package citics.sharing.customview

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import citics.sharing.utils.LoaiTaiSan
import com.sharing.R
import com.sharing.databinding.LayoutAddressBinding


/**
 * Created by ChinhQT on 25/10/2022.
 */

class AddressLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private var binding: LayoutAddressBinding
    private var onFavouriteClick: (() -> Unit)? = null

    init {
        binding = LayoutAddressBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.AddressLayout, 0, 0
            )

            try {
                val direction: Int = typedArray.getInt(R.styleable.AddressLayout_loai_tai_san, 0)
                val showFavorite: Boolean =
                    typedArray.getBoolean(R.styleable.AddressLayout_showFavourite, false)
                if (direction == LoaiTaiSan.CAN_HO.type) {
                    showUICanHO()
                } else if (direction == LoaiTaiSan.NHA_DAT.type) {
                    showUINhaDat()
                }
                binding.imgFavourite.setOnClickListener {
                    onFavouriteClick?.invoke()
                }
            } finally {
                typedArray.recycle()
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showUICanHO() {
        binding.tvTitle.text = context.getString(R.string.can_ho)
        binding.tvTitle.setDrawableStartTextview(
            ContextCompat.getDrawable(context, R.drawable.ic_can_ho_white)
        )
    }

    private fun showUINhaDat() {
        binding.tvTitle.text = context.getString(R.string.nha_dat)
        binding.tvTitle.setDrawableStartTextview(
            ContextCompat.getDrawable(context, R.drawable.ic_home_white)
        )
    }

    fun setLoaiTaiSan(loaiTaiSan: String) {
        if (loaiTaiSan == LoaiTaiSan.NHA_DAT.typeName) {
            showUINhaDat()
        } else if (loaiTaiSan == LoaiTaiSan.CAN_HO.typeName) {
            showUICanHO()
        } else {
            showUIDuAn()
        }
    }

    private fun showUIDuAn() {
        binding.tvTitle.text = context.getString(R.string.du_an)
        binding.tvTitle.setDrawableStartTextview(
            ContextCompat.getDrawable(context, R.drawable.ic_du_an_white)
        )
    }

    fun setAddress(address1: String, address2: String) {
        binding.address1.text = address1
        binding.address2.text = address2
        binding.addressLayout.visibility = View.VISIBLE
    }

    fun setOnFavouriteClick(function: () -> Unit) {
        this.onFavouriteClick = function
    }

    fun setSave(saved: Boolean?) {
        binding.imgFavourite.visibility = View.VISIBLE
        if (saved == true) {
            binding?.imgFavourite?.setImageResource(R.drawable.ic_favourite_fill)
        } else if (saved == false) {
            binding?.imgFavourite?.setImageResource(R.drawable.ic_favoutire)
        }else{
            binding.imgFavourite.visibility = View.GONE
        }

    }

}