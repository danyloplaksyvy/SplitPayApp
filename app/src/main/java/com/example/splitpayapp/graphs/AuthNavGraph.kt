package com.example.splitpayapp.graphs


import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.splitpayapp.Screens
import com.example.splitpayapp.googlesignin.GoogleAuthUiClient
import com.example.splitpayapp.googlesignin.SignInViewModel
import com.example.splitpayapp.googlesignin.SignInWithGoogleScreen
import com.example.splitpayapp.views.SplashScreen
import com.example.splitpayapp.views.auth.LoginScreen
import com.example.splitpayapp.views.auth.ForgotScreen
import com.example.splitpayapp.views.auth.RegisterScreen
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTH,
        startDestination = Screens.SplashScreen.name
    ) {
        composable(route = Screens.SplashScreen.name) {
            SplashScreen(navController)
        }
        composable(route = Screens.LoginScreen.name) {
            LoginScreen(navController = navController)
        }
        composable(route = Screens.RegisterScreen.name) {
            RegisterScreen(navController)
        }
        composable(route = Screens.ForgotScreen.name) {
            ForgotScreen(navController)
        }
    }
}