package com.dicoding.myulamacompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.myulamacompose.ui.nav.NavItem
import com.dicoding.myulamacompose.ui.screen.AboutMeScreen
import com.dicoding.myulamacompose.ui.screen.DetailUlamaScreen
import com.dicoding.myulamacompose.ui.screen.HomeScreen
import com.dicoding.myulamacompose.ui.nav.Screen


@Composable
@Preview
fun UlamaApps(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailUlama.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { ulamaId ->
                        navController.navigate(Screen.DetailUlama.createRoute(ulamaId))
                    }
                )
            }
            composable(
                route = Screen.DetailUlama.route,
                arguments = listOf(
                    navArgument("ulamaId") {
                        type = NavType.StringType
                    }
                )) { backStackEntry ->
                val idUlama = backStackEntry.arguments?.getString("ulamaId")
                DetailUlamaScreen(
                    idUlama = idUlama ?: "",
                    navigateUp = { navController.navigateUp() }
                )
            }
            composable(Screen.AboutMe.route) {
                AboutMeScreen()
            }
        }

    }
}

@Composable
private fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val navigationItems = listOf(
        NavItem(
            title = stringResource(R.string.home),
            icon = Icons.Default.Home,
            screen = Screen.Home
        ),
        NavItem(
            title = stringResource(R.string.about_me),
            icon = Icons.Default.AccountCircle,
            screen = Screen.AboutMe
        ),
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = modifier
    ) {
        BottomNavigation(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = MaterialTheme.colors.primary
        ) {
            navigationItems.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}