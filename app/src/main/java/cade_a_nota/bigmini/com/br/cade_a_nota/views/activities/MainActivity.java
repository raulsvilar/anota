package cade_a_nota.bigmini.com.br.cade_a_nota.views.activities;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import cade_a_nota.bigmini.com.br.cade_a_nota.R;
import cade_a_nota.bigmini.com.br.cade_a_nota.views.fragments.AboutFragment;
import cade_a_nota.bigmini.com.br.cade_a_nota.views.fragments.NotesFragment;
import cade_a_nota.bigmini.com.br.cade_a_nota.views.fragments.ProfileFragment;
import me.dm7.barcodescanner.zxing.ZXingScannerView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ZXingScannerView.ResultHandler {
    @BindView(R.id.bottomNavigation)
    BottomNavigationView bottomNavigationView;
    private ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            replaceFragment(new NotesFragment());
            bottomNavigationView.getMenu().getItem(0).setChecked(true);
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.my_notes:
                replaceFragment(new NotesFragment());
                break;
            case R.id.my_profile:
                replaceFragment(new ProfileFragment());
                break;
            case R.id.about:
                replaceFragment(new AboutFragment());
                break;
        }
        return false;
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
        //Toast.makeText(this, "oi", Toast.LENGTH_SHORT).show();
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
        Log.e("QRCODE", result.getText());
    }
}
