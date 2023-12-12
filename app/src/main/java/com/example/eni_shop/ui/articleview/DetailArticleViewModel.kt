package com.example.eni_shop.ui.articleview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDAO
import com.example.eni_shop.db.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "DetailArticleViewModel"
class DetailArticleViewModel(private val articleDAO: ArticleDAO) : ViewModel() {

    var fav = MutableLiveData<Boolean>(false)
    fun addArticleToFav(article: Article){

        viewModelScope.launch(Dispatchers.IO) {
            articleDAO.addNewOne(article)
        }
    }

    fun deleteArticleFav(article: Article){
        viewModelScope.launch(Dispatchers.IO) {
            articleDAO.delete(article)
        }

    }

    fun checkArticle(id : Long){

        viewModelScope.launch(Dispatchers.IO) {
            val article = articleDAO.selectById(id)
            Log.i(TAG, "checkArticle: " + article)
            if(article != null){
                Log.i(TAG, "checkArticle: ICI !" )
                fav.postValue(true)
            }

        }

    }


    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application =
                    checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return DetailArticleViewModel(
                    AppDatabase.getInstance(application.applicationContext).articleDao()
                ) as T
            }
        }

    }


}