package citics.sharing.utils.biometric
import androidx.fragment.app.FragmentManager

interface BiometricCallback {
    fun onSdkVersionNotSupported()
    fun onBiometricAuthenticationNotSupported()
    fun onBiometricAuthenticationNotAvailable()
    fun onBiometricAuthenticationPermissionNotGranted()
    fun onBiometricAuthenticationInternalError(error: String?)
    fun onAuthenticationFailed()
    fun onAuthenticationCancelled()
    fun onAuthenticationSuccessful()
    fun onAuthenticationHelp(helpCode: Int, helpString: CharSequence?)
    fun onAuthenticationError(errorCode: Int, errString: CharSequence?)
    fun getFragmentMn(): FragmentManager?
}