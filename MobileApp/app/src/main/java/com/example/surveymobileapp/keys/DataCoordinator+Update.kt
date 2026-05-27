package com.example.surveymobileapp.keys

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// MARK: DataStore Update Functionality
fun DataCoordinator.updateJWTString(value: String) {
    this.sampleJWTStringPreferenceVariable = value
    GlobalScope.launch(Dispatchers.Default) {
        // Update DataStore
        setJWTStringDataStore(value)
        // OPTIONAL - Send Broadcast
        // Not included in this tutorial - consult the ReadMe to learn how to setup notifications to alert your system.
    }
}
