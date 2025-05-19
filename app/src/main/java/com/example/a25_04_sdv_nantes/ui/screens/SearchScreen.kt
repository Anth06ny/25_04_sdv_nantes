package com.example.a25_04_sdv_nantes.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.a25_04_sdv_nantes.ui.theme._25_04_sdv_nantesTheme
import com.example.a25_04_sdv_nantes.viewmodel.MainViewModel


@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true,
           uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES or android.content.res.Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun SearchScreenPreview() {
    //Il faut remplacer NomVotreAppliTheme par le thème de votre application
    //Utilisé par exemple dans MainActivity.kt sous setContent {...}
    _25_04_sdv_nantesTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SearchScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun SearchScreen(modifier:Modifier = Modifier, mainViewModel: MainViewModel = MainViewModel()) {

    val list = mainViewModel.dataList.collectAsStateWithLifecycle().value

    Column (modifier= modifier.fillMaxSize().background(Color.LightGray)) {
        println("SearchScreen()")
        Text(text = "Text1",fontSize = 20.sp,
modifier = Modifier.background(Color.Yellow)
    .padding(4.dp)
    .background(Color.Red)
            )
        Spacer(Modifier.size(8.dp))
        Text(text = "Text2",fontSize = 14.sp)
        repeat(list.size) {
            PictureRowItem(list[it].title)
        }
    }
}

@Composable
fun PictureRowItem(text:String){
    Text(text = text,fontSize = 20.sp)
}