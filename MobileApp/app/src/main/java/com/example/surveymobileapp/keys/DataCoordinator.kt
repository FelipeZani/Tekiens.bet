package com.example.surveymobileapp.keys

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DataCoordinator {
    companion object {
        val shared = DataCoordinator()
        const val identifier = "[DataCoordinator]"
    }

    var context: Context? = null

    var sampleJWTStringPreferenceVariable: String = ""
    val defaultSampleJWTStringPreferenceValue: String = ""

    private val JWT_NAME = "JWTToken"
    val Context.dataStore by preferencesDataStore(
        name = JWT_NAME
    )

    fun initialize(context: Context, onLoad: () -> Unit) {
        Log.i(
            "${DataCoordinator.identifier}",
            "Initializing..."
        )
        // Set Context
        this.context = context
        // Load DataStore Settings
        GlobalScope.launch(Dispatchers.Default) {
            // Update Sample String
            sampleJWTStringPreferenceVariable = getJWTStringDataStore()

            Log.i(
                "${DataCoordinator.identifier}",
                "initialized JWTString $sampleJWTStringPreferenceVariable."
            )
            // Callback
            onLoad()
        }
    }


}