package com.cryptoemergency.cryptoemergency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.cryptoemergency.cryptoemergency.navigation.Navigation
import com.cryptoemergency.cryptoemergency.providers.localNavController.NavControllerProvider
import com.cryptoemergency.cryptoemergency.providers.localSnackBar.SnackBarProvider
import com.cryptoemergency.cryptoemergency.providers.theme.MainThemeProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MainThemeProvider {
                NavControllerProvider {
                    SnackBarProvider {
                        Navigation()
                    }
                }
            }
        }
    }
}
