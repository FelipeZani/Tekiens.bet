package com.example.surveymobileapp.auth

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow



class AuthVM : ViewModel() {

    private val _showPwdFlow = MutableStateFlow(false)
    private val _showingLoginMenuFlow = MutableStateFlow(false)
    private val _nameLastString = MutableStateFlow("")
    private val _emailString = MutableStateFlow("")
    private val _passwordString = MutableStateFlow("")

    //Expose state flows to the UI
    var nameLastString = _nameLastString.asStateFlow()
    val emailString = _emailString.asStateFlow()
    val passwordString = _passwordString.asStateFlow()
    val showingPwdStateFlow = _showPwdFlow.asStateFlow()
    val showingLoginMenuStateFlow = _showingLoginMenuFlow.asStateFlow()


    fun updateNameLastString(value : String){
        this._nameLastString.value=value
    }
    fun updateEmailString(value : String){
        this._emailString.value=value
    }
    fun updatePasswordString(value : String){
        this._passwordString.value=value
    }
    fun checkAttributesValidity() : Boolean{

        println("Checking user credentials for SignUp") //
        val nameValidationRegex = "([A-Z][a-z]+)(\\s{0,1}[d'-]*[A-Z][a-z]+)*".toRegex()
        val emailValidationRegex = """^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$""".toRegex()
        val passwordValidationRegex = """^((?=\S*?[A-Z])(?=\S*?[a-z])(?=\S*?[0-9]).{6,})\S$""".toRegex()

        if(showingLoginMenuStateFlow.value == false) {
            if (! _nameLastString.value.matches(nameValidationRegex)) {
                println("Input name doesn't match regex in AuthVM, value ${_nameLastString.value} " )
                return false
            }
            println("Name approved")

        }

        if (! _emailString.value.matches(emailValidationRegex)) {
            println("Input email doesn't match regex in AuthVM, value ${_emailString.value} ")
            return false
        }
        println("Name approved")

        if(!_passwordString.value.matches(passwordValidationRegex)) {
            println("Input password doesn't match regex in AuthVM, value ${_passwordString.value} ")
            return false
        }
        println("PWD approved")

        println("Credentials passed SupTest")
        return true


    }
    fun togglePwdVisibility(){
        this._showPwdFlow.value = !this._showPwdFlow.value
    }

    fun toggleAuthMenu(){
        this._showingLoginMenuFlow.value=!this._showingLoginMenuFlow.value
    }

}