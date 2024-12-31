package com.example.mvvmcompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavApp() {

    val navController = rememberNavController()
    NavHost(navController, startDestination = "register") {
        composable(route = "register") {
            RegisterScreen() {
                navController.navigate("login/$it")
            }
        }
        composable(route = "home") {
            HomeScreen(navController)
        }
        composable(route = "login/{email}", arguments = listOf(
            navArgument("email") {
                type = NavType.StringType
            }
        )) {
            var email = it.arguments?.getString("email")
            if (email.isNullOrEmpty()) email = "default"
            LoginScreen(navController, email)
        }
    }

}

@Composable
fun RegisterScreen(onClickLogin: (email: String) -> Unit) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("This is Register Screen")
        Text("Go to Login", modifier = Modifier.clickable {
            onClickLogin("rj80599")
//            navController.navigate("login")
        })
//        Text("Go to Home", modifier = Modifier.clickable {
//            navController.navigate("home")
//        })
    }
}

@Composable
fun LoginScreen(navController: NavController, email: String) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("This is Login Screen \n value = $email")
        Text("Go to Register", modifier = Modifier.clickable {
            navController.navigate("register")
        })
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("This is Home Screen")
        Text("Go to Register", modifier = Modifier.clickable {
            navController.navigate("register")
        })
    }
}