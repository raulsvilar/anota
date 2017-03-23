package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.webview;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cade_a_nota.bigmini.com.br.cade_a_nota.R;

public class WebViewActivity extends AppCompatActivity {
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        webView.setWebViewClient(new MyWebClient(progressBar));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setBackgroundColor(ContextCompat.getColor(this,R.color.color_white));
        webView.loadUrl(getIntent().getExtras().getString("url"));
    }
}
