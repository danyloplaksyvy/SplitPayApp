package com.example.splitpayapp.presentation.data.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStoreManager: DataStore<Preferences> by preferencesDataStore("settings")

@Singleton
class DataStoreManager @Inject constructor(@ApplicationContext val context: Context) {
    private val dataStoreManager = context.dataStoreManager

    private object dataStoreKeys {
        val APP_ENTRY_KEY = booleanPreferencesKey("app_entry")
    }

    val readAppEntry = dataStoreManager.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preferences ->
        preferences[dataStoreKeys.APP_ENTRY_KEY] ?: true
    }

    suspend fun saveAppEntry() {
        dataStoreManager.edit { preferences ->
            preferences[dataStoreKeys.APP_ENTRY_KEY] = false
        }
    }
}