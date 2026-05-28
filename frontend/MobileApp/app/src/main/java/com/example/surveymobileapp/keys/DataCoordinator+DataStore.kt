package com.example.surveymobileapp.keys

import android.util.Log
import androidx.datastore.preferences.core.edit
import com.example.surveymobileapp.keys.DataCoordinator.Companion.identifier
import kotlinx.coroutines.flow.firstOrNull



suspend fun DataCoordinator.getJWTStringDataStore(): String {
    val context = this.context ?: return DataCoordinator.shared.defaultSampleJWTStringPreferenceValue
    return context.dataStore.data.firstOrNull()?.get(PreferenceKeys.jwt)
        ?: DataCoordinator.shared.defaultSampleJWTStringPreferenceValue
}

suspend fun DataCoordinator.setJWTStringDataStore(value: String) {
    val context = this.context ?: return
    Log.i(
        identifier,
        " Setting SampleDataStore."
    )
    context.dataStore.edit { preferences ->
        preferences[PreferenceKeys.jwt] = value
        Log.i(
            identifier,
            "Setting sampleDataStore, sample string : $value."
        )
    }
}
