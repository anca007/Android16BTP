package com.example.eni_shop.ui.articleadd

import androidx.lifecycle.ViewModel
import com.example.eni_shop.bo.Article
import com.example.eni_shop.repository.ArticleRepository
import com.example.eni_shop.utils.DateConverter
import java.text.SimpleDateFormat

class AjoutArticleViewModel : ViewModel() {

    var title: String = ""
    var description: String = ""
    var price: Double = 0.0
    var date: String = ""

    private var articleRepository = ArticleRepository()

    fun addArticle() {

        if(title != "" && description != "" && price != 0.0 && date != ""){
            val article =
                Article(0, title, description, price, "", DateConverter.stringToSimpleDate(date))
            articleRepository.addArticle(article)
        }

    }


}