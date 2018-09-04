package com.example.jaishreepal.thenetwork;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class webview5 extends AppCompatActivity {
    android.webkit.WebView webview5;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview5);

        /*progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...\nPlease Wait.");*/
        Bundle extras=getIntent().getExtras();
        webview5=findViewById(R.id.wv2);
        WebSettings webSettings=webview5.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview5.getSettings().setLoadWithOverviewMode(true);
        webview5.getSettings().setUseWideViewPort(true);
        webview5.getSettings().setBuiltInZoomControls(true);

        webview5.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webview5.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview5.setScrollbarFadingEnabled(true);
        webview5.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                DownloadManager.Request request=new DownloadManager.Request(Uri.parse(url));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"notes");
                DownloadManager downloadManager=(DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);

            }
        });
        
        webview5.getSettings().getJavaScriptCanOpenWindowsAutomatically();
        /*if(Build.VERSION.SDK_INT>=Build.VERSION_CODES)*/
        webview5.setWebViewClient(new WebViewClient());
       /* webview.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.dismiss();
            }
        });*/
        webview5.loadUrl(extras.getString("link6"));

    }
}
