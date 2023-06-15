package citics.sharing.activity.base

import android.hardware.biometrics.BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import citics.sharing.utils.biometric.BiometricCallback
import citics.sharing.utils.biometric.MyBiometricManager
import timber.log.Timber
import androidx.biometric.BiometricManager
import com.sharing.R


abstract class BaseBiometricFragment<T : ViewBinding, VM : BaseViewModel>(private val inflateMethod: (LayoutInflater, ViewGroup?, Boolean) -> T) :
    BaseFragment<T, VM>(inflateMethod), BiometricCallback {
    private var countInvalidFinger: Int = 0
    private var mMyBiometricManager: MyBiometricManager? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFingerPrinter()
    }

    private fun initFingerPrinter() {
        mMyBiometricManager =
            MyBiometricManager.BiometricBuilder(requireContext())
                .setDescription(getString(R.string.dbsthcb)).setTitle(getString(R.string.dbsthcact))
                .setNegativeButtonText(getString(R.string.skip))
                .build()
    }

    open fun showBiometricDialog() {
        mMyBiometricManager?.authenticate(this)
        countInvalidFinger = 0
    }

    override fun onSdkVersionNotSupported() {
        Timber.d("onSdkVersionNotSupported")

    }

    override fun onBiometricAuthenticationNotSupported() {
        Timber.d("onBiometricAuthenticationNotSupported")

    }

    override fun onBiometricAuthenticationNotAvailable() {
        Timber.d("onBiometricAuthenticationNotAvailable")

    }

    override fun onBiometricAuthenticationPermissionNotGranted() {
        Timber.d("onBiometricAuthenticationPermissionNotGranted")

    }

    override fun onBiometricAuthenticationInternalError(error: String?) {
        Timber.d("onBiometricAuthenticationInternalError")

    }

    override fun onAuthenticationFailed() {
        Timber.d("onAuthenticationFailed")

        countInvalidFinger++
        if (countInvalidFinger == 3) {
            onAuthenticationFailed3Time()
            countInvalidFinger = 0
        }
    }

    override fun onAuthenticationCancelled() {
        Timber.d("onAuthenticationCancelled")

    }

    override fun onAuthenticationSuccessful() {
        Timber.d("onAuthenticationSuccessful")

    }

    override fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?) {
        Timber.d("onAuthenticationHelp")

    }

    override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
        Timber.d("onAuthenticationError ${errorCode} || ${errString}")
        if (errorCode == 10) {
            onAuthenticationCancelByUser()
        }
    }

    override fun getFragmentMn(): FragmentManager? {
        return childFragmentManager
    }

    open fun onAuthenticationFailed3Time() {

    }

    open fun onAuthenticationCancelByUser() {

    }

    fun hasUserConfiguredBiometric(): Boolean {
        val myBiometricManager = BiometricManager.from(requireContext())
        fun queryBiometricStatusFromDevice(): Int =
            myBiometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_WEAK)
        return when (queryBiometricStatusFromDevice()) {
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> false
            else -> true
        }
    }

}