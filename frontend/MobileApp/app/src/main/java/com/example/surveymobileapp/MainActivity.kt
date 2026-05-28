package com.example.surveymobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.surveymobileapp.auth.AuthVM
import com.example.surveymobileapp.auth.view.LogInUi
import com.example.surveymobileapp.auth.view.SingUpUi
import com.example.surveymobileapp.keys.DataCoordinator
import com.example.surveymobileapp.keys.updateJWTString
import com.example.surveymobileapp.ui.theme.SurveyMobileAppTheme
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        DataCoordinator.shared.initialize(context = this,
            {println("HELLO HEREEEE")}
        )
        setContent {
            SurveyMobileAppTheme {
                val authVM = viewModel<AuthVM>()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    if(authVM.showingLoginMenuStateFlow.collectAsState().value == true){
                        LogInUi(innerPadding, authVM)
                        println("Inside SignIn")
                    }else{
                        SingUpUi(innerPadding, authVM)
                        println("Inside SignIn")

                    }
                }
            }
        }
    }
}


@OptIn(DelicateCoroutinesApi::class)
@Composable
fun RequestSenderUi(paddingValues: PaddingValues){
    //principal content
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                })
            }
        }
        Text("This Text is a placeholder text")
        Button(
            onClick = {
                CoroutineScope(Dispatchers.Default).launch{
/*
                    client.login("/user/login",User("Blitz","toto@tat.com","123"))
*/
                }
            }
        ) {Text("Click Here") }
    }


}
@Preview(showSystemUi = true)
@Composable
fun RequestSenderPreview() {
    SurveyMobileAppTheme {
        RequestSenderUi(PaddingValues(24.dp))
    }
}