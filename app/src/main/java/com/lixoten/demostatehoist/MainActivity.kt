package com.lixoten.demostatehoist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lixoten.demostatehoist.ui.theme.DemoStateHoistTheme
import com.lixoten.demostatehoist.ui.theme.Purple700

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoStateHoistTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DemoApp()
                }
            }
        }
    }
}


@Composable
fun MyTopBar() {
    TopAppBar {
        Text(text = "State Hoisting")
    }
}

@Composable
fun DemoApp() {
    Scaffold(
        topBar = { MyTopBar() }
    ) {
        DemoScreen()
    }
}

@Composable
fun DemoScreen() {
    // TextField B used State Hoist
    // TextField A does not

    var foo by remember { mutableStateOf("") }
    var counter by remember { mutableStateOf(0) }
    Column {
        TextFieldA()

        Divider(thickness = 4.dp, color = Purple700, modifier = Modifier.padding(16.dp))

        TextFieldB(
            value = foo,
            onValueChange = { foo = it }
        )

        Divider(thickness = 4.dp, color = Purple700, modifier = Modifier.padding(16.dp))

        ClickMe(
            onClickMe = { counter++ }
        )
        Divider(thickness = 4.dp, color = Purple700, modifier = Modifier.padding(16.dp))


        Text("Hoisted value can be passed around and displayed outside")
        Text(
            "Hoisted value foo: $foo",
            style = MaterialTheme.typography.h5
        )
        Text(
            "Hoisted Counter:  $counter",
            style = MaterialTheme.typography.h5
        )
    }
}


@Composable
fun TextFieldA() {
    var foo2 by remember { mutableStateOf("") }

    Column {
        TextField(
            value = foo2,
            onValueChange = { foo2 = it },
            label = { Text(text = "Enter a Value for A (not hoisted)") }
        )
    }
}

@Composable
fun TextFieldB(
    value: String,
    onValueChange: (String) -> Unit
) {
    Column {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = "Enter a Value for B (Hoisted)") }
        )
    }
}

@Composable
fun ClickMe(
    onClickMe: () -> Unit
) {
    // var counter by remember { mutableStateOf(0) }
    Button(
        onClick = onClickMe
    ) {
        Text(text = "Click me")
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DemoStateHoistTheme {
        DemoApp()
    }
}