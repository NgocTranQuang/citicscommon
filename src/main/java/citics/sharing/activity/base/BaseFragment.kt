package citics.sharing.activity.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.viewbinding.ViewBinding
import com.citics.valuation.data.model.response.ErrorResponse
import citics.sharing.data.repository.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import citics.sharing.customview.HeaderLayout
import citics.sharing.extension.showBalloonPopup
import com.sharing.R

abstract class BaseFragment<V : ViewBinding, VM : BaseViewModel>(private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> V) :
    Fragment() {

    private var _binding: V? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    val binding get() = _binding!!
    abstract val viewModel: VM


//    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                // in here you can do logic when backPress is clicked
                onBackPress()
                isEnabled = false
                onBackHeader()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onConfigUI()
        onObserverData()
        onClickListener()
    }

    open fun onClickListener() {
        getHeaderLayout()?.let {
            it.onBackClickListener = {
                onBackHeader()
            }
        }
    }

    open fun onBackHeader() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

    open fun onBackPress() {

    }

    open fun onObserverData() {
    }

    open fun getHeaderLayout(): HeaderLayout? {
        return null
    }

    fun showErrorDialog(
        title: String? = getString(R.string.system_error),
        smg: String?
    ) {
        (requireActivity() as? BaseActivity<*, *>)?.showErrorDialog(title, smg)
    }

    fun showError(message: String) {
        (requireActivity() as? BaseActivity<*, *>)?.showError(message)
    }

    fun showSuccess(message: String) {
        (requireActivity() as? BaseActivity<*, *>)?.showSuccess(message)
    }

    fun showInfo(message: String) {
        (requireActivity() as? BaseActivity<*, *>)?.showInfo(message)
    }

    fun showLoading() {
        (requireActivity() as? BaseActivity<*, *>)?.showLoading()
    }

    fun hideLoading() {
        (requireActivity() as? BaseActivity<*, *>)?.hideLoading()
    }

    open fun onConfigUI() {
        (requireActivity() as? BaseActivity<*, *>)?.setupUI(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showBalloonPopup(content: String, view: View, xOff: Int = 0) {
        view.showBalloonPopup(content, xOff)
    }

    open fun dataListenerScope(listenerData: suspend CoroutineScope.() -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
//        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            listenerData.invoke(this)
//        }
        }
    }

    suspend fun <T> StateFlow<Resource<T>>.handleResponse(
        onLoading: (() -> Unit)? = null,
        onFail: (suspend ((ErrorResponse?) -> Unit))? = null,
        onSuccess: suspend (T?) -> Unit
    ) {
        (activity as BaseActivity<*, *>).apply {
            handleResponse(onLoading, onFail, onSuccess)
        }
    }

    suspend fun <T> StateFlow<Resource<T>>.handleResponseOnce(
        onLoading: (() -> Unit)? = null,
        onFail: (suspend ((ErrorResponse?) -> Unit))? = null,
        onSuccess: suspend (T?) -> Unit
    ) {

        (activity as BaseActivity<*, *>).apply {
            handleResponseOnce(onLoading, onFail, onSuccess)
        }
    }


    fun NavController.navigateWithAnimation(@IdRes resId: Int, args: Bundle? = null) {
        navigate(resId = resId, args = args, navOptions = getNavOptions())
    }


    private fun getNavOptions(): NavOptions {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in)
            .setExitAnim(R.anim.fade_out)
            .setPopEnterAnim(R.anim.fade_in)
            .setPopExitAnim(R.anim.slide_out)
            .build()
    }


}