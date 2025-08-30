package cz.pesta.skoda_test.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.ui.Modifier
import cz.pesta.skoda_test.ui.navigation.MainNavigation
import dagger.hilt.android.AndroidEntryPoint
import vwg.skoda.maulcompose.lib.foundation.MaulTheme

@AndroidEntryPoint
class MainActivity : ConnectionActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Should fix statusbar color in dark mode, not working...
        setContent {
            MaulTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.safeDrawing.asPaddingValues())
                ) {
                    MainNavigation()
                }
            }
        }
    }
}