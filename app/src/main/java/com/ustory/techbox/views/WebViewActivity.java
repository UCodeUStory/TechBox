package com.ustory.techbox.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.administrator.test2.R;
import com.ustory.techbox.core.BaseAppCompatActivity;
import com.ustory.techbox.utils.L;

/**
 * @ Author: qiyue (ustory)
 * @ Email: qiyuekoon@foxmail.com
 * @ Data:2016/3/11
 */
public class WebViewActivity extends BaseAppCompatActivity {

    private ProgressBar webviewPb;
    private WebView webview;
    private static final int PROGRESS_RATIO = 1000;
    private final static String EXTRA_URL = null;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        webview = (WebView)findViewById(R.id.webview);
        webviewPb = (ProgressBar)findViewById(R.id.webview_pb);

    }

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        AppBarLayout appBarLayout = findView(R.id.include_toolbar);
        Toolbar mToolbar = (Toolbar) appBarLayout.findViewById(R.id.global_toolbar);
        mToolbar.setTitle("外部链接");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void initListeners() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void enableCustomClients() {
        this.webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            /**
             * @param view The WebView that is initiating the callback.
             * @param url  The url of the page.
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
               /* if (url.contains("www.vmovier.com")) {
                   // WebViewUtils.injectCSS(EasyWebViewActivity.this, EasyWebViewActivity.this.webview, "vmovier.css");
                } else if (url.contains("video.weibo.com")) {
                 //   WebViewUtils.injectCSS(EasyWebViewActivity.this, EasyWebViewActivity.this.webview, "weibo.css");
                } else if (url.contains("m.miaopai.com")) {
                  //  WebViewUtils.injectCSS(EasyWebViewActivity.this, EasyWebViewActivity.this.webview, "miaopai.css");
                }*/
            }
        });
        this.webview.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                WebViewActivity.this.webviewPb.setProgress(progress);
                setProgress(progress * PROGRESS_RATIO);
                if (progress >= 80) {
                    WebViewActivity.this.webviewPb.setVisibility(View.GONE);
                } else {
                    WebViewActivity.this.webviewPb.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    protected void initData() {
        this.enableJavascript();
        this.enableCaching();
        this.enableCustomClients();
        this.enableAdjust();
        this.zoomedOut();
        L.i("qiyue","this.getUrl()="+this.getUrl().toString());
        this.webview.loadUrl(this.getUrl());
    }
    @SuppressLint("SetJavaScriptEnabled")
    private void enableJavascript() {
        this.webview.getSettings().setJavaScriptEnabled(true);
        this.webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
    }

    private void enableCaching() {
        this.webview.getSettings().setAppCachePath(getFilesDir() + getPackageName() + "/cache");
        this.webview.getSettings().setAppCacheEnabled(true);
        this.webview.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
    }

    private void enableAdjust() {
        this.webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        this.webview.getSettings().setLoadWithOverviewMode(true);
    }

    private void zoomedOut() {
        this.webview.getSettings().setLoadWithOverviewMode(true);
        this.webview.getSettings().setUseWideViewPort(true);
        this.webview.getSettings().setSupportZoom(true);
    }


    private String getUrl() {
       // return IntentUtils.getStringExtra(this.getIntent(), EXTRA_URL);
        return this.getIntent().getStringExtra(EXTRA_URL);
    }
    public static void toUrl(Context context, String url) {
        toUrl(context, url, android.R.string.untitled);
    }

    /**
     * @param context    Any context
     * @param url        A valid url to navigate to
     * @param titleResId A String resource to display as the title
     */
    public static void toUrl(Context context, String url, int titleResId) {
        toUrl(context, url, context.getString(titleResId));
    }

    /**
     * @param context Any context
     * @param url     A valid url to navigate to
     * @param title   A title to display
     */
    public static void toUrl(Context context, String url, String title) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(EXTRA_URL, url);
        L.i("qiyue","url1="+url.toString());
      //  intent.putExtra(EXTRA_TITLE, title);
        context.startActivity(intent);
    }

    /**
     * For gank api
     *
     * @param context context
     * @param url     url
     * @param title   title
     * @param type    type
     */
    public static void toUrl(Context context, String url, String title, String type) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(EXTRA_URL, url);
        L.i("qiyue","url2="+url.toString());
       // intent.putExtra(EXTRA_TITLE, title);
       // intent.putExtra(EXTRA_GANK_TYPE, type);
        context.startActivity(intent);
    }
}
