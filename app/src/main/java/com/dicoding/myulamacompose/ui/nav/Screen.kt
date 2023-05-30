package com.dicoding.myulamacompose.ui.nav

sealed class Screen(val route: String) {
    object DetailUlama : Screen("home/{ulamaId}") {
        fun createRoute(ulamaId: String) = "home/$ulamaId"
    }
    object Home : Screen("home")
    object AboutMe : Screen("about_me")
}