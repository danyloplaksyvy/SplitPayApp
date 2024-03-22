package com.example.splitpayapp

sealed class Screen(val route: String) {
    object AddExpenseScreen: Screen("addexpensescreen")
    object ArticleScreen: Screen("articlescreen")
    object FriendsScreen: Screen("friendsscreen")
    object GroupsScreen: Screen("groupsscreen")
    object RecentActivityScreen: Screen("recentactivityscreen")
}