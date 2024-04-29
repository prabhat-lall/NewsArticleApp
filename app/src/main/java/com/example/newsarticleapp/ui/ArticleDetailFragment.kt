package com.example.newsarticleapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsarticleapp.R
import com.example.newsarticleapp.databinding.FragmentArticleDetailBinding

private const val ARG_WEB_URL = "webUrl"

class ArticleDetailFragment : Fragment() {

    private lateinit var binding: FragmentArticleDetailBinding
    private var webUrl: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            webUrl = it.getString(ARG_WEB_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentArticleDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initListeners()
    }

    private fun initListeners() {
        binding.backBtn.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.openInChrome.setOnClickListener {
            loadWebView(webUrl!!)
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initUI() {
//        binding.webView.settings.javaScriptEnabled = true
//        binding.webView.loadUrl(webUrl!!)
    }

    private fun loadWebView(url:String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setPackage("com.android.chrome")
        startActivity(intent)
    }

    companion object {

        @JvmStatic
        fun newInstance(webUrl: String) =
            ArticleDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_WEB_URL, webUrl)
                }
            }
    }
}