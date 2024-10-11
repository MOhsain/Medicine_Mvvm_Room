package com.test.medmvvm.utils

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefsHelper @Inject
constructor(private val mSharedPreferences: SharedPreferences) {

    private val LANGUAGE = "kalam_user_language"
    private val CALL_QUALITY = "kalam_call_quality"
    private val SELECT_AUTO = "kalam_translate_data"
    private val AUDIO_SPEED = "kalam_audio_speed"
    private val KEY_IS_NEW_FCM = "key_kalam_is_new_FCM"
    private val APP_LANGUAGE = "kalam_application_language"
    private val SHOW_CONTACT_DIALOG = "kalam_contact_permission"
    private val IS_AUTO_DOWNLOAD = "is_auto_download"
    private val SHOWCASE_STEPS = "kalam_showcase_steps"
    private val PRIVATE_CHAT_DILOAG = "kalam_show_private_chat_dialog"
    private val CHECK_CAMERA_PERMISSION = "kalam_camera_permission"
    private val DRAFT_MSG = "kalam_draft_msg_"
    private val LATES_STORY_TIME = "kalam_lates_story_time"
    private val ENCRYPTION_KEY = "encryption_key_aes"
    private val NOTIFICATIONS_COUNT = "notifications_count"
    private val GET_PENDING_ACTION_API_KEY = "get_pending_action_api_key"


    private fun put(key: String, value: String) {
        mSharedPreferences.edit().putString(key, value).apply()
    }

    fun put(key: String, value: Int) {
        mSharedPreferences.edit().putInt(key, value).apply()
    }

    private fun put(key: String, value: Boolean) {
        mSharedPreferences.edit().putBoolean(key, value).apply()
    }

    operator fun get(key: String, defaultValue: String): String? {
        return mSharedPreferences.getString(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Int): Int? {
        return mSharedPreferences.getInt(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Float): Float? {
        return mSharedPreferences.getFloat(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Boolean): Boolean? {
        return mSharedPreferences.getBoolean(key, defaultValue)
    }

    fun saveIsNewFcmToken(isNewToken: Boolean) {
        put(KEY_IS_NEW_FCM, isNewToken)
    }

    fun isNewFcmToken(): Boolean? {
        return get(KEY_IS_NEW_FCM, false)
    }

//    fun saveFCMToken(fcmToken: String) {
//        put(AppConstants.FCM_TOKEN, fcmToken)
//    }
//
//    fun saveUserLogin(boolean: Boolean) {
//        put(AppConstants.KEY_IS_LOGIN, boolean)
//    }
//
//    fun getFCMToken(): String? {
//        return get(AppConstants.FCM_TOKEN, "")
//    }
//
//    fun isLoggedIn(): Boolean {
//        return get(AppConstants.KEY_IS_LOGIN, false) ?: false
//    }
//
//    fun setUser(user: UserData?) {
//        put(AppConstants.KEY_IS_LOGIN, true)
//        val json = Gson().toJson(user)
//        put(AppConstants.KEY_USER_OBJECT, json)
//    }
//
//
//    fun getUser(): UserData? {
//        return try {
//            val json = get(AppConstants.KEY_USER_OBJECT, "")
//            Gson().fromJson(json, UserData::class.java)
//        } catch (e: Exception) {
//            null
//        }
//    }

    fun saveLanguage(language: String) {
        put(LANGUAGE, language)
    }

    fun getLanguage(): String? {
        return get(LANGUAGE, "")
    }

    fun saveApplicationLanguage(language: String) {
        put(APP_LANGUAGE, language)
    }

    fun getApplicationLanguage(): String? {
        return get(APP_LANGUAGE, "")
    }

    fun saveTranslateState(translate: Int) {
        put(SELECT_AUTO, translate)
    }

    fun changeAudioSpeed(): Double {
        val incrementedValue = when (get(AUDIO_SPEED, "1.0")?.toFloat()) {
            1f -> {
                "1.5"
            }

            1.5f -> {
                "2.0"
            }

            2f -> {
                "2.5"
            }

            2.5f -> {
                "3.0"
            }

            else -> {
                "1.0"
            }
        }
        Debugger.wtf("changeAudioSpeed", "incrementedValue: $incrementedValue")
        put(AUDIO_SPEED, incrementedValue)
        return incrementedValue.toDouble()
    }

    fun getAudioSpeed(): Float {
        return get(AUDIO_SPEED, "1")?.toFloat() ?: 1.0f

    }

    fun getTransState(): Int? {
        return get(SELECT_AUTO, 0)
    }

    fun saveCallQuality(type: String) {
        put(CALL_QUALITY, type)
    }

    fun getCallQuality(): String? {
        return get(CALL_QUALITY, "")
    }

    fun setAutoDownloadForAllChats(autoDownload: Boolean) {
        put(IS_AUTO_DOWNLOAD, autoDownload)
    }

    fun isAutoDownloadForAllChats(): Boolean? {
        return get(IS_AUTO_DOWNLOAD, true)
    }

    fun getContactPermission(): Boolean? {
        return get(SHOW_CONTACT_DIALOG, true)
    }

    fun setContactPermission(check: Boolean) {
        put(SHOW_CONTACT_DIALOG, check)
    }

    fun getShowCaseSteps(): Boolean? {
        return get(SHOWCASE_STEPS, true)
    }

    fun getShowPrivateChatDialog(): Boolean? {
        return get(PRIVATE_CHAT_DILOAG, true)
    }

    fun setShowCaseSteps(check: Boolean) {
        put(SHOWCASE_STEPS, check)
    }

    fun setShowPrivateChatDialog(check: Boolean) {
        put(PRIVATE_CHAT_DILOAG, check)
    }

    fun getFirstTimeCameraPer(): Boolean? {
        return get(CHECK_CAMERA_PERMISSION, true)
    }

    fun setFirstTimeCameraPer(check: Boolean) {
        put(CHECK_CAMERA_PERMISSION, check)
    }

    fun getDraftMessageOfChat(chatId: String): String? {
        return get("$DRAFT_MSG$chatId", "")
    }

    fun setDraftMessageOfChat(chatId: String, draftMsg: String) {
        if (draftMsg.isBlank() ||
            draftMsg.isEmpty()
        ) {
            put("$DRAFT_MSG$chatId", "")
        } else {
            put("$DRAFT_MSG$chatId", draftMsg)
        }
    }

    fun saveLatestStoryTime(time: String) {
        put(LATES_STORY_TIME, time)
    }

    fun getLatestStoryTime(): String? {
        return get(LATES_STORY_TIME, "")
    }

    fun saveEncryptionKey(key: String) {
        put(ENCRYPTION_KEY, key)
    }

    fun getEncryptionKey(): String? {
        return get(ENCRYPTION_KEY, "")
    }


    fun saveNotificationsCount(count: Int) {
        put(NOTIFICATIONS_COUNT, count)
    }

    fun getNotificationsCount(): Int? {
        return get(NOTIFICATIONS_COUNT, 0)
    }

    fun setPendingActionApiRunning(value: Boolean) {
        put(GET_PENDING_ACTION_API_KEY, value)
    }

    fun isPendingActionApiRunning(): Boolean? {
        return get(GET_PENDING_ACTION_API_KEY, false)
    }
}
