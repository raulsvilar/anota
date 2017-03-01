package cade_a_nota.bigmini.com.br.cade_a_nota.presentation.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cade_a_nota.bigmini.com.br.cade_a_nota.R;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.base.BaseActivity;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.initial.InitialActivity;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.notes.EmptyNotesFragment;
import cade_a_nota.bigmini.com.br.cade_a_nota.presentation.notes.NewNoteFragment;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends BaseActivity implements ZXingScannerView.ResultHandler, MainContract.View {
    @BindView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;
    private MainContract.Presenter presenter;
    private ZXingScannerView mScannerView;

    @Override
    protected void onStart() {
        super.onStart();
        presenter = new MainPresenter();
        presenter.execute(this);
        presenter.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("Minhas Notas");
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
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

    @OnClick(R.id.floating_action_button)
    public void onClick() {
        startScanner();
    }

    @Override
    public void handleResult(Result result) {
        mScannerView.removeAllViews(); //<- here remove all the views, it will make an Activity having no View
        mScannerView.stopCamera(); //<- then stop the camera
        setContentView(R.layout.activity_main);
        NewNoteFragment noteFragment = NewNoteFragment.newInstance(result.getText());
        noteFragment.show(getSupportFragmentManager(), getString(R.string.new_note));
    }


    @Override
    public void emptyState() {
        addFragment(new EmptyNotesFragment());
        floatingActionButton.show();
    }

    @Override
    public void singedOut() {
        Toast.makeText(this, "Por favor, faça login novamente", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, InitialActivity.class));
        finish();
    }

    public void checkPermissions() {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            startActivity(new Intent(this, InitialActivity.class));
            finish();
        }
    }

}
