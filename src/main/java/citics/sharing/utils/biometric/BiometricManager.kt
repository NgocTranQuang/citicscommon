package citics.sharing.utils.biometric

import android.content.Context
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG

class BiometricManager protected constructor(biometricBuilder: BiometricBuilder) :
    BiometricManagerV23() {
    protected var mCancellationSignal = CancellationSignal()

    init {
        context = biometricBuilder.context
        title = biometricBuilder.title
        subtitle = biometricBuilder.subtitle
        description = biometricBuilder.description
        negativeButtonText = biometricBuilder.negativeButtonText
    }

    fun authenticate(biometricCallback: BiometricCallback) {

//        if(title == null) {
//            biometricCallback.onBiometricAuthenticationInternalError("Biometric Dialog title cannot be null");
//            return;
//        }
//
//
//        if(subtitle == null) {
//            biometricCallback.onBiometricAuthenticationInternalError("Biometric Dialog subtitle cannot be null");
//            return;
//        }
//
//
//        if(description == null) {
//            biometricCallback.onBiometricAuthenticationInternalError("Biometric Dialog description cannot be null");
//            return;
//        }
//
//        if(negativeButtonText == null) {
//            biometricCallback.onBiometricAuthenticationInternalError("Biometric Dialog negative button text cannot be null");
//            return;
//        }
        if (!BiometricUtils.isSdkVersionSupported) {
            biometricCallback.onSdkVersionNotSupported()
            return
        }
        if (!BiometricUtils.isPermissionGranted(context)) {
            biometricCallback.onBiometricAuthenticationPermissionNotGranted()
            return
        }
        if (!BiometricUtils.isHardwareSupported(context)) {
            biometricCallback.onBiometricAuthenticationNotSupported()
            return
        }
        if (!BiometricUtils.isFingerprintAvailable(context)) {
            biometricCallback.onBiometricAuthenticationNotAvailable()
            return
        }
        displayBiometricDialog(biometricCallback)
    }

    fun cancelAuthentication() {
        if (BiometricUtils.isBiometricPromptEnabled) {
            if (!mCancellationSignal.isCanceled) mCancellationSignal.cancel()
        } else {
            if (!mCancellationSignalV23.isCanceled) mCancellationSignalV23.cancel()
        }
    }

    private fun displayBiometricDialog(biometricCallback: BiometricCallback) {
        if (BiometricUtils.isBiometricPromptEnabled) {
            displayBiometricPrompt(biometricCallback)
        } else {
            displayBiometricPromptV23(biometricCallback)
        }
    }

    private fun displayBiometricPrompt(biometricCallback: BiometricCallback) {
        context?.let {
            val builder = BiometricPrompt.Builder(context)
                .setTitle(title ?: "")
                .setSubtitle(subtitle ?: "")
                .setDescription(description ?: "")
                .setNegativeButton(
                    negativeButtonText ?: "",
                    it.mainExecutor
                ) { dialogInterface, i -> biometricCallback.onAuthenticationCancelled() }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                builder.setAllowedAuthenticators(android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG)
            }
            builder.build()
                .authenticate(
                    mCancellationSignal, it.mainExecutor,
                    BiometricCallbackV28(biometricCallback)
                )
        }
    }

    class BiometricBuilder(val context: Context) {
        var title: String? = null
        var subtitle: String? = null
        var description: String? = null
        var negativeButtonText: String? = null
        fun setTitle(title: String): BiometricBuilder {
            this.title = title
            return this
        }

        fun setSubtitle(subtitle: String): BiometricBuilder {
            this.subtitle = subtitle
            return this
        }

        fun setDescription(description: String): BiometricBuilder {
            this.description = description
            return this
        }

        fun setNegativeButtonText(negativeButtonText: String): BiometricBuilder {
            this.negativeButtonText = negativeButtonText
            return this
        }

        fun build(): BiometricManager {
            return BiometricManager(this)
        }
    }
}