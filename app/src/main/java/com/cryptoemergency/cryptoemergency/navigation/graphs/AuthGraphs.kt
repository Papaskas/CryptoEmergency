package com.cryptoemergency.cryptoemergency.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.cryptoemergency.cryptoemergency.navigation.Destination
import com.cryptoemergency.cryptoemergency.ui.screens.auth.changeProfileData.ChangeProfileDataScreen
import com.cryptoemergency.cryptoemergency.ui.screens.auth.login.LoginScreen
import com.cryptoemergency.cryptoemergency.ui.screens.auth.profile.ProfileScreen

fun NavGraphBuilder.authGraphs() {
    composable<Destination.Auth.Profile> { ProfileScreen() }
    composable<Destination.Auth.ChangeProfileData> { ChangeProfileDataScreen() }
    composable<Destination.Auth.Login> { LoginScreen() }
}
