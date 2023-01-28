package com.app.develop.ransapp.base.core

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.app.develop.ransapp.R
import java.io.IOException
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.Certificate
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

@RequiresApi(Build.VERSION_CODES.M)
fun Context.hasInternet() = !this.isAirplaneModeActive() && this.isConnected()

fun Context.isAirplaneModeActive(): Boolean {
    return Settings.Global.getInt(contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
}

@RequiresApi(Build.VERSION_CODES.M)
fun Context.isConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.getNetworkCapabilities(cm.activeNetwork)?.hasCapability((NetworkCapabilities.NET_CAPABILITY_INTERNET)) ?: false
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.screenSize(sizeType: CoreSizeType) : Int {
    val displayMetrics = resources.displayMetrics
    return if (sizeType == CoreSizeType.HEIGHT) displayMetrics.heightPixels else displayMetrics.widthPixels
}


@Throws(
    CertificateException::class,
    IOException::class,
    KeyStoreException::class,
    NoSuchAlgorithmException::class,
    KeyManagementException::class
)
fun Context.configSSLConfig(type: SSLConfigType): SSLContext? {
    val cf: CertificateFactory? = CertificateFactory.getInstance("X.509")
    var ca: Certificate? = null
    try {
        /*val certificate: Int = when(type){
            SSLConfigType.MI_CHECK-> R.raw.micheck
            SSLConfigType.AGREEMENT->R.raw.agreement
            SSLConfigType.DOCUMENTS->R.raw.documents
        }
        this.resources.openRawResource(certificate)
            .use { cert -> ca = cf?.generateCertificate(cert) }*/
    } catch (e: Exception) {
        println(e.message)
    }
    val keyStoreType = KeyStore.getDefaultType()
    val keyStore = KeyStore.getInstance(keyStoreType)
    keyStore.load(null, null)
    keyStore.setCertificateEntry("ca", ca)
    val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
    val tmf =
        TrustManagerFactory.getInstance(tmfAlgorithm)
    tmf.init(keyStore)
    val sslContext = SSLContext.getInstance("TLS")
    sslContext.init(null, tmf.trustManagers, null)
    return sslContext
}

fun configTrustManager(): X509TrustManager? {
    val trustManagerFactory: TrustManagerFactory?
    return try {
        trustManagerFactory =
            TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        trustManagerFactory.init(null as KeyStore?)
        val trustManagers: Array<TrustManager> = trustManagerFactory.trustManagers
        if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
            null
        } else trustManagers[0] as X509TrustManager
    } catch (e: NoSuchAlgorithmException) {
        println("NoSuchAlgorithmException ${e.message}")
        null
    } catch (e: KeyStoreException) {
        println("KeyStoreException ${e.message}")
        null
    }
}

enum class CoreSizeType {
    HEIGHT,
    WIDTH;
}