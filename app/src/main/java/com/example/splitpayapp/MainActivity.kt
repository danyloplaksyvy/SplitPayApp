package com.example.splitpayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.splitpayapp.presentation.googlesignin.GoogleAuthUiClient
import com.example.splitpayapp.presentation.navigation.graphs.RootNavigationGraph
import com.example.splitpayapp.presentation.data.datastore.viewmodel.DataStoreViewModel
import com.example.splitpayapp.ui.theme.MyTheme
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<DataStoreViewModel>()

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = getColor(R.color.main_blue)
        installSplashScreen().setKeepOnScreenCondition {
            mainViewModel.isLoading
        }
//        val userId = Firebase.auth.currentUser?.uid ?: return // Get the user's UID (or handle the null case)
//        val fs = Firebase.firestore
//        fs.collection("usersbook").document(userId).set(mapOf("name" to "My Book"))
//            .addOnSuccessListener { Log.d("Firestore", "DocumentSnapshot successfully written!") }
//            .addOnFailureListener { e -> Log.w("Firestore", "Error writing document", e) }
        setContent {
            MyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootNavigationGraph(
                        googleAuthUiClient = googleAuthUiClient,
                        startDestination = mainViewModel.startDestination,
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }
}

