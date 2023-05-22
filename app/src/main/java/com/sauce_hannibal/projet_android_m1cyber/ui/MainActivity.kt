package com.sauce_hannibal.projet_android_m1cyber.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sauce_hannibal.projet_android_m1cyber.ui.theme.JetpackComposeBoilerplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            JetpackComposeBoilerplateTheme {
                ComposeApp()
            }
        }
    }
}