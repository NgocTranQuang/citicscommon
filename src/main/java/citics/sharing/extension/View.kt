package citics.sharing.extension

import android.app.Dialog
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.content.res.Resources.NotFoundException
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Parcelable
import android.os.SystemClock
import android.text.TextUtils
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.annotation.CheckResult
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.children
import androidx.core.view.forEach
import androidx.core.view.updateMargins
import androidx.core.widget.ImageViewCompat
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.*
import citics.sharing.customview.CustomChipLayout
import citics.sharing.customview.TextViewUnit
import citics.sharing.customview.ThongTinLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.citics.cbank.R
import com.google.android.gms.common.internal.Preconditions
import com.google.android.material.chip.ChipGroup
import com.google.android.material.divider.MaterialDivider
import com.skydoves.balloon.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import timber.log.Timber


/**
 * Created by ChinhQT on 10/10/2022.
 */

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.GONE
}

fun Window.setSystemBarTextWhite() {
    val view = findViewById<View>(android.R.id.content)
    val flags = view.systemUiVisibility
    view.systemUiVisibility = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
}

fun Window.setSystemBarTextDark() {
    val view = findViewById<View>(android.R.id.content)
    val flags = view.systemUiVisibility
    view.systemUiVisibility = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
}


class ClickListener(private val interval: Long, private val onCLick: (View) -> Unit) :
    View.OnClickListener {
    private var lastTimeClicked: Long = 0

    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked > interval) {
            lastTimeClicked = SystemClock.elapsedRealtime()
            onCLick(v)
        }
    }
}

fun View.setSecureOnClickListener(interval: Long = 500, onClick: (View) -> Unit) {
    val clickListener = ClickListener(interval = interval) {
        onClick(it)
    }
    setOnClickListener(clickListener)
}

fun Dialog.setDefaultWindowTheme() {
    window?.apply {
        setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        )
        addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        statusBarColor = Color.TRANSPARENT

        setSystemBarTextDark()
        setDimAmount(0.3f)
    }
}

fun getId(view: View): String? {
    return if (view.id == View.NO_ID) "no-id" else view.resources.getResourceName(view.id)
}


fun ImageView.loadByUrl(url: String?, errorImage: Int = R.drawable.error_data) {
    val options: RequestOptions =
        RequestOptions().centerCrop().placeholder(R.drawable.ic_loading_gif)
            .error(errorImage)
    Glide.with(this).load(url).apply(options).into(this)
}

fun ImageView.loadByUrl(url: Int?, errorImage: Int = R.drawable.error_data) {
    val options: RequestOptions =
        RequestOptions().centerCrop().placeholder(R.drawable.ic_loading_gif)
            .error(errorImage)
    Glide.with(this).load(url).apply(options).into(this)
}


fun ImageView.loadAvatar(url: String?) {
    val options: RequestOptions =
        RequestOptions().centerCrop().placeholder(R.drawable.ic_loading_gif)
            .error(R.drawable.ic_avatar_default).centerCrop()
    Glide.with(this).load(url).apply(options).circleCrop().into(this)
}

fun ImageView.setTintColor(color: Int) {
    setColorFilter(
        ContextCompat.getColor(
            this.context, color
        ), android.graphics.PorterDuff.Mode.SRC_IN
    )

}


fun ImageView.setTintColorString(color: String?) {
    color?.trim()?.let {
        try {
            ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(Color.parseColor(it)))
        } catch (e: Exception) {
            Timber.d(" setTintColorString : ${e.message}")
        }
    }
}

fun View.setBackgroundTint(color: Int) {
    ViewCompat.setBackgroundTintList(
        this, ColorStateList.valueOf(ContextCompat.getColor(this.context, color))
    )
}

fun View.setBackgroundTintString(color: String?) {
    color?.let {
        ViewCompat.setBackgroundTintList(
            this, ColorStateList.valueOf(Color.parseColor(it))
        )
    }

}

fun Context.getDrawableFromName(name: String?): Drawable? {
    return try {
        val resources: Resources = resources
        val resourceId: Int = resources.getIdentifier(
            name, "drawable",
            packageName
        )
        AppCompatResources.getDrawable(this, resourceId)
    } catch (e: NotFoundException) {
        null
    }
}

fun ImageView.loadByName(name: String) {
    setImageDrawable(this.context.getDrawableFromName(name))
}

fun RadioGroup.setList(list: List<String>, selected: String? = null) {
    val p = RadioGroup.LayoutParams(
        RadioGroup.LayoutParams.MATCH_PARENT,
        RadioGroup.LayoutParams.WRAP_CONTENT
    )
    p.updateMargins(0, 16, 0, 16)
    removeAllViews()
    list.forEachIndexed { index, s ->
        val radioBtn = AppCompatRadioButton(context)
        radioBtn.layoutParams = p
        radioBtn.id = index
        radioBtn.text = s
        if (s == selected) {
            radioBtn.isChecked = true
        }
        radioBtn.applyCiticsStyle()
        addView(radioBtn)
        val divider = MaterialDivider(context)
        addView(divider)
    }
}

fun RadioButton.applyCiticsStyle() {
    val colorStateList = ColorStateList(
        arrayOf(
            intArrayOf(-android.R.attr.state_checked), intArrayOf(
                android.R.attr.state_checked
            )
        ), intArrayOf(
            context.getColor(R.color.color_divider),  // Unchecked
            context.getColor(R.color.color_blue) // Checked
        )
    )
    buttonTintList = colorStateList
}


