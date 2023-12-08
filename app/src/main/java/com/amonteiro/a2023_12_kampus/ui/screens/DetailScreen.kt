package com.amonteiro.a2023_12_kampus.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.amonteiro.a2023_12_kampus.ComposeViewModel
import com.amonteiro.a2023_12_kampus.R
import com.amonteiro.a2023_12_kampus.ui.theme.A2023_12_kampusTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    A2023_12_kampusTheme {
        Surface(modifier = Modifier.fillMaxWidth(), color = Color.LightGray) {
            DetailScreen(position = 0)
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    navHostController: NavHostController? = null,
    position: Int,
    viewModel: ComposeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val pictureData = viewModel.myList.getOrNull(position)

    Column {
        Text(
            text = pictureData?.text ?: "non trouvé : $position",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )
        if (pictureData != null) {
            GlideImage(
                model = pictureData.url,
                contentDescription = null,
                loading = placeholder(R.mipmap.ic_launcher_round),
                failure = placeholder(R.mipmap.ic_launcher),
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
        Button(
            onClick = { navHostController?.popBackStack() },
            contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
            modifier = Modifier
                .padding(8.dp)
                .align(CenterHorizontally)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Back")
        }
    }
}