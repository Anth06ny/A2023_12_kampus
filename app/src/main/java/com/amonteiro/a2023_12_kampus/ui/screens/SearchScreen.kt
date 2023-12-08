package com.amonteiro.a2023_12_kampus.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.amonteiro.a2023_12_kampus.ComposeViewModel
import com.amonteiro.a2023_12_kampus.R
import com.amonteiro.a2023_12_kampus.Routes
import com.amonteiro.a2023_12_kampus.ui.theme.A2023_12_kampusTheme
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder


//Code affiché dans la Preview
@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun SearchScreenPreview() {
    A2023_12_kampusTheme {
        Surface(modifier = Modifier.fillMaxWidth(), color = Color.LightGray) {
            SearchScreen()
        }
    }
}

//Composable représentant l'ensemble de l'écran
@Composable
fun SearchScreen(modifier: Modifier = Modifier, navHostController: NavHostController? = null,
                 viewModel: ComposeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    //En cas de changement de searchText, recomposition et donc recalcule de la variable filterList
    val filterList = viewModel.myList.filter { it.text.contains(viewModel.searchText) }

    Column(modifier) {
        SearchBar(textValue = viewModel.searchText, onValueChange = {
            viewModel.uploadSearchText(it)
        })
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.weight(1f)) {
            items(filterList.size) {
                PictureRowItem(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color.White)
                        .clickable {
                            navHostController?.navigate(Routes.DetailScreen.addParam(it))
                        }
                    , filterList[it]
                )
            }
        }

        Row(modifier = Modifier.align(CenterHorizontally)) {
            Button(
                onClick = { viewModel.uploadSearchText("") },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Clear filter")
            }

            Button(
                onClick = {
                    viewModel.loadData()

                },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Icon(
                    Icons.Filled.Send,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Load data")
            }
        }


    }
}

@Composable
fun SearchBar(modifier: Modifier = Modifier, textValue: String, onValueChange: (String) -> Unit = {}) {

    var i = remember { mutableIntStateOf(0) }

    TextField(
        value = "$textValue",
        onValueChange = onValueChange, //Action
        leadingIcon = { //Image d'icone
            Icon(
                imageVector = Icons.Sharp.Search,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = null
            )
        },
        placeholder = { //Texte d'aide
            Text("Votre recherche ici")
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)//Hauteur minimum
            .padding(8.dp)
    )
}

//Composable affichant 1 PictureData
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PictureRowItem(modifier: Modifier = Modifier, data: PictureData) {

    var isExpended by remember { mutableStateOf(false) }

    val displayText = if (isExpended) data.longText else (data.longText.take(20) + "...")

    Row(modifier = modifier.fillMaxWidth()) {
        GlideImage(
            model = data.url,
            contentDescription = "Pas de description",
            loading = placeholder(R.mipmap.ic_launcher_round), // Image de chargement
            // Image d'échec. Permet également de voir l'emplacement de l'image dans la Preview
            failure = placeholder(R.mipmap.ic_launcher),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(100.dp)
                .fillMaxHeight()
        )

        Column(Modifier.clickable {
            isExpended = !isExpended
        }) {
            Text(
                text = data.text,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .animateContentSize(),
                text = displayText,
                fontSize = 14.sp,
                color = Color.Blue
            )
        }
    }
}


const val LONG_TEXT = """Le Lorem Ipsum est simplement
    du faux texte employé dans la composition
    et la mise en page avant impression.
    Le Lorem Ipsum est le faux texte standard
    de l'imprimerie depuis les années 1500"""

data class PictureData(val url: String, val text: String, val longText: String)

//jeu de donnée
val pictureList = arrayListOf(
    PictureData("https://picsum.photos/200", "ABCD", LONG_TEXT),
    PictureData("https://picsum.photos/201", "BCDE", LONG_TEXT),
    PictureData("https://picsum.photos/202", "CDEF", LONG_TEXT),
    PictureData("https://picsum.photos/203", "EFGH", LONG_TEXT)
)