@ExperimentalCoroutinesApi
@CheckResult
fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow {
        Preconditions.checkMainThread()
        val listener = doOnTextChanged { text, _, _, _ -> trySend(text) }
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
fun EditText.searchDebounce(
    time: Long = 500,
    lifecycleScope: CoroutineScope,
    onChange: (String?) -> Unit
) {
    textChanges().debounce(time).onEach {
        onChange.invoke(it.toString())
    }.launchIn(lifecycleScope)
}


fun View.showBalloonPopup(
    content: String,
    xOff: Int = 0,
    resLayout: Int? = null
): Balloon? {
    if (content.isEmpty()) return null

    val balloon = context?.let {
        createBalloon(it) {
            setWidth(BalloonSizeSpec.WRAP)
            setHeight(BalloonSizeSpec.WRAP)
            setText(content)
            setTextColorResource(R.color.white)
            setTextSize(15f)
            setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            setPadding(12)
            setCornerRadius(8f)
            setIsVisibleArrow(false)
            setBackgroundColorResource(R.color.black)
            setBalloonAnimation(BalloonAnimation.FADE)
            setLifecycleOwner(lifecycleOwner)
            if (resLayout != null) {
                setLayout(resLayout)
            }
            build()
        }
    }
    balloon?.showAlignBottom(this, xOff)
    return balloon
}

fun View.delayOnLifecycle(
    durationInMillis: Long, dispatcher: CoroutineDispatcher = Dispatchers.Main, block: () -> Unit
): Job? = findViewTreeLifecycleOwner()?.let { lifecycleOwner ->
    lifecycleOwner.lifecycle.coroutineScope.launch(dispatcher) {
        delay(durationInMillis)
        block()
    }
}


fun ViewGroup.saveChildViewStates(): SparseArray<Parcelable> {
    val childViewStates = SparseArray<Parcelable>()
    children.forEach { child -> child.saveHierarchyState(childViewStates) }
    return childViewStates
}

fun ViewGroup.restoreChildViewStates(childViewStates: SparseArray<Parcelable>) {
    children.forEach { child -> child.restoreHierarchyState(childViewStates) }
}


fun ChipGroup.setListChip(
    listChip: MutableList<String>?,
    chipType: CustomChipLayout.ChipType = CustomChipLayout.ChipType.NORMAL
) {
    val newList = listChip?.filter { !TextUtils.isEmpty(it) }?.toMutableList() ?: mutableListOf()
    if (newList.size == 0) {
        newList.add("-")
    }
    removeAllViews()
    addChips(newList, chipType)
}

fun ChipGroup.addChips(
    listChip: MutableList<String>?,
    chipType: CustomChipLayout.ChipType = CustomChipLayout.ChipType.NORMAL
) {
    listChip?.forEach {
        val chip = CustomChipLayout(context)
        chip.setValue(it, chipType)
        addView(chip)
    }
}


fun ViewGroup.setDataColor(map: Map<String, Any>?) {
    forEach {
        if (it is TextViewUnit) {
            if (it.tag != null) {
                if (it.tag is String) {
                    it.setDataYellowColor(map?.get(it.tag))
                }
            }
        } else if (it is ThongTinLayout) {
            if (it.tag != null) {
                it.setDataYellowColor(map?.get(it.tag))
            }
        } else if (it is ViewGroup) {
            it.setDataColor(map)
        }
    }
}

fun ViewGroup.setDataColor(map: MutableList<String>?) {
    forEach {
        if (it is TextViewUnit) {
            if (it.tag != null) {
                if (it.tag is String) {
                    if (map?.contains(it.tag) == true) {
                        it.setTitleColor(R.color.color_red)
                    } else {
                        it.setTitleColor(R.color.color_text)
                    }
                }
            }
        } else if (it is ThongTinLayout) {
            if (it.tag != null) {
                if (map?.contains(it.tag) == true) {
                    it.setTitleColor(R.color.color_red)
                } else {
                    it.setTitleColor(R.color.color_text)
                }
            }
        } else if (it is ViewGroup) {
            it.setDataColor(map)
        }
    }
}

fun ViewGroup.setColorError(map: MutableList<String>? = null) {
    map?.let { keys ->
        forEach { view ->
//            if (view is CiticsTextField) {
//                if (view.tag != null) {
//                    if (keys.contains(view.tag)) {
//                        view.setState(true)
//                    } else {
//                        view.setState(false)
//                    }
//                }
//            } else if (view is CiticsTextChooser) {
//                if (view.tag != null) {
//                    if (keys.contains(view.tag)) {
//                        view.setState(true)
//                    } else {
//                        view.setState(false)
//                    }
//                }
//            } else if (view is ChooserWithChipsLayout) {
//                if (view.tag != null) {
//                    if (keys.contains(view.tag)) {
//                        view.setState(true)
//                    } else {
//                        view.setState(false)
//                    }
//                }
//            } else if (view is ViewGroup) {
//                view.setColorError(keys)
//            }
        }
    } ?: kotlin.run {
        forEach { view ->
//            if (view is CiticsTextField) {
//                if (view.tag != null) {
//                    view.setState(false)
//                }
//            } else if (view is CiticsTextChooser) {
//                if (view.tag != null) {
//                    view.setState(false)
//                }
//            } else if (view is ChooserWithChipsLayout) {
//                if (view.tag != null) {
//                    view.setState(false)
//                }
//            } else if (view is ViewGroup) {
//                view.setColorError()
//            }
        }
    }
}