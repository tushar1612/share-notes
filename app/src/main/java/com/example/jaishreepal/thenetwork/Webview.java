package com.example.jaishreepal.thenetwork;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webview extends AppCompatActivity {
    android.webkit.WebView webview;
    /*private ProgressDialog progressDialog;*/

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        /*progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...\nPlease Wait.");*/
        Bundle extras=getIntent().getExtras();
        webview=findViewById(R.id.wv);
        WebSettings webSettings=webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setBuiltInZoomControls(true);

        webview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.setScrollbarFadingEnabled(true);
        webview.setDownloadListener(new DownloadListener() {
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
        webview.getSettings().getJavaScriptCanOpenWindowsAutomatically();
        /*if(Build.VERSION.SDK_INT>=Build.VERSION_CODES)*/
        webview.setWebViewClient(new WebViewClient());
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
        webview.loadUrl(extras.getString("link5"));
        /*webview.loadUrl("https://infinitenetwork001.wixsite.com/thenetwork");*/
    }
}
