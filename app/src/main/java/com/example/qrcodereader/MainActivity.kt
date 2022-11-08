package com.example.qrcodereader

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.qrcodereader.ui.theme.QRCodeReaderTheme
import com.journeyapps.barcodescanner.ScanOptions
import android.widget.Toast
import androidx.compose.material.TextButton
import com.journeyapps.barcodescanner.ScanContract


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val barcodeLauncher = registerForActivityResult(
            ScanContract()
        ) { result ->
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG)
                    .show()
            }
        }

        // Launch
        fun onButtonClick() {
            val options = ScanOptions().setOrientationLocked(true)
            barcodeLauncher.launch(options)
        }

        super.onCreate(savedInstanceState)
        setContent {
            QRCodeReaderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    TextButton(onClick = { onButtonClick() }) {
                        Text(text = "QRCodeReaderを起動する")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QRCodeReaderTheme {

    }
}
