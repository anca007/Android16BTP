package com.example.eni_shop.ui.articlelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.eni_shop.adapter.ArticleAdapter
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
                displayArticles(it, view)
            }
        }

        //viewLifecycleOwner à utiliser dans les fragments
        vm.getArticleList().observe(viewLifecycleOwner) {
            displayArticles(it, view)
        }

    }

    private fun displayArticles(articles: List<Article>, view: View) {

        binding.articleRecycler.adapter = ArticleAdapter(articles) {
            val direction =
                ListeArticleFragmentDirections.actionListToDetailArticle(it)
            Navigation.findNavController(view).navigate(direction)
        }
        binding.articleRecycler.layoutManager = LinearLayoutManager(view.context)

    }


}