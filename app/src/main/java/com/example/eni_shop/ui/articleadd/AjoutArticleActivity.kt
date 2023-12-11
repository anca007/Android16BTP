package com.example.eni_shop.ui.articleadd

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.eni_shop.ui.HomeActivity
import com.example.eni_shop.bo.Article
import com.example.eni_shop.databinding.ActivityMainBinding
import com.example.eni_shop.repository.ArticleRepository

class AjoutArticleActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var vm : AjoutArticleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = ViewModelProvider(this)[AjoutArticleViewModel::class.java]
        binding.vm = vm

        binding.button.setOnClickListener {
            vm.addArticle()

            Intent(this, HomeActivity::class.java).also {
                it.putExtra("title", vm.title)
                it.putExtra("price", vm.price.toString())
                startActivity(it)
            }


        }


    }


}