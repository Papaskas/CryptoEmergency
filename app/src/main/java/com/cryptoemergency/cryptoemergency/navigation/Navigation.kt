package com.cryptoemergency.cryptoemergency.navigation

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.Text
import com.cryptoemergency.cryptoemergency.R
import com.cryptoemergency.cryptoemergency.providers.localNavController.LocalNavController
import com.cryptoemergency.cryptoemergency.ui.common.Screen
import com.cryptoemergency.cryptoemergency.ui.common.pinCode.domain.PinCodeCreateViewModel
import com.cryptoemergency.cryptoemergency.ui.common.pinCode.domain.PinCodeEnterViewModel
import com.cryptoemergency.cryptoemergency.ui.common.pinCode.ui.PinCodeTemplate

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
    val vm: PinCodeEnterViewModel = hiltViewModel()

    Screen {
        Column(Modifier.padding(it)) {
            PinCodeTemplate(
                viewModel = vm,
                rightSpecialButton = {
                    IconButton({ vm.onDeleteLastCode() }) {
                        Icon(
                            painter = painterResource(R.drawable.close),
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    }
}
