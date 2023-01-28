package com.app.develop.ransapp.local

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class PreferenceManager(private val context: Context) {

    private val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefSession by lazy {
        EncryptedSharedPreferences.create(
            context,
            CONST_PREFERENCE_SESSION,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    val prefIntro by lazy {
        EncryptedSharedPreferences.create(
            context,
            CONST_PREFERENCE_INTRO,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private val prefCredentials by lazy {
        EncryptedSharedPreferences.create(
            context,
            CONST_PREFERENCE_CREDENTIALS,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private val prefStatus by lazy {
        EncryptedSharedPreferences.create(
            context,
            CONST_PREFERENCE_STATUS,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private var editorSession: SharedPreferences.Editor = prefSession.edit()
    private var editorCredentials: SharedPreferences.Editor = prefCredentials.edit()
    private var editorStatus: SharedPreferences.Editor = prefStatus.edit()

    fun saveDataUser(data: String, token: String) {
        editorSession.putBoolean(CONST_KEY_IS_LOGIN, true)
        editorSession.putString(CONST_KEY_DATA_USER, data)
        editorSession.putString(CONST_KEY_TOKEN, token)
        editorSession.commit()
    }

    fun validateIsLogin(): Boolean {
        return prefSession.getBoolean(CONST_KEY_IS_LOGIN, false)
    }

    fun getDataUser(): String {
        return prefSession.getString(CONST_KEY_DATA_USER, String()).toString()
    }

    fun getToken(): String {
        return prefSession.getString(CONST_KEY_TOKEN, String())!!
    }

    fun clearDataPreference() {
        editorCredentials.clear().commit()
        editorStatus.clear().commit()
        editorSession.clear().commit()
    }

    companion object {
        const val CONST_PREFERENCE_SESSION = "PREFERENCE_SESSION"
        const val CONST_PREFERENCE_INTRO = "PREFERENCE_INTRO"
        const val CONST_PREFERENCE_CREDENTIALS = "PREFERENCE_CREDENTIALS"
        const val CONST_PREFERENCE_STATUS = "PREFERENCE_STATUS"

        const val CONST_KEY_IS_LOGIN = "KEY_IS_LOGIN"
        const val CONST_KEY_DATA_USER = "KEY_DATA_USER"
        const val CONST_KEY_TOKEN = "KEY_TOKEN"

    }

}