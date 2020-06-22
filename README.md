# AndroidJs
Android 与 js 互相调用的例子

### Android 调用  JS 步骤

```kotlin
// 1. 设置 webView javaScriptEnabled 为 true
wv_test.settings.javaScriptEnabled = true
// 2. 加载 html
wv_test.loadUrl("file:///android_asset/show.html")
// 3. 调用 html 中定义的 js 函数 test()
wv_test.loadUrl("javascript:test()")
```

### JS 调用 Android 步骤

1. 给 webView 绑定 java 对象 -- 这一步需要提前绑定

   ```kotlin
   // 给 webView 绑定 java 对象 ,方便 js 调用 android 方法
   wv_test.addJavascriptInterface(this, "justTest")
   ```

2.  在 activity 中定义 js 中被调用的方法,需要使用 @JavascriptInterface 修饰方法

   ```kotlin
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
   ```

3. 在 js 中调用该方法

   ```html
   <button onclick="justTest.hello('js调用安卓方法！')">调用安卓方法</button>
   ```

### 参考

1. [Android 与 js 相互调用](https://www.cnblogs.com/lanxingren/p/9603633.html)