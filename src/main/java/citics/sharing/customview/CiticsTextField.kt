package citics.sharing.customview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import citics.sharing.extension.restoreChildViewStates
import citics.sharing.extension.saveChildViewStates
import citics.sharing.extension.toRound
import citics.sharing.utils.DecimalDigitsInputFilter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.sharing.R
import com.sharing.databinding.LayoutTextFieldBinding


/**
 * Created by ChinhQT on 04/10/2022.
 */
class CiticsTextField @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    var binding: LayoutTextFieldBinding

    var endIconOnClickListener: ((View) -> Unit)? = null

    // This field listener for text field changed
    var onChangeTextField: ((String?) -> Unit)? = null

    private var radiusType: TypeRadius = TypeRadius.All

    private var kdvdType: Boolean = false

    init {
        binding = LayoutTextFieldBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.CiticsTextField, 0, 0
            )

            try {
                val titleLbl = typedArray.getString(R.styleable.CiticsTextField_label_title)
                val showTitleImg =
                    typedArray.getBoolean(R.styleable.CiticsTextField_label_show_icon, false)
                val titleImg = typedArray.getDrawable(R.styleable.CiticsTextField_label_icon_img)
                val inputHint = typedArray.getString(R.styleable.CiticsTextField_input_hint)
                val lableEnd = typedArray.getString(R.styleable.CiticsTextField_label_end)
                val radius = typedArray.getInt(R.styleable.CiticsTextField_bg_radius, 0)
                val inputType = typedArray.getInt(
                    R.styleable.CiticsTextField_android_inputType, EditorInfo.TYPE_NULL
                )
                val imeOption = typedArray.getInt(
                    R.styleable.CiticsTextField_android_imeOptions, EditorInfo.IME_ACTION_NONE
                )
                kdvdType =
                    typedArray.getBoolean(R.styleable.CiticsTextField_is_kdvd, false)
                val maxLength = typedArray.getInt(
                    R.styleable.CiticsTextField_android_maxLength, 100
                )
                val maxLines = typedArray.getInt(R.styleable.CiticsTextField_android_maxLines, 0)

                binding.textInput.filters += InputFilter.LengthFilter(maxLength)

                if (maxLines != 0) {
                    binding.textInput.maxLines = maxLines
                }

                radiusType = when (radius) {
                    TypeRadius.All.type -> {
                        TypeRadius.All
                    }
                    TypeRadius.LEFT.type -> {
                        TypeRadius.LEFT
                    }
                    else -> {
                        TypeRadius.RIGHT
                    }
                }

                bindData(
                    titleLbl,
                    showTitleImg || titleImg != null,
                    titleImg,
                    inputHint,
                    inputType,
                    imeOption,
                    lableEnd
                )

            } finally {
                typedArray.recycle()
            }
        }
    }

    fun bindData(
        titleLbl: String?,
        showTitleImg: Boolean?,
        titleImg: Drawable?,
        inputHint: String?,
        inputType: Int?,
        imeOption: Int?,
        lableEnd: String?
    ) {
        binding.titleLbl.text = titleLbl
        if (showTitleImg == true) {
            binding.titleImg.visibility = View.VISIBLE
        } else {
            binding.titleImg.visibility = View.GONE
        }
        binding.titleImg.setImageDrawable(titleImg)
        binding.textInput.hint = inputHint
        if (inputType != EditorInfo.TYPE_NULL) {
            inputType?.let {
                // Check input type có phải là decimal hay ko (Không dùng  InputType.TYPE_NUMBER_FLAG_DECIMAL => Sai đó)
                if (inputType == 0x00002002 && !kdvdType) {
                    binding.textInput.filters = arrayOf<InputFilter>(
                        DecimalDigitsInputFilter(2)
                    )
                }
                binding.textInput.inputType = it
            }
        }
        if (lableEnd == context.getString(R.string.d)) {
            // Nếu edit text này là edittext nhập số tiền thì giới hạn số tiền dc nhập
            val maxLength = 15
            binding.textInput.setFilters(arrayOf<InputFilter>(LengthFilter(maxLength)))
        }
        imeOption?.let {
            binding.textInput.imeOptions = it
        }
        binding.tvEnd.text = lableEnd
        if (TextUtils.isEmpty(lableEnd)) {
            binding.tvEnd.visibility = View.GONE
        } else {
            binding.tvEnd.visibility = View.VISIBLE
        }
        setBoundColor()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onFinishInflate() {
        super.onFinishInflate()

        binding.textInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                onChangeTextField?.invoke(s.toString())

                if (s?.isNotEmpty() == true) {
                    setState()
                }
            }
        })
    }

    fun setData(data: Double) {
        if (data == 0.0) {
            binding.textInput.setText("")
        } else {
            binding.textInput.setText(data.toString())
        }
    }

    fun disable() {
        binding.textInput.isEnabled = false
        binding.textInput.setTextColor(context.getColor(R.color.button_disable))
    }

    fun setState(isError: Boolean = false) {
        setBoundColor(isError)
        setIcon(isError)
    }

    private fun setBoundColor(isError: Boolean = false) {
        if (isError) {
            binding.vBorder.setBackgroundResource(radiusType.backGroundError)
        } else {
            binding.vBorder.setBackgroundResource(radiusType.backGround)
        }
    }

    private fun setIcon(isError: Boolean = false) {
        if (isError) {
            if (radiusType == TypeRadius.All) {
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

                binding.inputLayout.setEndIconOnClickListener {
                    endIconOnClickListener?.invoke(binding.inputLayout)
                }
            } else {
                binding.inputLayout.endIconMode = TextInputLayout.END_ICON_NONE
            }
        } else {
            if (radiusType == TypeRadius.All) {
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
            } else {
                binding.inputLayout.endIconMode = TextInputLayout.END_ICON_NONE
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

    fun getTextInput(): TextInputEditText = binding.textInput

    fun getText() = binding.textInput.text

    fun setTitle(title: String) {
        binding.titleLbl.text = title
    }

    fun setHint(hint: String) {
        binding.textInput.hint = hint
    }

    fun setText(value: String?, onChange: ((String?) -> Unit)? = null) {
        if (value == null) {
            binding.textInput.setText("")
        } else {
            binding.textInput.setText("$value")
        }
        onChange?.let { change ->
            onChangeTextField = {
                change.invoke(binding.textInput.text.toString())
            }
        }
    }

    fun setText(value: Int?, onChange: ((Int?) -> Unit)? = null) {
        if (value == null) {
            binding.textInput.setText("")
        } else {
            binding.textInput.setText("$value")
        }
        onChange?.let { change ->
            onChangeTextField = {
                change.invoke(binding.textInput.text.toString().toIntOrNull())
            }
        }
    }

    fun setText(value: Double?, onChange: ((Double?) -> Unit)? = null) {
        if (value == null) {
            binding.textInput.setText("")
        } else {
            if (kdvdType) {
                binding.textInput.setText(value.toString())
            } else {
                binding.textInput.setText(value.toRound())
            }
        }
        onChange?.let { change ->
            onChangeTextField = {
                change.invoke(binding.textInput.text.toString().toDoubleOrNull())
            }
        }
    }

    fun setText(value: Float?, onChange: ((Float?) -> Unit)? = null) {
        if (value == null) {
            binding.textInput.setText("")
        } else {
            binding.textInput.setText(value.toRound())
        }
        onChange?.let { change ->
            onChangeTextField = {
                change.invoke(binding.textInput.text.toString().toFloatOrNull())
            }
        }
    }

    fun setText(value: Long?, onChange: ((Long?) -> Unit)? = null) {
        if (value == null) {
            binding.textInput.setText("")
        } else {
            binding.textInput.setText(value.toString())
        }
        onChange?.let { change ->
            onChangeTextField = {
                change.invoke(binding.textInput.text.toString().toLongOrNull())
            }
        }
    }

    enum class TypeRadius(val type: Int, val backGround: Int, val backGroundError: Int) {
        All(
            0, R.drawable.background_citics_textfield, R.drawable.background_citics_textfield_error
        ),
        RIGHT(
            1,
            R.drawable.background_border_radius_right,
            R.drawable.background_border_radius_right_error
        ),
        LEFT(
            2,
            R.drawable.background_border_radius_left,
            R.drawable.background_border_radius_left_error
        )
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