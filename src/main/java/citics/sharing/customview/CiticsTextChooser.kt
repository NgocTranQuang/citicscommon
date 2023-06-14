package citics.sharing.customview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.text.InputType
import android.text.TextUtils
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.citics.cbank.R
import com.citics.cbank.databinding.LayoutTextChooserBinding
import citics.sharing.extension.restoreChildViewStates
import citics.sharing.extension.saveChildViewStates
import citics.sharing.extension.setTintColor
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by ChinhQT on 13/10/2022.
 */
class CiticsTextChooser @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutTextChooserBinding

    var onClickListener: (() -> Unit)? = null

    init {
        binding = LayoutTextChooserBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.CiticsTextChooser, 0, 0
            )

            try {
                val titleLbl =
                    typedArray.getString(R.styleable.CiticsTextChooser_text_chooser_title)
                val titleImg =
                    typedArray.getDrawable(R.styleable.CiticsTextChooser_text_chooser_icon_img)
                val content = typedArray.getString(R.styleable.CiticsTextChooser_text_input)
                val inputHint =
                    typedArray.getString(R.styleable.CiticsTextChooser_text_chooser_input_hint)
                val maxLines = typedArray.getInt(R.styleable.CiticsTextChooser_android_maxLines, 1)

                bindData(titleLbl, titleImg != null, titleImg, content, inputHint, maxLines)

                binding.vClick.setOnClickListener {
                    onClickListener?.invoke()
                }
            } finally {
                typedArray.recycle()
            }
        }

    }

    fun bindData(
        titleLbl: String?,
        showTitleImg: Boolean?,
        titleImg: Drawable?,
        content: String?,
        inputHint: String?,
        maxLines: Int
    ) {
        binding.titleLbl.text = titleLbl
        if (showTitleImg == true) {
            binding.titleImg.visibility = View.VISIBLE
        } else {
            binding.titleImg.visibility = View.GONE
        }
        binding.titleImg.setImageDrawable(titleImg)
        binding.textInput.setText(content)
        binding.textInput.hint = inputHint
        binding.textInput.maxLines = maxLines
        binding.textInput.ellipsize = TextUtils.TruncateAt.END
    }

    fun getTextInput(): TextInputEditText = binding.textInput

    fun getText() = binding.textInput.text

    fun setText(data: String) {
        binding.textInput.setText(data)
    }

    fun setState(isError: Boolean = false) {
        setBoundColor(isError)
        setIcon(isError)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        onClickListener = {
            l?.onClick(this)
        }
    }

    fun disable() {
        binding.imgArrow.visibility = View.GONE
        binding.textInput.setTextColor(context.getColor(R.color.button_disable))
    }

    private fun setBoundColor(isError: Boolean = false) {
        if (isError) {
            binding.imgArrow.setTintColor(R.color.login_drawable_hint_error)
            binding.backgroundLayout.setBackgroundResource(R.drawable.background_citics_textfield_error)
        } else {
            binding.imgArrow.setTintColor(R.color.color_blue)
            binding.backgroundLayout.setBackgroundResource(R.drawable.background_citics_textfield)
        }
    }

    private fun setIcon(isError: Boolean = false) {
        if (isError) {
            binding.inputLayout.endIconMode = TextInputLayout.END_ICON_CUSTOM
            val drawable =
                ResourcesCompat.getDrawable(resources, R.drawable.ic_warning, context.theme)
            binding.inputLayout.setEndIconTintList(
                ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        resources, R.color.login_drawable_hint_error, context.theme
                    )
                )
            )
            binding.inputLayout.endIconDrawable = drawable

        } else {
            when (binding.textInput.inputType) {
                InputType.TYPE_CLASS_TEXT -> {
                    binding.inputLayout.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
                }
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD -> {
                    binding.inputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
                }
                else -> {
                    binding.inputLayout.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
                }
            }

            binding.inputLayout.setEndIconTintList(
                ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        resources, R.color.login_drawable_hint_normal, context.theme
                    )
                )
            )
        }
    }

    public override fun onSaveInstanceState(): Parcelable {
        // Timber.d("onSaveInstanceState")
        return SavedState(super.onSaveInstanceState()).apply {
            childrenStates = saveChildViewStates()
        }
    }

    public override fun onRestoreInstanceState(state: Parcelable) {
        // Timber.d("onRestoreInstanceState")
        when (state) {
            is SavedState -> {
                super.onRestoreInstanceState(state.superState)
                state.childrenStates?.let { restoreChildViewStates(it) }
            }
            else -> super.onRestoreInstanceState(state)
        }
    }

    override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>) {
        dispatchFreezeSelfOnly(container)
    }

    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>) {
        dispatchThawSelfOnly(container)
    }

    internal class SavedState : BaseSavedState {

        internal var childrenStates: SparseArray<Parcelable>? = null

        constructor(superState: Parcelable?) : super(superState)

        @Suppress("UNCHECKED_CAST")
        constructor(source: Parcel) : super(source) {
            // Timber.d("Reading children children state from sparse array")
            childrenStates =
                source.readSparseArray<SparseArray<Parcelable>>(javaClass.classLoader) as SparseArray<Parcelable>?
        }

        @Suppress("UNCHECKED_CAST")
        override fun writeToParcel(out: Parcel, flags: Int) {
            // Timber.d("Writing children state to sparse array")
            super.writeToParcel(out, flags)
            out.writeSparseArray(childrenStates as SparseArray<Any>)
        }

        companion object {
            @Suppress("UNUSED")
            @JvmField
            val CREATOR = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(source: Parcel) = SavedState(source)
                override fun newArray(size: Int): Array<SavedState?> = arrayOfNulls(size)
            }
        }
    }
}