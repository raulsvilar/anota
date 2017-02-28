package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cade_a_nota.bigmini.com.br.cade_a_nota.R;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.base.BaseActivity;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.notes.EmptyNotesFragment;


import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends BaseActivity implements ZXingScannerView.ResultHandler {
    FloatingActionButton floatingActionButton;
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);
        if (savedInstanceState == null) {
            addFragment(new EmptyNotesFragment());
        }
    }

    public void startScanner(View view) {
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        List<BarcodeFormat> formats = new ArrayList<>();
        formats.add(BarcodeFormat.QR_CODE);
        mScannerView.setFormats(formats);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        /*
            Chama a proxima tela onde o usuario ira preencher as informações adcionais e salvar a nota
         */
        Log.e("QRCODE", result.getText());
    }

}
