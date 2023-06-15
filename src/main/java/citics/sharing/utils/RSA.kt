package citics.sharing.utils

import android.util.Base64
import com.citics.cbank.BuildConfig
import java.security.Key
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

/**
 * Created by ChinhQT on 02/10/2022.
 */
object RSA {
    fun encrypt(data: String, publicKey: Key?): String {
        val cipher: Cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val bytes = cipher.doFinal(data.toByteArray())
        return Base64.encodeToString(bytes, Base64.NO_WRAP)
    }

    fun getPublicKey(): PublicKey {
        //The string is converted into a key pair object
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePublic(
            X509EncodedKeySpec(
                Base64.decode(
                    BuildConfig.KEY_LOGIN, Base64.DEFAULT
                )
            )
        )
    }

    fun getBioPublicKey(): PublicKey {
        //The string is converted into a key pair object
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePublic(
            X509EncodedKeySpec(
                Base64.decode(
                    BuildConfig.KEY_BIO, Base64.DEFAULT
                )
            )
        )
    }
}