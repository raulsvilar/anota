package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.webview;

import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MyWebClient extends WebViewClient {

    private ProgressBar progressBar;

    public MyWebClient(){};
    public MyWebClient(ProgressBar progressBar){
        this.progressBar = progressBar;
    };

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        // TODO Auto-generated method stub
        super.onPageStarted(view, url, favicon);
        view.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override

    public void onPageFinished(WebView view, String url) {
        progressBar.setVisibility(View.INVISIBLE);
        view.setVisibility(View.VISIBLE);
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // TODO Auto-generated method stub

        view.loadUrl(url);
        return true;

    }
}