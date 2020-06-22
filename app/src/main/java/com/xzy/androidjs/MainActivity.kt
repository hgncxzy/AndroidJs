package com.xzy.androidjs

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_android_call_js.setOnClickListener {
            testJS()
        }
        // 给 webView 绑定 java 对象 ,方便 js 调用 android 方法
        wv_test.addJavascriptInterface(this, "justTest")
    }

    /**
     * android 调用 js 里面的 test() 方法
     * **/
    @SuppressLint("SetJavaScriptEnabled")
    private fun testJS() {
        // 1. 设置 webView javaScriptEnabled 为 true
        wv_test.settings.javaScriptEnabled = true
        // 2. 加载 html
        wv_test.loadUrl("file:///android_asset/show.html")
        // 3. 调用 html 中定义的 js 函数 test()
        wv_test.loadUrl("javascript:test()")
    }

    /**
     * 供 js 调用的方法
     * */
    @JavascriptInterface
    public fun hello(str: String) {
        // 1. 给 webView 绑定 java 对象 -- 这一步需要提前绑定
        // 2. 在 activity 中定义 js 中被调用的方法
        // 3. 在 js 中调用该方法
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}