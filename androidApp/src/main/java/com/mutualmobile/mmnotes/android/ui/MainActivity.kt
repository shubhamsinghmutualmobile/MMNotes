package com.mutualmobile.mmnotes.android.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.mutualmobile.mmnotes.android.theme.BaseComposeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.LandingPageDestination
import com.ramcosta.composedestinations.rememberDestinationsNavController

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            BaseComposeTheme {
                val navController = rememberDestinationsNavController()
                DestinationsNavHost(
                    navController = navController,
                    startDestination = LandingPageDestination
                )
            }
        }
    }
}
