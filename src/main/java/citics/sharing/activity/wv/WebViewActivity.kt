package citics.sharing.activity.wv

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.webkit.*
import androidx.core.os.bundleOf
import citics.sharing.activity.base.BaseActivity
import citics.sharing.activity.base.BaseViewModel
import citics.sharing.customview.HeaderLayout
import com.sharing.databinding.FragmentWebviewBinding
import timber.log.Timber

/**
 * Created by ChinhQT on 28/02/2023.
 */
class WebViewActivity : BaseActivity<FragmentWebviewBinding, BaseViewModel>() {
    override val viewModel: BaseViewModel = BaseViewModel()

    private var url: String? = null
    private var isDocumentFile: Boolean? = true
    private var newUA: Boolean? = true
    protected var myAssetID: String? = null
    private var mTitle: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arguments = intent.extras
        url = arguments?.getString(KEY_URL)
        myAssetID = arguments?.getString(KEY_MY_ASSET_ID)
        isDocumentFile = arguments?.getBoolean(KEY_IS_DOCUMENT)
        newUA = arguments?.getBoolean(KEY_NEWUA)
        mTitle = arguments?.getString(KEY_TITLE)

        url.let {
            initWV(it)
        }

        mTitle?.let {
            getHeaderLayout().setTitle(it)
        } ?: kotlin.run {
            url.let {
                val fileName = it?.substring(it.lastIndexOf('/') + 1)
                getHeaderLayout().setTitle(fileName ?: "")
            }
        }
    }

    fun getHeaderLayout(): HeaderLayout {
        return binding.headerLayout
    }


    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
    }

    override fun onPause() {
        super.onPause()
        Timber.d("onPause")
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWV(url: String?) {
        binding.webView.webChromeClient = object : WebChromeClient() {

            override fun onProgressChanged(view: WebView, progress: Int) {
                if (binding.progress.visibility != View.VISIBLE) {
                    binding.progress.visibility = View.VISIBLE
                }

                if (progress == 100) {
                    binding.progress.visibility = View.GONE
                }
            }

        }
        binding.webView.settings.allowContentAccess = true
        binding.webView.settings.allowFileAccess = true
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.loadWithOverviewMode = true
        binding.webView.settings.useWideViewPort = true
        binding.webView.settings.lightTouchEnabled = true
        binding.webView.settings.builtInZoomControls = true
        binding.webView.settings.displayZoomControls = true
        binding.webView.settings.setSupportZoom(true)

        if (isDocumentFile == true) {
            makeWebViewClient()
            binding.webView.loadUrl(DOMAIN + url)
        } else {
            if (newUA == true) {
                binding.webView.webViewClient = object : WebViewClient() {

                    override fun onLoadResource(view: WebView, url: String) {
                        super.onLoadResource(view, url)
                        view.evaluateJavascript(
                            "document.querySelector('meta[name=\"viewport\"]').setAttribute('content'," +
                                    " 'width=1024px, initial-scale=' + (document.documentElement.clientWidth / 1024));",
                            null
                        )
                    }

                    override fun onPageFinished(view: WebView, url: String) {
                        super.onPageFinished(view, url)
                        binding.webView.zoomIn()
                    }

                    override fun onReceivedError(
                        view: WebView?,
                        request: WebResourceRequest?,
                        error: WebResourceError?
                    ) {
                        super.onReceivedError(view, request, error)
                        openChrome()
                    }
                }
                val newUA =
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:112.0) Gecko/20100101 Firefox/112.0"
                binding.webView.settings.userAgentString = newUA
                binding.webView.settings.useWideViewPort = true
                binding.webView.evaluateJavascript(
                    "document.querySelector('meta[name=\"viewport\"]').setAttribute('content'," +
                            " 'width=1024px, initial-scale=' + (document.documentElement.clientWidth / 1024));",
                    null
                )
            } else {
                makeWebViewClient()
            }
            binding.webView.loadUrl(url ?: "")
        }

    }

    fun openChrome() {
        val urlIntent = Intent(
            Intent.ACTION_VIEW, Uri.parse(url)
        )
        startActivity(urlIntent)
    }

    private fun makeWebViewClient() {
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                openChrome()
            }
        }
    }

    companion object {
        const val DOMAIN = "https://view.officeapps.live.com/op/view.aspx?src="
        const val KEY_URL = "url"
        const val KEY_IS_DOCUMENT = "KEY_IS_DOCUMENT"
        const val KEY_MY_ASSET_ID = "KEY_MY_ASSET_ID"
        const val KEY_TITLE = "KEY_TITLE"
        const val KEY_NEWUA = "KEY_NEWUA"
        fun newIntent(
            context: Context,
            url: String,
            myAssetID: String = "",
            isDocumentFile: Boolean = true,
            title: String = "",
            makeNewUA: Boolean = false
        ): Intent {
            val intent = Intent(context, WebViewActivity::class.java)
            val data = bundleOf(
                KEY_URL to url,
                KEY_IS_DOCUMENT to isDocumentFile,
                KEY_MY_ASSET_ID to myAssetID,
                KEY_TITLE to title,
                KEY_NEWUA to makeNewUA
            )
            intent.putExtras(data)
            return intent
        }
    }

    override val bindingInflater: (LayoutInflater) -> FragmentWebviewBinding
        get() = FragmentWebviewBinding::inflate


}