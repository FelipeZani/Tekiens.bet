package com.example.surveymobileapp.auth.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.surveymobileapp.UserLogin
import com.example.surveymobileapp.UserSignup

import com.example.surveymobileapp.auth.AuthVM
import com.example.surveymobileapp.client.KtorClient
import com.example.surveymobileapp.client.getResponseContent
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Regular
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.regular.Eye
import compose.icons.fontawesomeicons.regular.EyeSlash
import compose.icons.fontawesomeicons.solid.ArrowRight
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@Composable
fun LogInUi(paddingValue: PaddingValues, viewModel : AuthVM){
    val client = KtorClient(HttpClient(CIO){
        install(ContentNegotiation){
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValue)
            .padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement
                .spacedBy(20.dp, Alignment.CenterHorizontally) as Arrangement.Vertical,

    ) {

        val emailTextFieldState = rememberTextFieldState(viewModel.emailString.collectAsState().value)
        val passwordTextFieldState = rememberTextFieldState(viewModel.passwordString.collectAsState().value)

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(fontSize = 30.sp,text="Login")
        }

        TextField(
            state = emailTextFieldState,
            label = { Text("Email") },

        )


        val toggledIcon = remember{mutableStateOf(false)}
        SecureTextField(
            textObfuscationMode = if(toggledIcon.value) TextObfuscationMode.Visible else TextObfuscationMode.Hidden,
            state = passwordTextFieldState,
            label = { Text("password") },
            trailingIcon = { IconButton(modifier = Modifier.size(20.dp),
                onClick = {
                    toggledIcon.value = !toggledIcon.value

                },
                content = {Icon(

                    imageVector = if(!toggledIcon.value) FontAwesomeIcons.Regular.Eye else FontAwesomeIcons.Regular.EyeSlash,
                    contentDescription = "Clickable Icon to toggle/show password")
                },
            )
            }
        )


        Button(
            onClick = {
                println("Checking credentials validity...")
                viewModel.updateEmailString(emailTextFieldState.text.toString())
                viewModel.updatePasswordString(passwordTextFieldState.text.toString())

                if(viewModel.checkAttributesValidity() == true){
                    val user = UserLogin(
                        viewModel.emailString.value,
                        viewModel.passwordString.value)
                    println(user)
                    CoroutineScope(Dispatchers.IO).launch{
                        val response = client.login("http://192.168.1.37:8080/session/login",
                           user
                        )
                        val responseContent: String? = getResponseContent(response)


                        val dataString = responseContent?.let{ content ->

                            Json.decodeFromString<UserSignup>(content)
                        }

                        if( dataString ==  null){
                            //handle error
                        }

                    }
                }
            },
            modifier = Modifier.size(200.dp, 50.dp)) {
            Text(fontSize = 18.sp, text = "LogIn")
        }
        Row(
            modifier = Modifier.clickable{
                viewModel.toggleAuthMenu()

            }
        ) {
            Text("Try To SignUp")

            Icon(
                FontAwesomeIcons.Solid.ArrowRight,
                modifier = Modifier.size(20.dp),
                contentDescription = "Arrow to allow user to switch authentification menu"
            )
        }
    }

}


@Composable
@Preview(showSystemUi = true)
fun LogInUiPreview(){
    LogInUi(PaddingValues(20.dp), viewModel())
}