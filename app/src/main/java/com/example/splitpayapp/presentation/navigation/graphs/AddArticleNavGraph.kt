package com.example.splitpayapp.presentation.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.splitpayapp.presentation.navigation.Screens
import com.example.splitpayapp.presentation.view.main.articlesscreen.articleviewmodel.ArticleViewModel
import com.example.splitpayapp.presentation.view.main.articlesscreen.components.AddArticleScreen

fun NavGraphBuilder.addArticleNavGraph(
    navController: NavHostController,
    articleViewModel: ArticleViewModel
) {

    navigation(route = Graph.ADD_ARTICLE, startDestination = AddArticle.ADD_ARTICLE_SCREEN.route) {
        composable(route = AddArticle.ADD_ARTICLE_SCREEN.route) {
            AddArticleScreen(
                onCancelClick = {
                    navController.navigate(Screens.ArticlesScreen.name)
                },
                onAddArticleClick = {
                    navController.navigate(Screens.ArticlesScreen.name)
                },
                articleViewModel = articleViewModel
            )
        }
    }
}

sealed class AddArticle(val route: String) {
    object ADD_ARTICLE_SCREEN : AddArticle(route = "ADD_ARTICLE_PAGE")
}
