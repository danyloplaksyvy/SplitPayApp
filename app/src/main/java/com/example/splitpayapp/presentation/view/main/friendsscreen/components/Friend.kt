package com.example.splitpayapp.presentation.view.main.friendsscreen.components

data class Friend(
    val id: Int,
    val name: String,
    val image: String = "https://picsum.photos/200",
    var isDeleting: Boolean = false
)


fun getFakeFriendList() = listOf(
    Friend(
        id = 1,
        name = "John",
        image = "https://picsum.photos/200"
    ),
    Friend(
        id = 2,
        name = "Mark",
        image = "https://picsum.photos/200"
    ),
    Friend(
        id = 3,
        name = "Alice",
        image = "https://picsum.photos/200"
    ),
    Friend(
        id = 4,
        name = "Kert",
        image = "https://picsum.photos/200"
    ),
    Friend(
        id = 5,
        name = "Bob",
        image = "https://picsum.photos/200"
    ),
    Friend(
        id = 6,
        name = "Nastya",
        image = "https://picsum.photos/200"
    ),
    Friend(
        id = 7,
        name = "Ann",
        image = "https://picsum.photos/200"
    ),
    Friend(
        id = 8,
        name = "Dasha",
        image = "https://picsum.photos/200"
    ),
    Friend(
        id = 9,
        name = "Leon",
        image = "https://picsum.photos/200"
    ),
    Friend(
        id = 10,
        name = "Mira",
        image = "https://picsum.photos/200"
    ),
    Friend(
        id = 11,
        name = "Sol",
        image = "https://picsum.photos/200"
    ),
)