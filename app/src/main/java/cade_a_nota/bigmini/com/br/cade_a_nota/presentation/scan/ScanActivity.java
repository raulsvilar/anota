package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.scan;

import android.os.Bundle;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import cade_a_nota.bigmini.com.br.cade_a_nota.R;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.base.BaseActivity;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.noteregister.NewNoteFragment;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanActivity extends BaseActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

    }


    public void startScanner() {
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
        mScannerView.stopCamera();
        NewNoteFragment noteFragment = NewNoteFragment.newInstance(result.getText());
        noteFragment.setCancelable(false);
        noteFragment.show(getSupportFragmentManager(), getString(R.string.new_note));
    }

    @Override
    protected void onResume() {
        super.onResume();
        startScanner();
    }
}
