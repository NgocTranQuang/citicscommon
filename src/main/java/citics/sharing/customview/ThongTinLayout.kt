package citics.sharing.customview

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.TextUtils
import android.text.util.Linkify
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.citics.cbank.R
import com.citics.cbank.databinding.LayoutItemThongTinBinding
import citics.sharing.extension.makeLinks
import citics.sharing.extension.setTintColor


/**
 * Created by ChinhQT on 25/10/2022.
 */
class ThongTinLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutItemThongTinBinding
    private var preventFormatNumber: Boolean = false
    private var mValue : String? = ""

    init {
        binding = LayoutItemThongTinBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.ThongTinLayout, 0, 0
            )

            try {
                val icon = typedArray.getDrawable(R.styleable.ThongTinLayout_thong_tin_layout_icon)
                val title = typedArray.getString(R.styleable.ThongTinLayout_thong_tin_layout_title)
                val value = typedArray.getString(R.styleable.ThongTinLayout_thong_tin_layout_value)
                val defaultValue =
                    typedArray.getString(R.styleable.ThongTinLayout_thong_tin_layout_default_value)
                        ?: ""
                val isBold = typedArray.getBoolean(
                    R.styleable.ThongTinLayout_thong_tin_layout_is_bold,
                    false
                )
                preventFormatNumber = typedArray.getBoolean(
                    R.styleable.ThongTinLayout_thong_tin_layout_prevent_format_number,
                    false
                )
                val unit =
                    typedArray.getString(R.styleable.ThongTinLayout_thong_tin_layout_unit) ?: ""

                val showDivider = typedArray.getBoolean(
                    R.styleable.ThongTinLayout_thong_tin_layout_show_divider, true
                )
                binding.tvTitle.text = "$title"
                binding.tvValue.setUnit(unit)
                binding.tvValue.setDefaultValue(defaultValue)
                binding.tvValue.setPreventFormatNumber(preventFormatNumber)
                if(!TextUtils.isEmpty(value)){
                    binding.tvValue.setValue(value)
                }

                val linkify = typedArray.getBoolean(
                    R.styleable.ThongTinLayout_thong_tin_layout_value_as_link,
                    false
                )
                if(linkify){
                    binding.tvValue.linksClickable = true
                    binding.tvValue.autoLinkMask = Linkify.WEB_URLS
                    binding.tvValue.setTextColor(context.getColor(R.color.colorMainBlue))
                    binding.tvValue.setPaintFlags(binding.tvValue.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)
                }

                if (icon != null) {
                    binding.imgSymbol.setImageDrawable(icon)
                } else {
                    binding.imgSymbol.visibility = View.GONE
                }

                val rIcon = typedArray.getDrawable(R.styleable.ThongTinLayout_thong_tin_layout_right_icon)
                if (rIcon != null) {
//                    binding.tvValue.setCompoundDrawablesWithIntrinsicBounds(null, null, rIcon, null)
                    binding.rightIndicator.setImageDrawable(rIcon)
                    binding.rightIndicator.visibility = View.VISIBLE
                }

                if (showDivider) {
                    binding.vDivider.visibility = View.VISIBLE
                } else {
                    binding.vDivider.visibility = View.GONE
                }
                setFontBold(isBold)

            } finally {
                typedArray.recycle()
            }
        }
    }

    private fun setFontBold(isBold: Boolean) {
        if (isBold) {
            binding.tvValue.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.text_size_19)
            )
//            val typeface = ResourcesCompat.getFont(context, R.font.quicksand_bold)
//            binding.tvValue.typeface = typeface
        } else {
            binding.tvValue.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.text_size_16)
            )
//            val typeface = ResourcesCompat.getFont(context, R.font.quicksand_medium)
//            binding.tvValue.typeface = typeface
        }
    }

    @SuppressLint("SetTextI18n")
    fun setValue(value: String?) {
        this.mValue = value
        binding.tvValue.setValue(value)
    }

    fun setUnit(unit: String?) {
        unit?.let {
            binding.tvValue.setUnit(unit)
        }
    }

    fun setTitleAndImage(title: String, img: Int, isShowDivider: Boolean = true) {
        binding.tvTitle.text = title
        binding.imgSymbol.setImageResource(img)
        if (isShowDivider) {
            binding.vDivider.visibility = View.VISIBLE
        } else {
            binding.vDivider.visibility = View.GONE
        }
    }

    fun setTitle(title: String) {
        binding.tvTitle.text = title
    }

    fun setDrawable(drawable: Drawable?) {
        binding.imgSymbol.setImageDrawable(drawable)
    }

    fun setDataYellowColor(any: Any?) {
        binding.tvValue.setDataYellowColor(any)
    }

    fun setValueColor(color: Int) {
        binding.tvValue.setTextColor(context.getColor(color))
    }

    fun setTitleColor(color: Int) {
        binding.tvValue.setTitleColor(color)
        binding.tvTitle.setTextColor(context.getColor(color))
        binding.imgSymbol.setTintColor(color)
    }

    fun makeLinkValue(value: String?, onClick: () -> Unit) {
        //Pls search 'thong_tin_layout_value_as_link', it may help
        setValue(value)
        binding.tvValue.makeLinks((value ?: "") to {
            onClick.invoke()
        })
    }

    fun openMapOnClickValue(activity: Activity) {
        val latLng = this.mValue
        binding.tvValue.setOnClickListener{
            val gmmIntentUri = Uri.parse("geo:"+latLng)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            mapIntent.resolveActivity(activity.packageManager)?.let {
                activity.startActivity(mapIntent)
            }
        }
    }

}