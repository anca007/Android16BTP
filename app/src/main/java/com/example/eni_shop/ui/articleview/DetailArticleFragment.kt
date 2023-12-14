package com.example.eni_shop.ui.articleview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.eni_shop.databinding.FragmentDetailArticleBinding
import com.squareup.picasso.Picasso


private const val TAG = "DetailArticleViewModel"
class DetailArticleFragment : Fragment() {

    lateinit var binding: FragmentDetailArticleBinding
    val args: DetailArticleFragmentArgs by navArgs()
    val vm: DetailArticleViewModel by viewModels { DetailArticleViewModel.Factory }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailArticleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val article = args.article
        //popule l'imageView avec l'url de l'image
        Picasso.get().load(article.urlImage).into(binding.imageView);
        vm.checkArticle(article.id)

        binding.article = article
        binding.vm = vm
        binding.lifecycleOwner = this

        binding.checkBoxFav.setOnClickListener {
            if (binding.checkBoxFav.isChecked) {
                vm.addArticleToFav(article)
                Toast.makeText(
                    context,
                    "L'article a été ajouté !",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                //je supprime l'article
                vm.deleteArticleFav(article)
                Toast.makeText(
                    context,
                    "L'article a été supprimé !",
                    Toast.LENGTH_LONG
                ).show()
            }

        }


        binding.tvArticleTitle.setOnClickListener {

//            Intent(Intent.ACTION_WEB_SEARCH).also {
//                it.putExtra(SearchManager.QUERY, "eni-shop " + article.titre)
//                startActivity(it)
//            }

            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/search?q=eni-shop+" + article.titre)
            ).also {
                startActivity(it)
            }

        }


    }

}