package com.example.eni_shop.ui.articlelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.eni_shop.bo.Article
import com.example.eni_shop.databinding.FragmentListeArticleBinding

class ListeArticleFragment : Fragment() {

    lateinit var binding: FragmentListeArticleBinding
    val vm : ListArticleViewModel by viewModels { ListArticleViewModel.Factory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListeArticleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFav.setOnClickListener {
            vm.getArticleListFav().observe(viewLifecycleOwner) {
                displayArticles(it)
            }
        }

        //viewLifecycleOwner à utiliser dans les fragments
        vm.getArticleList().observe(viewLifecycleOwner) {
            displayArticles(it)
        }

        binding.btnToDetail.setOnClickListener {
            var article = vm.getRandomArticle()
            if (article != null) {
                val direction =
                    ListeArticleFragmentDirections.actionListToDetailArticle(
                        article
                    )
                Navigation.findNavController(view).navigate(direction)
            }

        }

    }

    private fun displayArticles(articles: List<Article>) {
        var titles = ""
        articles.forEach {
            titles += it.titre + "\n"
        }.also {
            binding.tvArticles.text = titles
        }

    }


}