package com.cryptoemergency.cryptoemergency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptoemergency.cryptoemergency.navigation.Navigation
import com.cryptoemergency.cryptoemergency.providers.localNavController.NavControllerProvider
import com.cryptoemergency.cryptoemergency.providers.localSnackBar.SnackBarProvider
import com.cryptoemergency.cryptoemergency.providers.theme.ThemeProvider
import com.cryptoemergency.cryptoemergency.providers.theme.viewModels.ThemeViewModelImpl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val viewModel: ThemeViewModelImpl = hiltViewModel()

            ThemeProvider(viewModel) {
                NavControllerProvider {
                    SnackBarProvider {
                        Navigation()
                    }
                }
            }
        }
    }
}
