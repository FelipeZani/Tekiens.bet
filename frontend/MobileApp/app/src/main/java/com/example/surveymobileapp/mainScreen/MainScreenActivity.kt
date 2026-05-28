package com.example.surveymobileapp.mainScreen

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.surveymobileapp.keys.DataCoordinator
import com.example.surveymobileapp.keys.updateJWTString
import com.example.surveymobileapp.mainScreen.ui.theme.SurveyMobileAppTheme
import compose.icons.AllIcons
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Icons
import compose.icons.fontawesomeicons.solid.User
import kotlin.math.roundToInt

class MainScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SurveyMobileAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(innerPadding)
                }
            }
        }
        DataCoordinator.shared.updateJWTString("HELLO WORLD")
    }
}

@Composable
fun MainScreen(innerPadding : PaddingValues){
    Column(
        modifier = Modifier.padding(innerPadding)
            .fillMaxSize()
    ) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            IconButton(
                onClick = {}
            ) {
                Icon(
                    FontAwesomeIcons.Solid.User,
                    contentDescription = "User Icon")
            }
            Text(
                fontSize = 15.sp,
                text = "1000 PQ"
            )
            Spacer(modifier = Modifier.padding(end=10.dp))

        }
        LazyColumn(
            modifier = Modifier.padding(top = 20.dp)
        ) {
            items(arrayOf("Pomme de terre", "Patate", "Chou")){ item->
                Card(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(
                        100.dp
                    )
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()

                            .padding(top = 10.dp),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text(item, modifier = Modifier.weight(0.5f))
                       Column() {
                           HorizontalDivider(thickness = 2.dp)
                           Row(
                               modifier = Modifier.fillMaxWidth()
                                   .background(MaterialTheme.colorScheme.surface),
                                horizontalArrangement = Arrangement.Absolute.SpaceAround
                           ) {
                               Text(
                                   text = "#Tags #tags #tags",
                                   modifier = Modifier.padding(bottom = 10.dp)

                               )
                               Text("Ok")
                           }

                       }
                    }


                }
            }

        }
        DraggableButton()
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Composable
private fun DraggableButton() {
    var offset by remember { mutableStateOf(IntOffset.Zero) }
    val context = LocalContext.current
    Button(
        onClick = {},
        shape = RoundedCornerShape(50.dp),
        modifier = Modifier
            .offset { offset }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        val maxOffsetX = (context.resources.displayMetrics.widthPixels - size.width) / 2
                        val maxOffsetY = (context.resources.displayMetrics.heightPixels - size.height) / 2
                        offset = IntOffset(
                            (offset.x + dragAmount.x.roundToInt()).coerceIn(-maxOffsetX, maxOffsetX),
                            (offset.y + dragAmount.y.roundToInt()).coerceIn(-maxOffsetY, maxOffsetY)
                        )
                        change.consume()
                    }
                )
            }
    ) {
        Text(text = "Drag me")
    }
}

@Preview(showSystemUi = true)
@Composable
fun MainPreviewScreen(){
    MainScreen(PaddingValues(20.dp))
}