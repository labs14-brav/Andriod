package com.thadocizn.brav.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by charles on 17,August,2019
 */
class SharedPreference(val context: Context) {

    private val PREFER_NAME = "userInfo"

    val sharePre: SharedPreferences = context.getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE)

    fun saveUserEmail(key: String, value: String) {

        val editor: SharedPreferences.Editor = sharePre.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun saveToken(key: String, value: String) {

        val editor: SharedPreferences.Editor = sharePre.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun saveUserId(key: String, value: Int) {

        val editor: SharedPreferences.Editor = sharePre.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getUserId(key: String): Int {

        return sharePre.getInt(key, 0)
    }

    fun getUserEmail(key: String): String? {

        return sharePre.getString(key, null)
    }

    fun getToken(key: String): String? {

        return sharePre.getString(key, null)
    }

    fun clear() {

        val editor: SharedPreferences.Editor = sharePre.edit()
        editor.clear()
        editor.apply()
    }

}