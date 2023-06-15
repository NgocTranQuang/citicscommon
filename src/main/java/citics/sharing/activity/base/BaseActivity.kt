package citics.sharing.activity.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.citics.valuation.data.model.response.ErrorResponse
import citics.sharing.data.repository.Resource
import citics.sharing.dialog.LoadingDialog
import citics.sharing.dialog.NormalDialog
import citics.sharing.utils.*
import com.sharing.R
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseActivity<V : ViewBinding, VM : BaseViewModel> : AnalyticActivity() {
    lateinit var binding: V
    abstract val viewModel: VM
    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflater(layoutInflater)
        setContentView(binding.root)
        onConfigUI()
        onObserverData()
        onClickListener()
    }

    open fun onClickListener() {

    }

    open fun onObserverData() {
        dataListenerScope {
            viewModel.showErrorDialog.collect {
                it?.let {
                    showErrorDialog(it.title, it.message)
                }
            }
        }
        dataListenerScope {
            viewModel.showLoading.collect {
                if (it) {
                    showLoading()
                } else {
                    hideLoading()
                }
            }
        }
        dataListenerScope {
            viewModel.showErrorHetCPoint.collect {
                it?.let {
                    DialogUtils.showDialogHetCPoint(this@BaseActivity, supportFragmentManager)
                }
            }
        }
    }

    open fun onConfigUI() {
        setupUI(binding.root)
    }

    fun showError(message: String) {
        Toasty.error(this, message, Toast.LENGTH_SHORT, true).show()
    }

    fun showSuccess(message: String) {
        Toasty.success(this, message, Toast.LENGTH_SHORT, true).show()
    }

    fun showInfo(message: String) {
        Toasty.info(this, message, Toast.LENGTH_SHORT, true).show()
    }

    fun showLoading() {
        if (loadingDialog?.isShowing() == true) return
        loadingDialog = LoadingDialog.getInstance()
        loadingDialog?.show(this.supportFragmentManager, LoadingDialog.TAG)
    }

    fun hideLoading() {
        if (loadingDialog?.isShowing() == true) {
            loadingDialog?.dismiss()
            loadingDialog = null
        }
    }


    open fun dataListenerScope(listenerData: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch(Dispatchers.Main) {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                listenerData.invoke(this)
            }
        }
    }


    fun showErrorDialog(
        title: String? = getString(R.string.system_error),
        smg: String?
    ) {
        var newTitle = title
        if (TextUtils.isEmpty(title)) {
            newTitle = getString(R.string.system_error)
        }
        let {
            NormalDialog.Builder(it).setImage(R.drawable.ic_warning, DialogType.ERROR)
                .setTitle(newTitle.toString()).setMessage(smg ?: "")
                .setPositiveButton(R.string.i_got_it) { dialogInterface, _ ->
                    dialogInterface.dismiss()
                }.show(it.supportFragmentManager)
        }
    }

    suspend fun <T> StateFlow<Resource<T>>.handleResponse(
        onLoading: (() -> Unit)? = null,
        onFail: (suspend ((ErrorResponse?) -> Unit))? = null,
        onSuccess: suspend (T?) -> Unit
    ) {
        collect {
            when (it) {
                is Resource.Loading -> {
                    onLoading?.invoke()
                }
                is Resource.Failure -> {
                    hideLoading()
                    if (it.error?.code == SERVER_CODE_INVALID_C_POINT_408 || it.error?.code == SERVER_CODE_INVALID_C_POINT_409) {
                        (this as MutableStateFlow<Resource<T>>).emit(Resource.None())
                        DialogUtils.showDialogHetCPoint(this@BaseActivity, supportFragmentManager)
                    } else if (it.error?.code == SERVER_CODE_LOGIN_IN_NEW_DEVICE) {
                        (this as MutableStateFlow<Resource<T>>).emit(Resource.None())
                        onLoginInNewDevice(it.error)
                    } else {
                        if (onFail != null) {
                            onFail.invoke(it.error)
                        } else {
                            showErrorDialog(it.error?.title, it.error?.message)
                        }
                    }
                }

                is Resource.Success -> {
                    onSuccess.invoke(it.data)
                }
                is Resource.None -> {

                }
            }
        }
    }

    open fun onLoginInNewDevice(errorResponse: ErrorResponse?) {
        // to override
//        val mIntent = Intent(this@BaseActivity, LoginActivity::class.java)
//        mIntent.putExtra(
//            LoginActivity.KEY_LOGIN_IN_NEW_DEVICE_MESSAGE,
//            errorResponse?.message
//        )
//        mIntent.putExtra(
//            LoginActivity.KEY_LOGIN_IN_NEW_DEVICE_TITLE,
//            errorResponse?.title
//        )
//        mIntent.flags =
//            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        startActivity(mIntent)
    }

    suspend fun <T> StateFlow<Resource<T>>.handleResponseOnce(
        onLoading: (() -> Unit)? = null,
        onFail: (suspend ((ErrorResponse?) -> Unit))? = null,
        onSuccess: suspend (T?) -> Unit
    ) {
        handleResponse(onLoading, onFail = {
            onFail?.invoke(it) ?: kotlin.run {
                hideLoading()
                showErrorDialog(it?.title, it?.message)
            }
            (this as MutableStateFlow<Resource<T>>).emit(Resource.None())
        }) {
            onSuccess.invoke(it)
            (this as MutableStateFlow<Resource<T>>).emit(Resource.None())
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    open fun setupUI(view: View?) {
        try {
            // Set up touch listener for non-text box views to hide keyboard.
            if (view !is EditText) {
                view?.setOnTouchListener { v, event ->
                    this.run {
                        hideSoftKeyboard(this)
                    }
                    false
                }
            }

            //If a layout container, iterate over children and seed recursion.
            if (view is ViewGroup) {
                for (i in 0 until view.childCount) {
                    val innerView = view.getChildAt(i)
                    setupUI(innerView)
                }
            }

        } catch (e: Exception) {
            Timber.d("${e.message}")
        }

    }

    open fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        if (inputMethodManager.isAcceptingText) {
            inputMethodManager.hideSoftInputFromWindow(
                activity.currentFocus?.windowToken,
                0
            )
        }
    }
    fun finishMe(){
        finishMeWithResult(0, null)
    }

    fun finishMeWithResult(code: Int, data: Intent?){
        if(code != 0) setResult(code, data)
        finish()
    }


    fun setDataResult(bundle: Bundle) {
        val intent = Intent()
        intent.putExtra(KEY_BUNDLE_DATA, bundle)
        setResult(RESULT_OK, intent);
    }

    abstract val bindingInflater: (LayoutInflater) -> V

}