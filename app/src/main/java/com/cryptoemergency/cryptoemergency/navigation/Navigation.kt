package com.cryptoemergency.cryptoemergency.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.ui.common.Screen
import com.cryptoemergency.cryptoemergency.ui.common.inputs.DoublePasswordsInput
import com.cryptoemergency.cryptoemergency.ui.common.inputs.EmailInput

@Composable
fun Navigation() {
    val navController = LocalNavController.current
    val context = LocalContext.current

//    val token by produceState<String?>(null) {
//        value = Store(Keys.TOKEN, context).get()
//    }

    TempPage()

//    if (token != null) {
//        NavHost(
//            navController = navController,
//            startDestination =
//            if (token != Keys.TOKEN.defaultValue) Destination.Home.Home
//            else Destination.Auth.Login,
//            enterTransition = { fadeIn(animationSpec = tween(200)) },
//        ) {
//            homeGraphs()
//            authGraphs()
//            pageGraphs()
//        }
//    }
}

@Composable
private fun TempPage() {
    val values = Pair(
        remember { mutableStateOf(TextFieldValue()) },
        remember { mutableStateOf(TextFieldValue()) },
    )

    val a = remember { mutableStateOf(TextFieldValue()) }
    val b = remember { mutableStateOf(false) }

    Screen {
        Column(
            Modifier.padding(it)
        ) {
            //EmailInput(value = a, hasError = b)
            DoublePasswordsInput(values)
        }
    }
}
