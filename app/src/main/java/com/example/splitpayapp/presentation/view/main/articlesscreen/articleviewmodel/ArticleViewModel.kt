package com.example.splitpayapp.presentation.view.main.articlesscreen.articleviewmodel

import androidx.lifecycle.ViewModel
import com.example.splitpayapp.presentation.view.main.articlesscreen.articlerepository.ArticleRepository
import com.example.splitpayapp.presentation.view.main.articlesscreen.components.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
open class ArticleViewModel @Inject constructor(private val repository: ArticleRepository) :
    ViewModel() {
    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles
}