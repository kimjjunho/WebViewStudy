package com.example.webviewsetting

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Window
import android.webkit.*
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
    private fun webViewSetting() {
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }

        }
        binding.mWebView.run {
            //내부에 JavascriptInterface 메서드 구현
            addJavascriptInterface(this, "MainActivity")

            //웹뷰 캐시 날리기
            //이전 페이지 데이터 저장이 캐쉬
            clearCache(true)

            //웹뷰의 히스토리 삭제(뒤로가기 삭제)
            //페이지를 이전으로 이동할 수 있는 기능이 히스토리
            clearHistory()

            //웹뷰에 표시할 웹사이트 주소, 웹뷰 주소
            loadUrl("http://m.naver.com")

            //경고 표시나 윈도우 닫기 등의 Web 브라우저 이벤트를 구하기 위한 클래스
            webChromeClient = object : WebChromeClient() {
                
            }

            //webChromeClient와 가장 큰 차이는 새탭이 열리는지의 유무이다
            webViewClient = object : WebViewClient() {
                //WebView에서 처음 한 번만 호출되는 메서드 페이지 로딩이 시작된 것을 알림
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                }

                //WebView가 주어진 URL로 지정된 리소스를 로드할 것이라고 알립니다
                override fun onLoadResource(view: WebView?, url: String?) {
                    super.onLoadResource(view, url)
                }

                //방문한 링크를 데이터베이스에 업데이트한다고 알립니다.
                override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
                    super.doUpdateVisitedHistory(view, url, isReload)
                }

                //WebView에서 처음 한 번만 호출되는 메서드, 페이지 로딩이 완료된 것을 알립니다
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                }

                //호스트 응용 프로그램에게 오류를 보고합니다.
                //웹뷰는 인터넷이 연결되어 있지 않았을때 주소가 노출되는 단점이 있다.
                // 이럴경우 url 주소를 보안상 노출된면 안되기 때문에 숨길경우 사용하면 유용할 것 같음
                override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                    super.onReceivedError(view, errorCode, description, failingUrl)

                    when(errorCode){
                        ERROR_AUTHENTICATION -> {} //서버에서 사용자 인증 실패
                        ERROR_BAD_URL -> {} //잘못된 URL
                        ERROR_CONNECT -> {} //서버로 연결 실패
                        ERROR_FAILED_SSL_HANDSHAKE -> {} // SSL handshake 수행실패
                        ERROR_FILE -> {} //일반 파일 오류
                        ERROR_FILE_NOT_FOUND -> {} //파일을 찾을 수 없습니다
                        ERROR_HOST_LOOKUP -> {}//서버 또는 프록시 호스트 이름 조회 실패
                        ERROR_IO -> {} //서버에서 읽거나 서버로 쓰기 실패
                        ERROR_PROXY_AUTHENTICATION -> {} //프록시에서 사용자 인증 실패
                        ERROR_REDIRECT_LOOP -> {} //너무 많은 리디렉션
                        ERROR_TIMEOUT -> {} //연결 시간 초과
                        ERROR_TOO_MANY_REQUESTS -> {} //페이지 로드중 너무 많은 요청 발생
                        ERROR_UNKNOWN -> {} //일반 오류
                        ERROR_UNSUPPORTED_SCHEME -> {} //URI가 지원되지 않는 방식
                        ERROR_UNSUPPORTED_AUTH_SCHEME -> {} //지원되지 않는 인증 체계
                    }
                }

                //인증 요청을 처리한다고 알림. 기본 동작은 요청을 취소하는 것(http 인증요청이 있을 경우)
                override fun onReceivedHttpAuthRequest(view: WebView?, handler: HttpAuthHandler?, host: String?, realm: String?) {
                    super.onReceivedHttpAuthRequest(view, handler, host, realm)
                }

                //스케일이 변경되었을 때 처리할 내용을 구현(확대나 크기등이 변화 있을 경우)
                override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
                    super.onScaleChanged(view, oldScale, newScale)
                }

                //잘못된 키 입력이 있을 경우 호출되는 메서드
                //시스템키를 재외하고, shouldOverrideKeyEvent가 true를 반환하는 경우나 일반적인 flow에서 항상 키 이벤트를 처리
                //키 이벤트가 발생된 곳으로 부터 비동기적으로 호출
                override fun onUnhandledKeyEvent(view: WebView?, event: KeyEvent?) {
                    super.onUnhandledKeyEvent(view, event)
                }

                //사용자의 키 입력이 있을 경우 호출되는 메서드
                //방식은 위와 onUnhandledKeyEvent와 동일
                override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
                    return super.shouldOverrideKeyEvent(view, event)
                }

                //새로운 URL이 현재 WebView에 로드되려고 할 때 호스트 응용 프로그램에게 컨트롤을 대신할 기회를 준다
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.loadUrl(url!!)
                    return true
                }
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    return super.shouldOverrideUrlLoading(view, request)
                }
            }
        }
    }
}

