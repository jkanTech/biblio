package com.jkantech.biblioispt.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.solver.widgets.ConstraintWidget.GONE
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jkantech.biblioispt.R
import com.jkantech.biblioispt.ui.utils.toasMessage

class ReadActivity : AppCompatActivity() {
    var url:String=""
    var format:String=""
    lateinit var webView:WebView
    lateinit var swipe:SwipeRefreshLayout
    lateinit var progressBar:ProgressBar
    lateinit var no_connexion:RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)
        val toolbar:Toolbar=findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val intent:Intent=getIntent()
        url= intent.getStringExtra("url").toString()
        format=intent.getStringExtra("format").toString()

         webView = findViewById(R.id.webView)
        progressBar=findViewById(R.id.progressBar)
         swipe=findViewById(R.id.swipe)
        no_connexion=findViewById(R.id.no_connexion)
        no_connexion.visibility=View.GONE

        swipe.setOnRefreshListener {

        }
        if (format.trim()=="web"){
            //url= intent.getStringExtra("url").toString()


        }else{
            toasMessage(this,"PDF")

        }

        intitWebView()

    }
    @SuppressLint("SetJavaScriptEnabled")
    private fun intitWebView(){



        webView.settings
        webView.loadUrl(url)
        val settings=webView.settings
        settings.setAppCacheEnabled(false)
        settings.loadWithOverviewMode=true
        settings.useWideViewPort=true
        settings.domStorageEnabled=true
        settings.javaScriptEnabled=true
        webView.certificate
        webView.favicon
        webView.webViewClient=object : WebViewClient(){

            fun onProgressChanged(view: WebView, newProgress: Int) {
                progressBar.progress = newProgress
            }
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                swipe.isRefreshing=false
                progressBar.progress
                no_connexion.visibility=View.GONE



                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                progressBar.visibility=View.GONE
                swipe.isRefreshing=false
                super.onPageFinished(view, url)
            }


            override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?) {
                swipe.isRefreshing=false
                toasMessage(this@ReadActivity,"Pas de Connection")
                no_connexion.visibility=View.VISIBLE


            }


        }

    }

    override fun onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack()
        }else

            super.onBackPressed()
        webView.clearCache(true)

    }

}