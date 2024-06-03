package com.example.splitpayapp.presentation.view.main.articlesscreen.articlerepository

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor() {
    private val db = Firebase.firestore

    companion object {
        private const val ARTICLES = "articles"
    }
}