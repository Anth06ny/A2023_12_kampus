package com.amonteiro.a2023_12_kampus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amonteiro.a2023_12_kampus.ui.screens.DetailScreen
import com.amonteiro.a2023_12_kampus.ui.screens.PictureData
import com.amonteiro.a2023_12_kampus.ui.screens.SearchScreen
import com.amonteiro.a2023_12_kampus.ui.screens.pictureList
import com.amonteiro.a2023_12_kampus.ui.theme.A2023_12_kampusTheme

class ComposeExActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            A2023_12_kampusTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppNavigation()
                    //TestShareRemember()
                }
            }
        }
    }
}


@Composable
fun AppNavigation() {

    val navController: NavHostController = rememberNavController()
    val viewModel: ComposeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()

    //Import version avec Composable
    NavHost(navController = navController, startDestination = Routes.SearchScreen.route) {
        //Route 1 vers notre SearchScreen
        composable(Routes.SearchScreen.route) {
            //on peut passer le navController à un écran s'il déclenche des navigations
            SearchScreen(navHostController = navController, viewModel = viewModel)
        }

        //Route 2 vers un écran de détail
        composable(
            route = Routes.DetailScreen.route,
            arguments = listOf(navArgument("data") { type = NavType.IntType })
        ) {
            val position = it.arguments?.getInt("data", 0) ?: 0
            DetailScreen(navHostController = navController , position = position, viewModel = viewModel)
        }
    }
}

sealed class Routes(val route: String) {
    //Route 1
    object SearchScreen : Routes("homeScreen")

    //Route 2 avec paramètre
    object DetailScreen : Routes("detailScreen/{data}") {
        //Méthode(s) qui vienne(nt) remplit le ou les paramètre
        fun addParam(position: Int) = "detailScreen/$position"

        //fun first() = "detailScreen/0"
    }
}

class ComposeViewModel : ViewModel() {
    //_myList modifiable à l'intérieur de la classe, myList non modifiable à l'exterieur
    private var _myList = mutableStateListOf<PictureData>()
    val myList  : List<PictureData> = _myList

    var searchText by mutableStateOf("")
        private set

    fun uploadSearchText(newText : String){
        searchText = newText
    }

    fun loadData() {//Simulation de chargement de donnée
        _myList.clear()

//        viewModelScope.launch(Dispatchers.Default) {
//            //Ma requete
//
//        }
        Thread.sleep(1000) //simule temps de la requête
        _myList.addAll(pictureList)

    }
}
