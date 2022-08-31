package com.example.webviewsetting

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.webviewsetting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        webViewSetting()
    }

    @SuppressLint("SetJavaScriptEnabled") //자바 스크립트 허용 관련 Lint tool
    private fun webViewSetting(){
        binding.mWebView.settings.apply {
            //새창 띄우기 허용
            setSupportMultipleWindows(false)

            //화면 확대 허용
            setSupportZoom(false)

            //웹뷰를 통해 content url에 접근할지 여부
            allowContentAccess = true

            //파일 접근 허용 설정
            allowFileAccess = true

            //파일 URL로부터 파일 접근 허용
            allowFileAccessFromFileURLs = true

            //화면 확대/축소 허용
            builtInZoomControls = false

            //브라우저 캐쉬 허용
            cacheMode = WebSettings.LOAD_NO_CACHE

            //로컬 저장 허용
            databaseEnabled = true

            //인코딩 설정
            defaultTextEncodingName = "UTF-8"

            //화면 확대/축소 허용
            displayZoomControls = false

            //로컬 저장 허용
            domStorageEnabled = true

            //자바 스크립트 새창 띄우기 허용
            javaScriptCanOpenWindowsAutomatically = false

            //자바 스크립트 허용
            javaScriptEnabled = true

            //컨텐츠 사이즈 맞추기
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN

            //
            loadsImagesAutomatically = true

            //html의 컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정
            loadWithOverviewMode = true

            //데이터베이스 접근 허용 여부
            databaseEnabled = true

            //클릭시 새창 안뜨게 (알리 및 요청 관련 설정)
            setSupportMultipleWindows(false)


            //
            userAgentString = "WebView"

            //html의 viewport 메타 태그 지원
            useWideViewPort = true


            //https에서 이미지가 표시 안되는 오류를 해결하기 위한 처리
            //Unnecessary; SDK_INT is always >= 26 사실 여기서는 if문이 필요가 없어 보임
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }

        }

        binding.mWebView.webViewClient = WebViewClient()
        binding.mWebView.run {
            //웹뷰에 표시할 웹사이트 주소, 웹뷰 주소
            loadUrl("http://m.naver.com")
        }
    }
}