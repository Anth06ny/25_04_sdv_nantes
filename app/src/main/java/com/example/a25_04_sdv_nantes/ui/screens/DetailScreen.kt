package com.example.a25_04_sdv_nantes.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.a25_04_sdv_nantes.R
import com.example.a25_04_sdv_nantes.model.PictureBean
import com.example.a25_04_sdv_nantes.ui.theme._25_04_sdv_nantesTheme
import com.example.a25_04_sdv_nantes.viewmodel.LONG_TEXT

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
            or android.content.res.Configuration.UI_MODE_TYPE_NORMAL)
@Composable
fun DetailScreenPreview() {
    _25_04_sdv_nantesTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            DetailScreen(
                modifier = Modifier.padding(innerPadding),
                //jeu de donnée pour la Preview
                pictureBean = PictureBean(1, "https://picsum.photos/200", "ABCD", LONG_TEXT),
                navHostController = null)
        }
    }
}
@OptIn(ExperimentalGlideComposeApi::class)
@Composable //id du PictureBean à afficher
fun DetailScreen(modifier: Modifier = Modifier,
                 pictureBean: PictureBean,
                 navHostController: NavHostController?){

    Column(modifier = modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = pictureBean.title,
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
        )


        GlideImage(
            model = pictureBean.url,
            //Pour aller le chercher dans string.xml
            //contentDescription = getString(R.string.picture_of_cat),
            //En dur
            contentDescription = "une photo de chat",
            // Image d'attente. Permet également de voir l'emplacement de l'image dans la Preview
            loading = placeholder(R.mipmap.ic_launcher_round),
            // Image d'échec de chargement
            failure = placeholder(R.mipmap.ic_launcher),
            contentScale = ContentScale.Fit,
            //même autres champs qu'une Image classique
            modifier = Modifier.fillMaxWidth().weight(1f)
        )

        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = pictureBean.longText,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = { navHostController?.popBackStack() },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Retour")
        }

    }

}