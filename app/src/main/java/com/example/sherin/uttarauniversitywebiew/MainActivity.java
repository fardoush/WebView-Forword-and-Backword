package com.example.sherin.uttarauniversitywebiew;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    private String URL = "https://www.uttarauniversity.edu.bd/";
    private SwipeRefreshLayout swipeRefreshLayout;
    private MenuItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
    }

    @SuppressLint("WrongViewCast")
    private void initView() {
        webView = findViewById(R.id.webViewId);
        swipeRefreshLayout = findViewById(R.id.swipeId);

    WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient()
        {
            public  void onPageStarted(WebView view, String url, Bitmap favicon)
            {
                swipeRefreshLayout.setRefreshing(true);


            }

            public void onPageFinished(WebView view,String url){
                swipeRefreshLayout.setRefreshing(false);
            }
            });
        webView.loadUrl(URL);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_back :
                onBackPressed();
                break;

            case R.id.menu_forward :
                onForwardPressed();
                break;

            case R.id.menu_refresh :
                webView.reload();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    private void onForwardPressed(){
        if(webView.canGoForward())
        {
            webView.goForward();
        }
        else{
            Toast.makeText(this, "Can't go further", Toast.LENGTH_SHORT).show();
        }
    }

    public  void  onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        }
        else {
            finish();
        }

    }

    }