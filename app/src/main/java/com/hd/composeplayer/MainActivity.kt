package com.hd.composeplayer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.hd.composeplayer.ui.theme.ComposePlayerTheme
import com.hd.composevideoPlayer.videoPlayer.Player

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        var uri: Uri? = null
        super.onCreate(savedInstanceState)
        val intent = intent
        if(intent != null && intent.action != null && intent.type != null){
            if(intent.action == Intent.ACTION_VIEW && intent.type!!.startsWith("video/")){
                 uri = intent.data
            }
        }
        setContent {
            ComposePlayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if(uri != null){
                        HomeScreen(uri)
                    } else{
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                         Text(text = "Sorry that was not video" , fontSize = 26.sp , fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun HomeScreen(uri: Uri) {
    Box(Modifier.fillMaxSize()){
        val context = LocalContext.current
        Player(context = context , url = uri , selected = true ,)
    }
}


