package com.example.webviewsetting

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
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

    //SetJavaScriptEnabled 자바 스크립트 허용 관련 Lint tool

    @SuppressLint("SetJavaScriptEnabled", "JavascriptInterface")
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
            cacheMode.run {
                //항상 네트워크에서 가져옴
                WebSettings.LOAD_NO_CACHE

                //캐시 기간만료 시 네트워크 접속
                WebSettings.LOAD_CACHE_ELSE_NETWORK

                //캐시만 불러옴(네트워크 사용 x)
                WebSettings.LOAD_CACHE_ONLY

                //기본 모드, 캐시 시용, 기간 만료 시 네트워크 사용
                WebSettings.LOAD_NO_CACHE

                //유요한 캐시일때만 캐시에서 로드한다. 그렇지 않다면 네트워크에서 가져옴
                WebSettings.LOAD_DEFAULT

                //기본 캐시 사용 @Deprecated
                WebSettings.LOAD_NORMAL
            }

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
            //내부에 JavascriptInterface 메서드 구현
            addJavascriptInterface(this,"MainActivity")

            //웹뷰 캐시 날리기
            //이전 페이지 데이터 저장이 캐쉬
            clearCache(true)

            //웹뷰의 히스토리 삭제(뒤로가기 삭제)
            //페이지를 이전으로 이동할 수 있는 기능이 히스토리
            clearHistory()

            //웹뷰에 표시할 웹사이트 주소, 웹뷰 주소
            loadUrl("http://m.naver.com")

            //경고 표시나 윈도우 닫기 등의 Web 브라우저 이벤트를 구하기 위한 클래스
            webChromeClient =  WebChromeClient()

        }

    }




}

